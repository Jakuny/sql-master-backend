// /js/i18n.js

const UI_TRANSLATIONS = {
    ru: {
        // --- ОБЩЕЕ ---
        back_menu: "← Меню",
        btn_home: "На главную",
        btn_back: "Назад",
        btn_logout: "Выйти",
        btn_login: "Войти",
        btn_register: "Регистрация",
        btn_run: "Выполнить",
        btn_next: "Дальше",
        btn_hint: "Подсказка",
        btn_answer: "Ответ",
        btn_help: "Справка",
        btn_save: "Сохранить",
        btn_cancel: "Отмена",
        btn_reset: "Сброс",
        btn_soon: "Скоро",

        // --- ЛЕЙБЛЫ ---
        lbl_result: "Результат",
        lbl_editor: "SQL Редактор",
        lbl_username: "Никнейм",
        lbl_email: "Email адрес",
        lbl_password: "Пароль",
        lbl_new_nick: "Новый ник",
        lbl_change_avatar: "Сменить аватар",

        // --- ПЛЕЙСХОЛДЕРЫ ---
        ph_username: "Придумайте никнейм",
        ph_email: "example@mail.com",
        ph_password: "••••••••",

        // --- ГЛАВНАЯ (Index) ---
        hero_title_1: "Изучай SQL",
        hero_title_2: "играючи",
        hero_desc: "Полный путь от новичка до профи. Проходи уроки, решай задачи, экспериментируй или готовься к собеседованию.",
        btn_lessons: "Уроки",
        btn_practice: "Практика",
        btn_sandbox: "Песочница",
        btn_interview: "Интервью",

        // --- АВТОРИЗАЦИЯ ---
        auth_welcome: "С возвращением!",
        auth_login_desc: "Введите данные для входа",
        auth_reg_title: "Регистрация",
        auth_reg_desc: "Создайте аккаунт, чтобы сохранять прогресс",
        reg_footer: "Уже есть аккаунт?",
        login_footer: "Нет аккаунта?",
        confirm_logout_title: "Вы уверены?",
        confirm_logout_text: "Вы выйдете из своего аккаунта.",
        confirm_logout_yes: "Да, выйти",
        confirm_logout_no: "Отмена",

        // --- ПРОФИЛЬ ---
        ph_experience: "Опыт",
        ph_level: "Уровень",
        ph_streak: "Стрик (дней)",
        profile_not_found: "Профиль не найден",
        settings_title: "Настройки профиля",
        err_nick_taken: "Этот никнейм уже занят",
        err_file_size: "Файл слишком большой (макс 5MB)",
        msg_saved: "Профиль обновлен",

        // --- ТРЕНАЖЕР ---
        task_title: "Задача",
        msg_correct: "✅ Верно",
        msg_correct_xp: "Отлично! Вам начислено {xp} XP",
        msg_correct_already: "Верно! Вы уже решили эту задачу ранее",
        msg_wrong: "❌ Неверно",
        status_error: "Ошибка",
        empty_state: "Ожидание запроса...",

        // --- ПЕСОЧНИЦА ---
        sand_struct: "Структура БД",
        sand_viz: "Схема (ERD)",
        sand_mock: "Демо-данные",
        sand_csv: "Загрузить CSV",
        sand_clear: "Сброс БД",
        sand_chart: "График",
        sand_dl_csv: "Скачать CSV",
        confirm_mock: "Создать таблицы Users, Products, Orders? Старые будут удалены.",
        confirm_clear: "Удалить все таблицы и очистить редактор?",

        // --- ОШИБКИ ---
        err_404_title: "Страница не найдена",
        err_404_desc: "Похоже, вы запросили несуществующую таблицу или сбились с пути.",
        err_403_title: "Доступ запрещен",
        err_403_desc: "У вас недостаточно прав для просмотра этой страницы.",
        err_500_title: "Система упала",
        err_500_desc: "Произошла внутренняя ошибка сервера. Мы уже чиним."
    },
    en: {
        back_menu: "← Menu",
        btn_home: "Go Home",
        btn_back: "Back",
        btn_logout: "Logout",
        btn_login: "Login",
        btn_register: "Register",
        btn_run: "Run",
        btn_next: "Next",
        btn_hint: "Hint",
        btn_answer: "Answer",
        btn_help: "Cheatsheet",
        btn_save: "Save",
        btn_cancel: "Cancel",
        btn_reset: "Reset",
        btn_soon: "Soon",

        lbl_result: "Result",
        lbl_editor: "SQL Editor",
        lbl_username: "Username",
        lbl_email: "Email address",
        lbl_password: "Password",
        lbl_new_nick: "New Nickname",
        lbl_change_avatar: "Change Avatar",

        ph_username: "Choose a nickname",
        ph_email: "example@mail.com",
        ph_password: "••••••••",

        hero_title_1: "Learn SQL",
        hero_title_2: "the fun way",
        hero_desc: "From beginner to pro. Interactive lessons, challenges, and sandbox.",
        btn_lessons: "Lessons",
        btn_practice: "Practice",
        btn_sandbox: "Sandbox",
        btn_interview: "Interview",

        auth_welcome: "Welcome back!",
        auth_login_desc: "Enter your credentials to login",
        auth_reg_title: "Registration",
        auth_reg_desc: "Create an account to save progress",
        reg_footer: "Already have an account?",
        login_footer: "Don't have an account?",
        confirm_logout_title: "Are you sure?",
        confirm_logout_text: "You will be logged out.",
        confirm_logout_yes: "Yes, logout",
        confirm_logout_no: "Cancel",

        ph_experience: "Experience",
        ph_level: "Rank Level",
        ph_streak: "Streak",
        profile_not_found: "Profile not found",
        settings_title: "Settings",
        err_nick_taken: "Nickname already taken",
        err_file_size: "File too large (max 5MB)",
        msg_saved: "Profile updated",

        task_title: "Task",
        msg_correct: "✅ Correct",
        msg_correct_xp: "Great! You gained {xp} XP",
        msg_correct_already: "Correct! You already solved this",
        msg_wrong: "❌ Wrong",
        status_error: "Error",
        empty_state: "Waiting for query...",

        sand_struct: "DB Structure",
        sand_viz: "Schema (ERD)",
        sand_mock: "Mock Data",
        sand_csv: "Upload CSV",
        sand_clear: "Reset DB",
        sand_chart: "Chart",
        sand_dl_csv: "Download CSV",
        confirm_mock: "Create mock data? Old tables will be deleted.",
        confirm_clear: "Clear everything?",

        err_404_title: "Page Not Found",
        err_404_desc: "Looks like you queried a non-existent table or lost your way.",
        err_403_title: "Access Denied",
        err_403_desc: "You don't have permission to view this page.",
        err_500_title: "System Crash",
        err_500_desc: "Internal server error occurred."
    },
    es: {
        back_menu: "← Menú",
        btn_home: "Inicio",
        btn_back: "Atrás",
        btn_logout: "Salir",
        btn_login: "Entrar",
        btn_register: "Registro",
        btn_run: "Ejecutar",
        btn_next: "Siguiente",
        btn_hint: "Pista",
        btn_answer: "Solución",
        btn_help: "Ayuda",
        btn_save: "Guardar",
        btn_cancel: "Cancelar",
        btn_reset: "Reiniciar",
        btn_soon: "Pronto",

        lbl_result: "Resultado",
        lbl_editor: "Editor SQL",
        lbl_username: "Usuario",
        lbl_email: "Correo",
        lbl_password: "Clave",

        hero_title_1: "Aprende SQL",
        hero_title_2: "jugando",
        hero_desc: "De principiante a profesional.",
        btn_lessons: "Lecciones",
        btn_practice: "Práctica",
        btn_sandbox: "Entorno",
        btn_interview: "Entrevista",

        auth_welcome: "¡Bienvenido!",
        auth_login_desc: "Ingrese sus datos",
        auth_reg_title: "Registro",
        auth_reg_desc: "Crea una cuenta",

        task_title: "Tarea",
        msg_correct: "✅ Correcto",
        msg_correct_xp: "¡Bien! Ganaste {xp} XP",
        msg_correct_already: "¡Ya resuelto!",
        msg_wrong: "❌ Incorrecto",
        empty_state: "Esperando...",

        sand_struct: "Estructura",
        sand_viz: "Esquema",
        sand_mock: "Datos Demo",
        sand_csv: "Subir CSV",
        sand_clear: "Limpiar",
        sand_chart: "Gráfico",
        sand_dl_csv: "Bajar CSV",
        confirm_mock: "¿Crear datos de prueba?",
        confirm_clear: "¿Borrar todo?",

        ph_experience: "Experiencia",
        ph_level: "Nivel",
        ph_streak: "Racha",

        err_404_title: "No encontrado",
        err_404_desc: "Página no existe.",
        err_500_title: "Error del sistema",
        err_500_desc: "Error interno del servidor."
    },
    zh: {
        back_menu: "← 菜单",
        btn_home: "首页",
        btn_back: "返回",
        btn_logout: "登出",
        btn_login: "登录",
        btn_register: "注册",
        btn_run: "执行",
        btn_next: "下一步",
        btn_hint: "提示",
        btn_answer: "答案",
        btn_help: "帮助",
        btn_save: "保存",
        btn_cancel: "取消",
        btn_reset: "重置",
        btn_soon: "即将推出",

        lbl_result: "结果",
        lbl_editor: "SQL 编辑器",
        lbl_username: "用户名",
        lbl_email: "邮箱",
        lbl_password: "密码",

        hero_title_1: "学习 SQL",
        hero_title_2: "轻松上手",
        hero_desc: "从入门到精通。",
        btn_lessons: "课程",
        btn_practice: "练习",
        btn_sandbox: "沙盒",
        btn_interview: "面试",

        auth_welcome: "欢迎回来",
        auth_login_desc: "请登录",
        auth_reg_title: "注册",
        auth_reg_desc: "创建帐户",

        task_title: "任务",
        msg_correct: "✅ 正确",
        msg_correct_xp: "太棒了！获得 {xp} XP",
        msg_correct_already: "已解决！",
        msg_wrong: "❌ 错误",
        empty_state: "等待...",

        sand_struct: "数据库结构",
        sand_viz: "架构图",
        sand_mock: "模拟数据",
        sand_csv: "上传 CSV",
        sand_clear: "清空",
        sand_chart: "图表",
        sand_dl_csv: "下载 CSV",
        confirm_mock: "创建测试数据？",
        confirm_clear: "清空所有？",

        ph_experience: "经验",
        ph_level: "等级",
        ph_streak: "连胜",

        err_404_title: "未找到页面",
        err_404_desc: "页面不存在。",
        err_500_title: "系统崩溃",
        err_500_desc: "服务器内部错误。"
    }
};

