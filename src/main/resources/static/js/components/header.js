/**
 * Компонент Хедера
 * Рендерит шапку сайта и управляет ее логикой (тема, язык, профиль)
 */
export function renderHeader(authData = { isLoggedIn: false }) {
    const headerElement = document.getElementById('main-header');
    if (!headerElement) {
        console.error("Header element with id 'main-header' not found!");
        return;
    }

    let authHtml;
    const currentPath = window.location.pathname;
    const loginUrl = `/login.html?from=${encodeURIComponent(currentPath)}`;

    // --- ЗАЩИТА: Проверяем, что username существует, прежде чем его использовать ---
    if (authData.isLoggedIn && authData.username) {
        const username = authData.username;
        let avatarHtml = username.charAt(0).toUpperCase();

        if (authData.avatarUrl) {
            const avatarSrc = `${authData.avatarUrl}?t=${new Date().getTime()}`;
            avatarHtml = `<img src="${avatarSrc}" class="w-full h-full object-cover" alt="Avatar">`;
        }

        authHtml = `
            <div class="flex items-center gap-4">
                <a href="/profile/me" class="flex items-center gap-3 hover:opacity-80 transition group" title="Профиль">
                    <div class="w-9 h-9 rounded-xl bg-gradient-to-br from-blue-500 to-purple-600 flex items-center justify-center text-xs font-black text-white uppercase border border-white/10 shadow-lg overflow-hidden group-hover:scale-105 transition duration-300">
                        ${avatarHtml}
                    </div>
                    <div class="hidden sm:flex flex-col items-start">
                        <span class="text-xs font-black text-theme-main uppercase tracking-wider leading-none">${username}</span>
                        <span class="text-[9px] text-blue-400 font-bold uppercase tracking-tighter">LVL ${authData.lvl || 0}</span>
                    </div>
                </a>
                <button onclick="confirmLogout()" class="text-theme-muted hover:text-red-500 transition-colors p-2 rounded-lg hover:bg-white/5" title="Выйти">
                    <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1"></path></svg>
                </button>
            </div>
        `;
    } else {
        // Если юзер не залогинен, или данных еще нет, показываем кнопку входа
        authHtml = `
            <a href="${loginUrl}" class="flex items-center gap-2 bg-blue-600 hover:bg-blue-500 text-white text-xs font-black uppercase tracking-widest py-2.5 px-6 rounded-xl transition-all shadow-lg shadow-blue-900/20 active:scale-95">
                <span data-i18n="btn_login">Войти</span>
            </a>
        `;
    }

    headerElement.innerHTML = `
        <nav class="max-w-7xl w-full mx-auto p-4 md:p-6 flex justify-between items-center">
            <a href="/" class="font-bold text-xl tracking-tight flex items-center gap-3 select-none group text-theme-main">
                <div class="bg-blue-600 px-3 py-1 rounded-lg logo-glow tracking-wide text-white group-hover:scale-105 transition-transform duration-300 flex-shrink-0">SQL</div>
                <span class="group-hover:text-blue-500 transition-colors">Master</span>
            </a>
            
            <div class="flex items-center gap-4">
                
                <button onclick="toggleTheme()" class="p-2 rounded-xl hover:bg-white/5 transition text-theme-muted hover:text-blue-400 border border-transparent hover:border-white/10" title="Сменить тему">
                    <svg id="header-icon-sun" class="w-5 h-5 hidden" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 3v1m0 16v1m9-9h-1M4 12H3m15.364 6.364l-.707-.707M6.343 6.343l-.707-.707m12.728 0l-.707.707M6.343 17.657l-.707.707M16 12a4 4 0 11-8 0 4 4 0 018 0z"></path></svg>
                    <svg id="header-icon-moon" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20.354 15.354A9 9 0 018.646 3.646 9.003 9.003 0 0012 21a9.003 9.003 0 008.354-5.646z"></path></svg>
                </button>

                <div class="relative">
                    <button onclick="toggleLangMenu(event)" id="lang-btn" class="bg-theme-card px-4 py-2 rounded-xl flex items-center gap-2 text-xs font-bold transition hover:border-blue-500/50 text-theme-main shadow-sm min-w-[100px] justify-between border border-transparent hover:bg-white/5">
                        <span id="current-lang-label">...</span>
                        <svg class="w-3 h-3 opacity-50 pointer-events-none" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7"></path></svg>
                    </button>
                    <div id="lang-menu" class="lang-dropdown absolute right-0 mt-2 w-36 bg-theme-card rounded-xl shadow-2xl p-1 z-[60]">
                        <button onclick="handleLangChange('ru', 'Русский')" class="w-full text-left px-3 py-2.5 rounded-lg text-xs font-bold hover:bg-blue-600 hover:text-white transition text-theme-main">Русский</button>
                        <button onclick="handleLangChange('en', 'English')" class="w-full text-left px-3 py-2.5 rounded-lg text-xs font-bold hover:bg-blue-600 hover:text-white transition text-theme-main">English</button>
                        <button onclick="handleLangChange('es', 'Español')" class="w-full text-left px-3 py-2.5 rounded-lg text-xs font-bold hover:bg-blue-600 hover:text-white transition text-theme-main">Español</button>
                        <button onclick="handleLangChange('zh', '中文')" class="w-full text-left px-3 py-2.5 rounded-lg text-xs font-bold hover:bg-blue-600 hover:text-white transition text-theme-main">中文</button>
                    </div>
                </div>

                <div id="header-auth-block" class="flex items-center pl-4 border-l border-gray-500/20 ml-2 min-w-[100px] justify-end">
                    ${authHtml}
                </div>
            </div>
        </nav>
    `;

    // Синхронизация иконок темы и текущего языка
    const savedTheme = localStorage.getItem('sql_theme') || 'dark';
    const sunIcon = document.getElementById('header-icon-sun');
    const moonIcon = document.getElementById('header-icon-moon');

    if (savedTheme === 'light') {
        sunIcon.classList.remove('hidden');
        moonIcon.classList.add('hidden');
    } else {
        sunIcon.classList.add('hidden');
        moonIcon.classList.remove('hidden');
    }

    const savedLang = localStorage.getItem('sql_lang') || 'ru';
    const labels = { ru: 'Русский', en: 'English', es: 'Español', zh: '中文' };
    const langLabel = document.getElementById('current-lang-label');
    if (langLabel) {
        langLabel.innerText = labels[savedLang] || 'Русский';
    }
}

