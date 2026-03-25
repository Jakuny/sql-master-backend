// /js/api.js

export async function refreshToken() {
    const refresh = localStorage.getItem('sql_refresh_token');
    if (!refresh) {
        console.warn("Refresh token missing");
        logout();
        throw new Error("No refresh token");
    }

    try {
        const response = await fetch('/api/v1/auth/refresh', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ refreshToken: refresh })
        });

        if (!response.ok) {
            console.error("Refresh request failed on server");
            logout();
            throw new Error("Refresh failed");
        }

        const data = await response.json();
        localStorage.setItem('sql_token', data.token);
        localStorage.setItem('sql_refresh_token', data.refreshToken);
        console.log("Tokens updated successfully");
        return data.token;
    } catch (error) {
        logout();
        throw error;
    }
}

export async function fetchWithAuth(url, options = {}) {
    let token = localStorage.getItem('sql_token');

    // 1. Инициализируем заголовки (или берем существующие)
    const headers = options.headers || {};

    // 2. Добавляем токен
    headers['Authorization'] = `Bearer ${token}`;

    // 3. ВАЖНО: Если мы отправляем FormData (файлы), нельзя ставить Content-Type вручную.
    // Браузер сам должен поставить 'multipart/form-data; boundary=...'
    if (!(options.body instanceof FormData)) {
        if (!headers['Content-Type']) {
            headers['Content-Type'] = 'application/json';
        }
    }

    // Собираем конфиг
    const config = {
        ...options,
        headers: headers
    };

    // 4. Делаем первый запрос
    let response = await fetch(url, config);

    // 5. Логика обновления токена (обрабатываем 401, 403 и 500 на случай ошибок сервера)
    if (response.status === 401 || response.status === 403 || response.status === 500) {
        console.log(`Auth issue or Server error (${response.status}). Trying refresh...`);

        try {
            const newToken = await refreshToken();

            // Обновляем токен в заголовках и повторяем запрос
            config.headers['Authorization'] = `Bearer ${newToken}`;
            response = await fetch(url, config);

        } catch (e) {
            console.error("Could not refresh token automatically");
            // Если рефреш не удался, пользователя выкинет logout внутри refreshToken
        }
    }

    return response;
}

export function logout() {
    // Чистим токены и имя пользователя
    localStorage.removeItem('sql_token');
    localStorage.removeItem('sql_refresh_token');
    localStorage.removeItem('sql_username');

    // Сохраняем текущий путь, чтобы вернуться после входа (кроме самого логина)
    const currentPath = window.location.pathname;
    if (currentPath !== '/login.html') {
        window.location.href = `/login.html?from=${encodeURIComponent(currentPath)}`;
    } else {
        window.location.reload(); // Если уже на логине, просто сброс
    }
}