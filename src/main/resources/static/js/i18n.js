// /js/i18n.js

let currentLang = localStorage.getItem('sql_lang') || 'ru';
let translations = {};

// Оставляем глобальный объект для обратной совместимости
// (Например, чтобы index.html мог брать typing_words отсюда)
window.UI_TRANSLATIONS = {};

/**
 * Асинхронно загружает JSON со словарем
 */
async function loadTranslations(lang) {
    try {
        const response = await fetch(`/locales/${lang}.json`);
        if (!response.ok) throw new Error(`Could not load /locales/${lang}.json`);

        translations = await response.json();
        window.UI_TRANSLATIONS[lang] = translations; // Сохраняем в глобальный объект
    } catch (error) {
        console.warn(`[i18n] Failed to load ${lang} translations, falling back to English.`, error);
        if (lang !== 'en') {
            await loadTranslations('en'); // Фолбэк на английский
        }
    }
}

/**
 * Главная функция инициализации (теперь асинхронная)
 */
window.initTranslations = async function() {
    const langSelect = document.getElementById('lang-select');
    if (langSelect) langSelect.value = currentLang;

    await loadTranslations(currentLang);
    window.applyTranslations();
};

/**
 * Применяет переводы ко всем элементам с атрибутом data-i18n
 */
window.applyTranslations = function() {
    document.querySelectorAll('[data-i18n]').forEach(el => {
        const key = el.getAttribute('data-i18n');
        const translation = translations[key];

        if (translation) {
            if (el.tagName === 'INPUT' || el.tagName === 'TEXTAREA') {
                el.placeholder = translation;
            } else {
                el.innerHTML = translation; // Поддерживаем HTML (например для <br>)
            }
        }
    });

    // Если на странице был зарегистрирован коллбэк для апдейта (например, typing-эффект на главной)
    if (typeof window.onTranslationsLoaded === 'function') {
        window.onTranslationsLoaded();
    }

    // Если мы в тренажере и надо перерендерить задачу
    if (typeof window.renderTask === 'function') window.renderTask();
};

/**
 * Смена языка на лету
 */
window.changeLanguage = async function(lang) {
    if (currentLang === lang) return;

    currentLang = lang;
    localStorage.setItem('sql_lang', lang);

    await loadTranslations(lang);
    window.applyTranslations();
};

/**
 * Хелпер для получения перевода по ключу из JS (например, для SweetAlert)
 */
window.t = function(key) {
    return translations[key] || key;
};

// Запускаем инициализацию при готовности DOM
document.addEventListener('DOMContentLoaded', window.initTranslations);