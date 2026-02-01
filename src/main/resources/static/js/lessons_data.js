// lessons_data.js

window.COURSES = [
    {
        id: 1,
        title: "Основы: SELECT",
        description: "Учимся выбирать данные и использовать калькулятор SQL.",
        initSql: [
            "CREATE TABLE Cities (name STRING, population INT)",
            "INSERT INTO Cities VALUES ('Moscow', 12000000), ('Paris', 2100000), ('London', 9000000)"
        ],
        theory: `
            <h3 class="text-xl font-bold text-white mb-4">Привет, будущий мастер!</h3>
            <p class="mb-4">Самая главная команда в SQL — это <code>SELECT</code>. Она говорит базе данных: "Выбери мне это".</p>
            
            <p class="mb-2">1. <b>Простой выбор:</b> Можно использовать SQL как калькулятор:</p>
            <pre class="bg-slate-800 p-2 rounded mb-4 text-green-300">SELECT 2 + 2</pre>
            
            <p class="mb-2">2. <b>Выбор текста:</b> Текст всегда пишется в одинарных кавычках:</p>
            <pre class="bg-slate-800 p-2 rounded mb-4 text-green-300">SELECT 'Привет SQL'</pre>

            <p class="mb-2">3. <b>Выбор из таблицы:</b> Чтобы взять данные, нужно указать столбцы и таблицу:</p>
            <pre class="bg-slate-800 p-2 rounded mb-4 text-green-300">SELECT name FROM Cities</pre>
        `,
        tasks: [
            {
                desc: "Попробуем SQL как калькулятор. Напишите запрос, который выведет число <b>100</b> (например <code>SELECT 50 + 50</code>).",
                checkSql: "SELECT 100",
                default: "SELECT 10 + ..."
            },
            {
                desc: "Теперь работаем с текстом. Выведите фразу <b>'SQL is cool'</b>. Не забудьте одинарные кавычки!",
                checkSql: "SELECT 'SQL is cool'",
                default: "SELECT ..."
            },
            {
                desc: "А теперь обратимся к таблице <code>Cities</code>. Выведите столбец <code>name</code> из этой таблицы.",
                checkSql: "SELECT name FROM Cities",
                default: "SELECT ... FROM Cities"
            }
        ]
    },
    {
        id: 2,
        title: "Фильтрация: WHERE",
        description: "Учимся выбирать только то, что нам нужно.",
        initSql: [
            "CREATE TABLE Products (product_id INT, product_name VARCHAR, price DECIMAL, category VARCHAR)",
            "INSERT INTO Products VALUES (1, 'Ноутбук Lenovo', 55000.99, 'Электроника'), (2, 'Мышка беспроводная', 2999.50, 'Электроника'), (3, 'SQL для начинающих', 1200.00, 'Книги'), (4, 'Футболка Python', 2499.00, 'Одежда'), (5, 'Кофеварка', 8900.00, 'Техника'), (6, 'Книга по алгоритмам', 1800.00, 'Книги'), (7, 'Наушники', 4500.00, 'Электроника')",
            "CREATE TABLE Customers (customer_id INT, customer_name VARCHAR, city VARCHAR)",
            "INSERT INTO Customers VALUES (1, 'Иван Петров', 'Москва'), (2, 'Анна Сидорова', 'Москва'), (3, 'Петр Иванов', 'Санкт-Петербург'), (4, 'Мария Кузнецова', 'Казань'), (5, 'Алексей Смирнов', 'Москва')"
        ],
        theory: `
            <h3 class="text-xl font-bold text-white mb-4">Фильтруем по условию</h3>
            <p class="mb-4">Часто нам нужны не все данные, а только конкретные. Для этого есть команда <code>WHERE</code> (ГДЕ). Она работает как фильтр — оставляет только те строки, которые соответствуют условию.</p>
            
            <p class="mb-2">Основные операторы сравнения:</p>
            <ul class="list-disc pl-5 space-y-1 text-slate-400 mb-4">
                <li><code>=</code> Равно (например: <code>WHERE city = 'Москва'</code>)</li>
                <li><code>></code> Больше (<code>WHERE price > 5000</code>)</li>
                <li><code><</code> Меньше</li>
                <li><code>>=</code> Больше или равно</li>
                <li><code><=</code> Меньше или равно</li>
                <li><code><></code> или <code>!=</code> Не равно</li>
            </ul>
            
            <p class="mb-2">Пример: найдем все электронные товары:</p>
            <pre class="bg-slate-800 p-2 rounded mb-4 text-green-300">SELECT * FROM Products WHERE category = 'Электроника'</pre>
            
            <p class="mb-2"><b>Интерактивный пример:</b> Попробуй запустить запрос, который ищет товары дороже 3000 рублей ИЛИ из категории 'Книги':</p>
            <pre class="bg-slate-800 p-2 rounded mb-4 text-green-300">SELECT product_name, price, category FROM Products WHERE price > 3000 OR category = 'Книги'</pre>
            <p class="text-sm text-slate-400">Этот запрос вернет две группы: дорогие товары (любой категории) и все книги (даже дешевые).</p>
        `,
        tasks: [
            {
                desc: "Найди все товары категории <b>'Электроника'</b> в таблице <code>Products</code>.",
                checkSql: "SELECT * FROM Products WHERE category = 'Электроника'",
                default: "SELECT * FROM Products WHERE ..."
            },
            {
                desc: "Найди товары дешевле <b>3000 рублей</b>.",
                checkSql: "SELECT * FROM Products WHERE price < 3000",
                default: "SELECT * FROM Products WHERE ..."
            },
            {
                desc: "Найди всех покупателей из <b>Москвы</b> в таблице <code>Customers</code>.",
                checkSql: "SELECT * FROM Customers WHERE city = 'Москва'",
                default: "SELECT * FROM Customers WHERE ..."
            }
        ]
      
      
    },
    // Урок 3: Сортировка — ORDER BY
{
    id: 3,
    title: "Сортировка: ORDER BY",
    description: "Учимся упорядочивать данные по нужному правилу.",
    initSql: [
        "CREATE TABLE Products (product_id INT, product_name VARCHAR, price DECIMAL, category VARCHAR, rating FLOAT)",
        "INSERT INTO Products VALUES (1, 'Ноутбук Lenovo', 55000.99, 'Электроника', 4.5), (2, 'Мышка беспроводная', 2999.50, 'Электроника', 4.2), (3, 'SQL для начинающих', 1200.00, 'Книги', 4.8), (4, 'Футболка Python', 2499.00, 'Одежда', 4.0), (5, 'Кофеварка', 8900.00, 'Техника', 4.1), (6, 'Книга по алгоритмам', 1800.00, 'Книги', 4.9), (7, 'Наушники', 4500.00, 'Электроника', 4.3), (8, 'Монитор 24\"', 32000.00, 'Электроника', 4.6)",
        "CREATE TABLE Customers (customer_id INT, customer_name VARCHAR, city VARCHAR, total_orders INT)",
        "INSERT INTO Customers VALUES (1, 'Иван Петров', 'Москва', 15), (2, 'Анна Сидорова', 'Москва', 8), (3, 'Петр Иванов', 'Санкт-Петербург', 22), (4, 'Мария Кузнецова', 'Казань', 5), (5, 'Алексей Смирнов', 'Москва', 12), (6, 'Екатерина Волкова', 'Новосибирск', 3), (7, 'Дмитрий Попов', 'Москва', 18)"
    ],
    theory: `
        <h3 class="text-xl font-bold text-white mb-4">Порядок имеет значение!</h3>
        <p class="mb-4">Когда данные выводятся без сортировки, они могут быть в любом порядке. SQL позволяет нам контролировать этот порядок с помощью команды <code>ORDER BY</code> (отсортировать по).</p>
        
        <p class="mb-2">Основные правила сортировки:</p>
        <ul class="list-disc pl-5 space-y-1 text-slate-400 mb-4">
            <li><b>ASC</b> (ascending) — по возрастанию (от А до Я, от 0 до 9). Используется по умолчанию.</li>
            <li><b>DESC</b> (descending) — по убыванию (от Я до А, от 9 до 0). Нужно указывать явно.</li>
            <li>Можно сортировать по нескольким столбцам сразу!</li>
        </ul>
        
        <p class="mb-2">Пример 1: Товары от дешевых к дорогим:</p>
        <pre class="bg-slate-800 p-2 rounded mb-4 text-green-300">SELECT product_name, price FROM Products ORDER BY price ASC</pre>
        
        <p class="mb-2">Пример 2: Покупатели по имени в обратном порядке (Я→А):</p>
        <pre class="bg-slate-800 p-2 rounded mb-4 text-green-300">SELECT customer_name FROM Customers ORDER BY customer_name DESC</pre>
        
        <p class="mb-2"><b>Интерактивный пример:</b> Запусти запрос, который сортирует товары сначала по категории (А→Я), а внутри категории — по рейтингу (от высокого к низкому):</p>
        <pre class="bg-slate-800 p-2 rounded mb-4 text-green-300">SELECT product_name, category, rating FROM Products ORDER BY category ASC, rating DESC</pre>
        <p class="text-sm text-slate-400">Обрати внимание: у категории <code>ASC</code> (по умолчанию), у рейтинга — <code>DESC</code>. Это покажет лучшие товары в начале каждой категории!</p>
    `,
    tasks: [
        {
            desc: "Выведи список товаров из таблицы <code>Products</code>, отсортированный по цене <b>от дорогих к дешевым</b>.",
            checkSql: "SELECT * FROM Products ORDER BY price DESC",
            default: "SELECT * FROM Products ORDER BY ..."
        },
        {
            desc: "Выведи имена покупателей из таблицы <code>Customers</code> в <b>алфавитном порядке</b> (А→Я).",
            checkSql: "SELECT customer_name FROM Customers ORDER BY customer_name ASC",
            default: "SELECT customer_name FROM Customers ORDER BY ..."
        },
        {
            desc: "Найди всех покупателей из Москвы и отсортируй их по количеству заказов <b>в порядке убывания</b> (у кого больше заказов — тот выше).",
            checkSql: "SELECT * FROM Customers WHERE city = 'Москва' ORDER BY total_orders DESC",
            default: "SELECT * FROM Customers WHERE ... ORDER BY ..."
        }
    ]
},
// Урок 4: Логические операторы — AND, OR, NOT
{
    id: 4,
    title: "Комбинирование условий",
    description: "Учимся составлять сложные условия фильтрации.",
    initSql: [
        "CREATE TABLE Products (product_id INT, product_name VARCHAR, price DECIMAL, category VARCHAR, in_stock BOOLEAN)",
        "INSERT INTO Products VALUES (1, 'Ноутбук Lenovo', 55000.99, 'Электроника', true), (2, 'Мышка беспроводная', 2999.50, 'Электроника', false), (3, 'SQL для начинающих', 1200.00, 'Книги', true), (4, 'Футболка Python', 2499.00, 'Одежда', true), (5, 'Кофеварка', 8900.00, 'Техника', true), (6, 'Книга по алгоритмам', 1800.00, 'Книги', false), (7, 'Наушники', 4500.00, 'Электроника', true), (8, 'Монитор 24\"', 32000.00, 'Электроника', true), (9, 'Чайник', 2500.00, 'Техника', false), (10, 'Кружка SQL', 800.00, 'Одежда', true)",
        "CREATE TABLE Customers (customer_id INT, customer_name VARCHAR, city VARCHAR, age INT, is_vip BOOLEAN)",
        "INSERT INTO Customers VALUES (1, 'Иван Петров', 'Москва', 28, true), (2, 'Анна Сидорова', 'Москва', 22, false), (3, 'Петр Иванов', 'Санкт-Петербург', 35, true), (4, 'Мария Кузнецова', 'Казань', 19, false), (5, 'Алексей Смирнов', 'Москва', 41, true), (6, 'Екатерина Волкова', 'Новосибирск', 31, false), (7, 'Дмитрий Попов', 'Москва', 24, false), (8, 'Ольга Новикова', 'Москва', 29, true)"
    ],
    theory: `
        <h3 class="text-xl font-bold text-white mb-4">Собираем условия в цепочку</h3>
        <p class="mb-4">Часто одного условия в <code>WHERE</code> недостаточно. Мы хотим найти товары, которые <b>и</b> дешевые, <b>и</b> в наличии, <b>или</b> клиентов, которые живут в Москве <b>и</b> являются VIP. Для этого используем логические операторы.</p>
        
        <p class="mb-2">Три основных оператора:</p>
        <ul class="list-disc pl-5 space-y-1 text-slate-400 mb-4">
            <li><code>AND</code> (И) — оба условия должны быть верны</li>
            <li><code>OR</code> (ИЛИ) — хотя бы одно условие должно быть верно</li>
            <li><code>NOT</code> (НЕ) — инвертирует условие</li>
        </ul>
        
        <p class="mb-2">Пример 1: Товары электроники, которые есть в наличии:</p>
        <pre class="bg-slate-800 p-2 rounded mb-4 text-green-300">SELECT * FROM Products WHERE category = 'Электроника' AND in_stock = true</pre>
        
        <p class="mb-2">Пример 2: Клиенты из Москвы ИЛИ VIP-клиенты:</p>
        <pre class="bg-slate-800 p-2 rounded mb-4 text-green-300">SELECT * FROM Customers WHERE city = 'Москва' OR is_vip = true</pre>
        
        <p class="mb-2">Пример 3: Товары, которые НЕ из категории "Одежда":</p>
        <pre class="bg-slate-800 p-2 rounded mb-4 text-green-300">SELECT * FROM Products WHERE NOT category = 'Одежда'</pre>
        
        <p class="mb-2"><b>Важно!</b> Операторы можно комбинировать, используя скобки для определения порядка:</p>
        <pre class="bg-slate-800 p-2 rounded mb-4 text-green-300">SELECT * FROM Products 
WHERE (category = 'Электроника' OR category = 'Техника') 
  AND price < 10000 
  AND in_stock = true</pre>
        
        <p class="mb-2"><b>Интерактивный пример:</b> Запусти запрос, который найдет VIP-клиентов из Москвы ИЛИ всех клиентов старше 30 лет:</p>
        <pre class="bg-slate-800 p-2 rounded mb-4 text-green-300">SELECT customer_name, city, age, is_vip 
FROM Customers 
WHERE (city = 'Москва' AND is_vip = true) OR age > 30</pre>
        <p class="text-sm text-slate-400">Обрати внимание на скобки! Они важны для правильной логики. Без них условие бы читалось как: "Москва И (VIP ИЛИ возраст > 30)"</p>
    `,
    tasks: [
        {
            desc: "Найди товары из категории <b>'Книги'</b>, которые <b>есть в наличии</b>.",
            checkSql: "SELECT * FROM Products WHERE category = 'Книги' AND in_stock = true",
            default: "SELECT * FROM Products WHERE category = ... AND ..."
        },
        {
            desc: "Найди всех клиентов, которые <b>не из Москвы</b>.",
            checkSql: "SELECT * FROM Customers WHERE NOT city = 'Москва'",
            default: "SELECT * FROM Customers WHERE NOT ..."
        },
        {
            desc: "Найди товары, которые <b>стоят меньше 5000 рублей</b> И <b>есть в наличии</b> ИЛИ товары из категории <b>'Одежда'</b>.",
            checkSql: "SELECT * FROM Products WHERE (price < 5000 AND in_stock = true) OR category = 'Одежда'",
            default: "SELECT * FROM Products WHERE (price < ... AND ...) OR ..."
        }
    ]
},
// Урок 5: Поиск по шаблону — LIKE
{
    id: 5,
    title: "Поиск по шаблону: LIKE",
    description: "Учимся искать данные по частичному совпадению текста.",
    initSql: [
        "CREATE TABLE Products (product_id INT, product_name VARCHAR, price DECIMAL, category VARCHAR)",
        "INSERT INTO Products VALUES (1, 'SQL для начинающих', 1200.00, 'Книги'), (2, 'Книга по Python', 1500.00, 'Книги'), (3, 'Книга по алгоритмам', 1800.00, 'Книги'), (4, 'Ноутбук Lenovo', 55000.99, 'Электроника'), (5, 'Мышь Logitech MX Master', 6999.99, 'Электроника'), (6, 'Клавиатура механическая', 4500.00, 'Электроника'), (7, 'Наушники Sony WH-1000XM4', 28900.00, 'Аудио'), (8, 'Наушники AirPods Pro', 22900.00, 'Аудио'), (9, 'Кофеварка автоматическая', 18900.00, 'Техника'), (10, 'Смартфон iPhone 15 Pro', 120000.00, 'Электроника'), (11, 'Смартфон Samsung Galaxy S24', 95000.00, 'Электроника'), (12, 'Коврик для мыши большой', 1499.00, 'Аксессуары'), (13, 'Монитор 27 дюймов 4K', 45000.00, 'Электроника')",
        "CREATE TABLE Customers (customer_id INT, customer_name VARCHAR, email VARCHAR, phone VARCHAR)",
        "INSERT INTO Customers VALUES (1, 'Иван Петров', 'ivan.petrov@mail.ru', '+79161234567'), (2, 'Анна Сидорова', 'anna.sidorova@gmail.com', '+79262345678'), (3, 'Петр Иванов', 'petr.ivanov@yandex.ru', '+79373456789'), (4, 'Мария Кузнецова', 'maria.kuznetsova@mail.ru', '+79484567890'), (5, 'Алексей Смирнов', 'alex.smirnov@gmail.com', '+79595678901'), (6, 'Екатерина Волкова', 'ekaterina.volkova@yandex.ru', '+79606789012'), (7, 'Дмитрий Попов', 'dmitry.popov@gmail.com', '+79717890123'), (8, 'Ольга Новикова', 'olga.novikova@mail.ru', '+79828901234'), (9, 'Сергей Козлов', 'sergey.kozlov@gmail.com', '+79939012345')"
    ],
    theory: `
        <h3 class="text-xl font-bold text-white mb-4">Поиск по части текста</h3>
        <p class="mb-4">Иногда мы не знаем точное значение для поиска, или хотим найти все записи, содержащие определенную часть текста. Для этого используется оператор <code>LIKE</code> (похоже на) вместе с символами-шаблонами.</p>
        
        <p class="mb-2">Специальные символы для шаблонов:</p>
        <ul class="list-disc pl-5 space-y-1 text-slate-400 mb-4">
            <li><code>%</code> (процент) — заменяет <b>любое количество любых символов</b> (включая ноль символов)</li>
            <li><code>_</code> (нижнее подчеркивание) — заменяет <b>ровно один любой символ</b></li>
            <li><code>NOT LIKE</code> — находит строки, которые <b>не соответствуют</b> шаблону</li>
        </ul>
        
        <p class="mb-2">Примеры использования:</p>
        <ul class="list-disc pl-5 space-y-1 text-slate-400 mb-4">
            <li><code>WHERE product_name LIKE '%SQL%'</code> — название содержит "SQL" в любой части</li>
            <li><code>WHERE customer_name LIKE 'А%'</code> — имя начинается с буквы "А"</li>
            <li><code>WHERE email LIKE '%@gmail.com'</code> — email заканчивается на "@gmail.com"</li>
            <li><code>WHERE phone LIKE '+7916%'</code> — телефон начинается с "+7916"</li>
            <li><code>WHERE product_name LIKE 'Наушники%'</code> — название начинается с "Наушники"</li>
            <li><code>WHERE customer_name LIKE '_ван%'</code> — имя, где вторая буква "в", третья "а", четвертая "н"</li>
        </ul>
        
        <p class="mb-2"><b>Интерактивный пример:</b> Запусти запрос, который найдет всех покупателей, у которых email заканчивается на <code>@gmail.com</code>:</p>
        <pre class="bg-slate-800 p-2 rounded mb-4 text-green-300">SELECT customer_name, email 
FROM Customers 
WHERE email LIKE '%@gmail.com'</pre>
        <p class="text-sm text-slate-400">Символ <code>%</code> перед <code>@gmail.com</code> означает "любое количество любых символов перед @gmail.com". Это позволяет находить все gmail-адреса независимо от имени пользователя.</p>
        
        <p class="mb-2 text-yellow-300"><b>💡 Подсказка:</b> Оператор <code>LIKE</code> чувствителен к регистру в некоторых базах данных. В AlaSQL регистр не учитывается для латинских букв.</p>
    `,
    tasks: [
        {
            desc: "Найди все товары, название которых содержит слово <b>'Наушники'</b>.",
            checkSql: "SELECT * FROM Products WHERE product_name LIKE '%Наушники%'",
            default: "SELECT * FROM Products WHERE product_name LIKE ..."
        },
        {
            desc: "Найди всех покупателей, у которых имя начинается с буквы <b>'А'</b>.",
            checkSql: "SELECT * FROM Customers WHERE customer_name LIKE 'А%'",
            default: "SELECT * FROM Customers WHERE customer_name LIKE ..."
        },
        {
            desc: "Найди все товары категории <b>'Электроника'</b>, название которых содержит <b>'смартфон'</b> (в любом регистре).",
            checkSql: "SELECT * FROM Products WHERE category = 'Электроника' AND LOWER(product_name) LIKE '%смартфон%'",
            default: "SELECT * FROM Products WHERE category = ... AND product_name LIKE ..."
        }
    ]
}
];