// --- ГЛОБАЛЬНЫЕ ФУНКЦИИ ДЛЯ ХЕДЕРА ---

window.toggleTheme = function() {
    const body = document.body;
    const currentTheme = body.getAttribute('data-theme');
    const newTheme = currentTheme === 'light' ? 'dark' : 'light';
    body.setAttribute('data-theme', newTheme);
    localStorage.setItem('sql_theme', newTheme);

    const sunIcon = document.getElementById('header-icon-sun');
    const moonIcon = document.getElementById('header-icon-moon');
    if (newTheme === 'light') {
        sunIcon.classList.remove('hidden');
        moonIcon.classList.add('hidden');
    } else {
        sunIcon.classList.add('hidden');
        moonIcon.classList.remove('hidden');
    }

    if (window.cmEditor) {
        window.cmEditor.setOption("theme", newTheme === 'light' ? 'eclipse' : 'dracula');
    }
}

window.toggleLangMenu = function(event) {
    if(event) event.stopPropagation();
    const menu = document.getElementById('lang-menu');
    if (menu) menu.classList.toggle('open');
};

window.handleLangChange = function(lang, label) {
    const menu = document.getElementById('lang-menu');
    if (menu) menu.classList.remove('open');

    if (window.changeLanguage) {
        window.changeLanguage(lang);
    } else {
        localStorage.setItem('sql_lang', lang);
        location.reload();
    }

    const lbl = document.getElementById('current-lang-label');
    if (lbl) lbl.innerText = label;
};

document.addEventListener('click', (e) => {
    const menu = document.getElementById('lang-menu');
    const btn = document.getElementById('lang-btn');
    if (menu && menu.classList.contains('open')) {
        if (!btn.contains(e.target) && !menu.contains(e.target)) {
            menu.classList.remove('open');
        }
    }
});