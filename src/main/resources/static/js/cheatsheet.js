// cheatsheet.js

// --- 1. Настройки автодополнения (IntelliSense) ---
// Если добавишь новые таблицы в tasks.js, добавь их и сюда, чтобы редактор их подсказывал.
window.SQL_AUTOCOMPLETE_CONFIG = {
    tables: {
        "Products": ["id", "name", "price", "category"],
        "Orders": ["id", "product_id", "quantity", "order_date"]
    }
};

// --- 2. Контент Шпаргалки ---
// Мы храним HTML в переменной, чтобы легко править текст в одном месте.
window.SQL_CHEATSHEET_HTML = `
    <!-- Блок: SELECT -->
    <div class="space-y-2 mb-6">
        <h4 class="text-blue-400 font-bold uppercase text-xs tracking-wider border-b border-slate-700 pb-1">1. Выборка данных (SELECT)</h4>
        <div class="text-slate-300 space-y-2">
            <p>Выбрать все столбцы:</p>
            <div class="bg-slate-800 p-3 rounded border border-slate-700/50 font-mono text-sm">
                <span class="text-purple-400">SELECT</span> * <span class="text-purple-400">FROM</span> Products;
            </div>
            <p>Выбрать конкретные столбцы:</p>
            <div class="bg-slate-800 p-3 rounded border border-slate-700/50 font-mono text-sm">
                <span class="text-purple-400">SELECT</span> name, price <span class="text-purple-400">FROM</span> Products;
            </div>
        </div>
    </div>

    <!-- Блок: WHERE -->
    <div class="space-y-2 mb-6">
        <h4 class="text-blue-400 font-bold uppercase text-xs tracking-wider border-b border-slate-700 pb-1">2. Фильтрация (WHERE)</h4>
        <div class="text-slate-300 space-y-2">
            <p>Операторы сравнения: <code>=</code>, <code>></code>, <code><</code>, <code>>=</code>, <code><=</code>, <code><></code> (не равно).</p>
            <div class="bg-slate-800 p-3 rounded border border-slate-700/50 font-mono text-sm">
                <span class="text-purple-400">SELECT</span> * <span class="text-purple-400">FROM</span> Products <br>
                <span class="text-purple-400">WHERE</span> price > 1000 <span class="text-purple-400">AND</span> category = 'Electronics';
            </div>
            <p>Поиск по шаблону (LIKE):</p>
            <div class="bg-slate-800 p-3 rounded border border-slate-700/50 font-mono text-sm">
                <span class="text-gray-500">-- Названия, начинающиеся на "iPhone"</span><br>
                <span class="text-purple-400">WHERE</span> name <span class="text-purple-400">LIKE</span> 'iPhone%';
            </div>
        </div>
    </div>

    <!-- Блок: ORDER BY -->
    <div class="space-y-2 mb-6">
        <h4 class="text-blue-400 font-bold uppercase text-xs tracking-wider border-b border-slate-700 pb-1">3. Сортировка (ORDER BY)</h4>
        <div class="text-slate-300 space-y-2">
            <div class="bg-slate-800 p-3 rounded border border-slate-700/50 font-mono text-sm">
                <span class="text-gray-500">-- ASC (по возрастанию, по умолчанию)</span><br>
                <span class="text-purple-400">ORDER BY</span> price <span class="text-purple-400">ASC</span>;<br><br>
                <span class="text-gray-500">-- DESC (по убыванию)</span><br>
                <span class="text-purple-400">ORDER BY</span> price <span class="text-purple-400">DESC</span>;
            </div>
        </div>
    </div>

    <!-- Блок: AGGREGATION -->
    <div class="space-y-2 mb-6">
        <h4 class="text-blue-400 font-bold uppercase text-xs tracking-wider border-b border-slate-700 pb-1">4. Группировка и Агрегация</h4>
        <div class="text-slate-300 space-y-2">
            <p>Функции: <code>COUNT(*)</code>, <code>SUM(column)</code>, <code>AVG(column)</code>, <code>MIN/MAX</code>.</p>
            <div class="bg-slate-800 p-3 rounded border border-slate-700/50 font-mono text-sm">
                <span class="text-purple-400">SELECT</span> category, <span class="text-yellow-400">COUNT</span>(*) as total_items <br>
                <span class="text-purple-400">FROM</span> Products <br>
                <span class="text-purple-400">GROUP BY</span> category;
            </div>
            <p class="text-xs text-yellow-500">Важно: Все столбцы в SELECT, кроме функций агрегации, должны быть указаны в GROUP BY.</p>
        </div>
    </div>

    <!-- Блок: JOIN -->
    <div class="space-y-2 mb-6">
        <h4 class="text-blue-400 font-bold uppercase text-xs tracking-wider border-b border-slate-700 pb-1">5. Объединение таблиц (JOIN)</h4>
        <div class="text-slate-300 space-y-2">
            <p>Позволяет брать данные из двух таблиц, связанных ключом (id).</p>
            <div class="bg-slate-800 p-3 rounded border border-slate-700/50 font-mono text-sm">
                <span class="text-purple-400">SELECT</span> Orders.id, Products.name <br>
                <span class="text-purple-400">FROM</span> Orders <br>
                <span class="text-purple-400">JOIN</span> Products <span class="text-purple-400">ON</span> Orders.product_id = Products.id;
            </div>
        </div>
    </div>
`;