let currentLang = localStorage.getItem('sql_lang') || 'ru';

function initTranslations() {
    const langSelect = document.getElementById('lang-select');
    if (langSelect) langSelect.value = currentLang;
    applyTranslations();
}

function applyTranslations() {
    // 1. Обновляем элементы с data-i18n
    document.querySelectorAll('[data-i18n]').forEach(el => {
        const key = el.getAttribute('data-i18n');
        const translation = UI_TRANSLATIONS[currentLang][key] || UI_TRANSLATIONS['en'][key];

        if (translation) {
            // Если это input/textarea, меняем placeholder
            if (el.tagName === 'INPUT' || el.tagName === 'TEXTAREA') {
                el.placeholder = translation;
            } else {
                // Иначе меняем текст
                el.innerText = translation;
            }
        }
    });

    // 2. Обновляем динамические страницы (Тренажер, Уроки)
    if (typeof window.renderTask === 'function') {
        window.renderTask();
    }

    // 3. Обновляем лейбл языка в кастомном меню
    const label = document.getElementById('current-lang-label');
    if (label) {
        const names = { ru: 'Русский', en: 'English', es: 'Español', zh: '中文' };
        label.innerText = names[currentLang];
    }
}

// Глобальная функция смены языка (вызывается из HTML)
window.changeLanguage = function(lang) {
    currentLang = lang;
    localStorage.setItem('sql_lang', lang);
    applyTranslations();
}

// Глобальная функция получения текста (для JS алертов)
window.t = function(key) {
    return (UI_TRANSLATIONS[currentLang] && UI_TRANSLATIONS[currentLang][key]) ||
        (UI_TRANSLATIONS['en'][key]) || key;
}

// Автозапуск
document.addEventListener('DOMContentLoaded', initTranslations);