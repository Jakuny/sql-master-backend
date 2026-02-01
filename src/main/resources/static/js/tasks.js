// tasks.js

// 1. Инициализация БД (Таблицы)
window.INIT_DB_SQL = [
    "CREATE TABLE Products (id INT, name STRING, price INT, category STRING)",
    "INSERT INTO Products VALUES (1, 'iPhone 13', 999, 'Electronics'), (2, 'Samsung S21', 899, 'Electronics'), (3, 'MacBook Air', 1200, 'Computers'), (4, 'Coffee Mug', 15, 'Home'), (5, 'T-Shirt', 25, 'Clothing')",
    "CREATE TABLE Orders (id INT, product_id INT, quantity INT, order_date DATE)",
    "INSERT INTO Orders VALUES (101, 1, 2, '2023-01-10'), (102, 3, 1, '2023-01-12'), (103, 5, 5, '2023-01-15'), (104, 1, 1, '2023-02-01')"
];

// 2. Список задач с ПЕРЕВОДАМИ
window.SQL_TASKS = [
    {
        id: 1,
        desc: {
            ru: "Выведите все столбцы из таблицы <code>Products</code>.",
            en: "Select all columns from the <code>Products</code> table.",
            es: "Seleccione todas las columnas de la tabla <code>Products</code>.",
            zh: "从 <code>Products</code> 表中选择所有列。"
        },
        hint: {
            ru: "Используйте символ * (звездочка).",
            en: "Use the * (asterisk) symbol.",
            es: "Usa el símbolo *.",
            zh: "使用 * 符号。"
        },
        checkSql: "SELECT * FROM Products",
        default: "SELECT * FROM Products"
    },
    {
        id: 2,
        desc: {
            ru: "Найдите названия (name) и цены товаров из категории 'Electronics'.",
            en: "Find names and prices of products from 'Electronics' category.",
            es: "Encuentra nombres y precios de la categoría 'Electronics'.",
            zh: "查找 'Electronics' 类别的产品名称和价格。"
        },
        hint: {
            ru: "WHERE category = 'Electronics'",
            en: "WHERE category = 'Electronics'",
            es: "WHERE category = 'Electronics'",
            zh: "WHERE category = 'Electronics'"
        },
        checkSql: "SELECT name, price FROM Products WHERE category = 'Electronics'",
        default: "SELECT name, price FROM Products"
    },
    {
        id: 3,
        desc: {
            ru: "Найдите товары дороже 100 долларов. Отсортируйте по цене (убывание).",
            en: "Find products more expensive than 100. Sort by price descending.",
            es: "Productos > 100. Ordenar por precio descendente.",
            zh: "价格 > 100。按价格降序排列。"
        },
        hint: {
            ru: "ORDER BY price DESC",
            en: "ORDER BY price DESC",
            es: "ORDER BY price DESC",
            zh: "ORDER BY price DESC"
        },
        checkSql: "SELECT name, price FROM Products WHERE price > 100 ORDER BY price DESC",
        default: "SELECT name, price FROM Products WHERE ..."
    },
    {
        id: 4,
        desc: {
            ru: "Посчитайте количество товаров в каждой категории (count).",
            en: "Count the number of products in each category.",
            es: "Cuente el número de productos en cada categoría.",
            zh: "计算每个类别中的产品数量。"
        },
        hint: {
            ru: "GROUP BY category",
            en: "GROUP BY category",
            es: "GROUP BY category",
            zh: "GROUP BY category"
        },
        checkSql: "SELECT category, COUNT(*) as count FROM Products GROUP BY category",
        default: "SELECT category, ... FROM Products ..."
    },
    {
        id: 5,
        desc: {
            ru: "Используя JOIN, выведите ID заказа и имя товара.",
            en: "Using JOIN, show Order ID and Product Name.",
            es: "Usando JOIN, muestre ID del pedido y nombre del producto.",
            zh: "使用 JOIN 显示订单 ID 和产品名称。"
        },
        hint: {
            ru: "JOIN Products ON ...",
            en: "JOIN Products ON ...",
            es: "JOIN Products ON ...",
            zh: "JOIN Products ON ..."
        },
        checkSql: "SELECT Orders.id, Products.name FROM Orders JOIN Products ON Orders.product_id = Products.id",
        default: "SELECT ... FROM Orders JOIN Products ON ..."
    },
    {
  "id": 6,
  "desc": {
    "ru": "Найдите сотрудников, которые соответствуют одному из двух условий:<br>1) Из отдела 'IT' с зарплатой выше 80000 и фамилией, оканчивающейся на 'ов' (поле full_name).<br>2) Из отдела 'Sales' с зарплатой от 40000 до 55000 включительно.<br>Выведите все столбцы.",
    "en": "Find employees who meet one of two conditions:<br>1) From the 'IT' department with salary above 80000 and last name ending with 'ov' (full_name field).<br>2) From the 'Sales' department with salary between 40000 and 55000 inclusive.<br>Return all columns.",
    "es": "Encuentre empleados que cumplan una de dos condiciones:<br>1) Del departamento 'IT' con salario superior a 80000 y apellido terminado en 'ov' (campo full_name).<br>2) Del departamento 'Sales' con salario entre 40000 y 55000 inclusive.<br>Devuelva todas las columnas.",
    "zh": "查找满足以下两个条件之一的员工：<br>1) 来自 'IT' 部门，薪资高于 80000，且姓氏以 'ov' 结尾（full_name 字段）。<br>2) 来自 'Sales' 部门，薪资在 40000 到 55000 之间（含）。<br>返回所有列。"
  },
  "hint": {
    "ru": "Используйте операторы AND, OR, LIKE, BETWEEN или комбинацию >= и <=.",
    "en": "Use AND, OR, LIKE, BETWEEN or combination of >= and <= operators.",
    "es": "Use los operadores AND, OR, LIKE, BETWEEN o combinación de >= y <=.",
    "zh": "使用 AND、OR、LIKE、BETWEEN 或 >= 和 <= 的组合。"
  },
  "checkSql": "SELECT * FROM Employees WHERE (department = 'IT' AND salary > 80000 AND full_name LIKE '%ов') OR (department = 'Sales' AND salary >= 40000 AND salary <= 55000)",
  "default": "SELECT * FROM Employees WHERE ..."
},
{
  "id": 7,
  "desc": {
    "ru": "Найдите продукты, общее количество проданных единиц (сумма quantity) которых больше 3. Выведите название продукта и общее количество проданных единиц.",
    "en": "Find products whose total sold quantity (sum of quantity) is greater than 3. Display product name and total sold quantity.",
    "es": "Encuentre productos cuya cantidad total vendida (suma de quantity) sea mayor que 3. Muestre el nombre del producto y la cantidad total vendida.",
    "zh": "查找总销售量（quantity 总和）大于 3 的产品。显示产品名称和总销售量。"
  },
  "hint": {
    "ru": "Используйте JOIN, GROUP BY, HAVING и агрегатную функцию SUM.",
    "en": "Use JOIN, GROUP BY, HAVING and the SUM aggregate function.",
    "es": "Use JOIN, GROUP BY, HAVING y la función de agregación SUM.",
    "zh": "使用 JOIN、GROUP BY、HAVING 和 SUM 聚合函数。"
  },
  "checkSql": "SELECT Products.name, SUM(Orders.quantity) AS total_quantity FROM Orders JOIN Products ON Orders.product_id = Products.id GROUP BY Products.id, Products.name HAVING SUM(Orders.quantity) > 3",
  "default": "SELECT Products.name, SUM(Orders.quantity) AS total_quantity FROM Orders JOIN Products ON ... GROUP BY ... HAVING ..."
}
];