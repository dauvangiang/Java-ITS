CREATE DATABASE customer_management;
USE customer_management;

CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) CHAR SET utf8mb4 DEFAULT 'Chưa đặt tên',
    phone CHAR(10) NOT nULL,
    email VARCHAR(100) NOT NULL,
    city VARCHAR(100) CHAR SET utf8mb4 DEFAULT 'KHÔng xác định'
);

INSERT INTO customers (name,  phone, email, city) VALUES
	('Nguyễn Văn A', '1254677890', 'anguyenvan@example.com', 'Hải Dương'),
    ('Nguyễn Văn B', '9874677890', 'bnuuyenvan@example.com', 'Hà Nội'),
    ('Nguyễn Văn C', '1906789088', 'cnguyenvan@example.com', 'Nghệ An'),
    ('Nguyễn Văn D', '8756589089', 'dnguyenvan@example.com', 'Hà Nội'),
    ('Nguyễn Văn E', '1908766089', 'enguyenvan@example.com', 'Nghệ An'),
    ('Nguyễn Văn G', '1902342355', 'gnguyenvan@example.com', 'Thái Bình'),
    ('Nguyễn Văn H', '8578987944', 'hnguyenvan@example.com', 'Hà Nội');

SELECT name, phone FROM customers;
SELECT DISTINCT city FROM customers;
SELECT * FROM customers WHERE city = 'Hà Nội';
UPDATE customers SET city = 'Hà Nam' WHERE id = 7;
SELECT * FROM customers ORDER BY city DESC;
SELECT city, COUNT(name) FROM customers GROUP BY city;