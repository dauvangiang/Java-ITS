CREATE DATABASE electricity_billing_system;

USE electricity_billing_system;

CREATE TABLE customer
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name   VARCHAR(255) NOT NULL,
    phone       VARCHAR(255) NOT NULL,
    address     VARCHAR(255) NOT NULL,
    status      INT DEFAULT 1,
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP NOT NULL
);

CREATE TABLE user
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    username  VARCHAR(255) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    phone     VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    address   VARCHAR(255) NOT NULL,
    role      INT DEFAULT 1
);

CREATE TABLE electricity_prices
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    min_use INT          NOT NULL,
    max_use INT          NOT NULL,
    price   FLOAT        NOT NULL,
    status      INT DEFAULT 1,
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP NOT NULL
);

CREATE TABLE electricity_bill
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id      BIGINT,
    writing_date     DATE         NOT NULL,
    billing_period   VARCHAR(255) NOT NULL,
    previous_reading INT          NOT NULL,
    current_reading  INT          NOT NULL,
    used             INT          NOT NULL,
    total_cost       FLOAT(10, 3) NOT NULL,
    payment_status INT DEFAULT 0,

    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

--un: admin
--pw: admin
INSERT INTO user(username, password, full_name, phone, email, address, role)
VALUES ("admin",
        "$2y$10$23hS70KrpGt062voer2R1OnTs0XKOTfwURgSNxfqqx4vUXfme4Tk6",
        "Admin",
        "00000",
        "admin@gmail.com",
        "HN",
        0);

INSERT INTO electricity_prices (name, min_use, max_use, price)
VALUES ('Bậc 1', 0, 50, 1806.0),
       ('Bậc 2', 51, 100, 1866.0),
       ('Bậc 3', 101, 200, 2167.0),
       ('Bậc 4', 201, 300, 2729.0),
       ('Bậc 5', 301, 400, 3050.0),
       ('Bậc 6', 401, 1000000000, 3151.0);

CREATE TABLE token_revoked
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    token   VARCHAR(255) NOT NULL,
    expired_at INT       NOT NULL
);

CREATE TABLE role (
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL
)

INSERT INTO role (name)
VALUES  ( "ADMIN" ),
        ("TECHNICIAN");

CREATE TABLE permission (
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL
)

INSERT INTO permission (name)
VALUES  ("WRITE_CUSTOMER"),
        ("READ_CUSTOMERS"),
        ("DELETE_CUSTOMER"),
        ("UPDATE_CUSTOMER"),
        ("WRITE_E_PRICES"),
        ("READ_E_PRICES"),
        ("DELETE_E_PRICES"),
        ("UPDATE_E_PRICES"),
        ("WRITE_PERMISSION"),
        ("READ_PERMISSION"),
        ("DELETE_PERMISSION"),
        ("UPDATE_PERMISSION"),
        ("WRITE_BILL"),
        ("READ_CUSTOMER");



