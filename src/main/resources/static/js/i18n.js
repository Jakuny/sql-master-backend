// i18n.js

const UI_TRANSLATIONS = {
    ru: {
        back_menu: "← Меню",
        
        hero_title_1: "Изучай SQL",
        hero_title_2: "играючи",
        hero_desc: "Полный путь от новичка до профи. Проходи уроки, решай задачи или экспериментируй.",
        btn_lessons: "Уроки",
        btn_practice: "Практика",
        btn_sandbox: "Песочница",
        btn_interview: "Интервью",
        
        task_title: "Задача",
        btn_hint: "Подсказка",
        btn_help: "Справка",
        btn_answer: "Ответ",
        btn_run: "Выполнить",
        btn_reset: "Сброс",
        btn_next: "Дальше",
        
        lbl_result: "Результат",
        lbl_editor: "SQL Редактор",
        msg_correct: "Верно",
        msg_wrong: "Неверно",
        status_error: "Ошибка синтаксиса",
        
        sand_struct: "Структура БД",
        sand_viz: "Схема (ERD)",
        sand_mock: "Демо-данные",
        sand_csv: "Загрузить CSV",
        sand_clear: "Сброс",
        sand_chart: "График",
        sand_dl_csv: "Скачать CSV"
    },
    en: {
        back_menu: "← Menu",
        
        hero_title_1: "Learn SQL",
        hero_title_2: "the fun way",
        hero_desc: "From beginner to pro. Interactive lessons, challenges, and sandbox.",
        btn_lessons: "Lessons",
        btn_practice: "Practice",
        btn_sandbox: "Sandbox",
        btn_interview: "Interview",
        
        task_title: "Task",
        btn_hint: "Hint",
        btn_help: "Cheatsheet",
        btn_answer: "Answer",
        btn_run: "Run",
        btn_reset: "Reset",
        btn_next: "Next",
        
        lbl_result: "Result",
        lbl_editor: "SQL Editor",
        msg_correct: "Correct",
        msg_wrong: "Wrong",
        status_error: "Syntax Error",
        
        sand_struct: "DB Structure",
        sand_viz: "Schema (ERD)",
        sand_mock: "Mock Data",
        sand_csv: "Upload CSV",
        sand_clear: "Reset",
        sand_chart: "Chart",
        sand_dl_csv: "Download CSV"
    },
    es: {
        back_menu: "← Menú",
        hero_title_1: "Aprende SQL",
        hero_title_2: "jugando",
        hero_desc: "De principiante a profesional.",
        btn_lessons: "Lecciones",
        btn_practice: "Práctica",
        btn_sandbox: "Entorno",
        btn_interview: "Entrevista",
        task_title: "Tarea",
        btn_hint: "Pista",
        btn_help: "Ayuda",
        btn_answer: "Solución",
        btn_run: "Ejecutar",
        btn_reset: "Reiniciar",
        btn_next: "Siguiente",
        lbl_result: "Resultado",
        lbl_editor: "Editor SQL",
        msg_correct: "Correcto",
        msg_wrong: "Incorrecto",
        status_error: "Error",
        sand_struct: "Estructura",
        sand_viz: "Esquema",
        sand_mock: "Datos Demo",
        sand_csv: "Subir CSV",
        sand_clear: "Reiniciar",
        sand_chart: "Gráfico",
        sand_dl_csv: "Bajar CSV"
    },
    zh: {
        back_menu: "← 菜单",
        hero_title_1: "学习 SQL",
        hero_title_2: "轻松上手",
        hero_desc: "从入门到精通。",
        btn_lessons: "课程",
        btn_practice: "练习",
        btn_sandbox: "沙盒",
        btn_interview: "面试",
        task_title: "任务",
        btn_hint: "提示",
        btn_help: "备忘单",
        btn_answer: "答案",
        btn_run: "执行",
        btn_reset: "重置",
        btn_next: "下一步",
        lbl_result: "结果",
        lbl_editor: "SQL 编辑器",
        msg_correct: "正确",
        msg_wrong: "错误",
        status_error: "错误",
        sand_struct: "结构",
        sand_viz: "架构图",
        sand_mock: "模拟数据",
        sand_csv: "上传 CSV",
        sand_clear: "重置",
        sand_chart: "图表",
        sand_dl_csv: "下载 CSV"
    }
};

let currentLang = localStorage.getItem('sql_lang') || 'ru';

function initTranslations() {
    const langSelect = document.getElementById('lang-select');
    if (langSelect) langSelect.value = currentLang;
    applyTranslations();
}

function applyTranslations() {
    document.querySelectorAll('[data-i18n]').forEach(el => {
        const key = el.getAttribute('data-i18n');
        if (UI_TRANSLATIONS[currentLang] && UI_TRANSLATIONS[currentLang][key]) {
            el.innerText = UI_TRANSLATIONS[currentLang][key];
        }
    });
    
    // Безопасный вызов обновлений интерфейса
    if (typeof renderTask === 'function') renderTask();
    if (typeof renderSchema === 'function') renderSchema();
}

function changeLanguage(lang) {
    currentLang = lang;
    localStorage.setItem('sql_lang', lang);
    applyTranslations();
}

function t(key) {
    return (UI_TRANSLATIONS[currentLang] && UI_TRANSLATIONS[currentLang][key]) || key;
}

// Автозапуск
document.addEventListener('DOMContentLoaded', () => {
    initTranslations();
});