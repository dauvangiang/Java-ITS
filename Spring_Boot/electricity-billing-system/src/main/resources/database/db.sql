CREATE DATABASE electricity_billing_system;

USE electricity_billing_system;

CREATE TABLE customer
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    full_name  VARCHAR(255) NOT NULL,
    phone      VARCHAR(255) NOT NULL,
    address    VARCHAR(255) NOT NULL,
    status     INT          NOT NULL DEFAULT 1 COMMENT 'Trạng thái: 0 - Ngừng dịch vụ, 1 - Đang sử dụng dịch vụ',
    created_at TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at TIMESTAMP    NOT NULL DEFAULT now(),
    PRIMARY KEY (id)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE role
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at TIMESTAMP    NOT NULL DEFAULT now(),
    PRIMARY KEY (id)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO role (name, created_at, updated_at)
VALUES ("ADMIN", now(), now()),
       ("TECHNICIAN", now(), now());
       
CREATE TABLE permission
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL,
    title      VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at TIMESTAMP    NOT NULL DEFAULT now(),
    PRIMARY KEY (id)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO permission (name, title, created_at, updated_at)
VALUES ("WRITE_CUSTOMER", "Thêm thông tin khách hàng", now(), now()),
       ("READ_CUSTOMERS", "Xem danh sách thông tin khách hàng", now(), now()),
       ("READ_CUSTOMER", "Xem thông tin khách hàng", now(), now()),
       ("DELETE_CUSTOMER", "Xóa thông tin khách hàng", now(), now()),
       ("UPDATE_CUSTOMER", "Cập nhật thông tin khách hàng", now(), now()),
       ("WRITE_E_PRICES", "Thêm bậc giá điện", now(), now()),
       ("READ_E_PRICES", "Xem bậc giá điện", now(), now()),
       ("DELETE_E_PRICES", "Xóa bậc giá điện", now(), now()),
       ("UPDATE_E_PRICES", "Cập nhật bậc giá điện", now(), now()),
       ("WRITE_PERMISSION", "Thêm quyền hạn", now(), now()),
       ("READ_PERMISSION", "Xem quyền hạn", now(), now()),
       ("DELETE_PERMISSION", "Xóa quyền hạn", now(), now()),
       ("UPDATE_PERMISSION", "Cập nhật quyền hạn", now(), now()),
       ("WRITE_BILL", "Ghi hóa đơn", now(), now());

CREATE TABLE role_permission
(
    id            BIGINT    NOT NULL AUTO_INCREMENT,
    role_id       BIGINT    NOT NULL COMMENT 'Khóa ngoại đến vai trò',
    permission_id BIGINT    NOT NULL COMMENT 'Khóa ngoại đến quyền hạn',
    created_at    TIMESTAMP NOT NULL DEFAULT now(),
    updated_at    TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES role (id),
    FOREIGN KEY (permission_id) REFERENCES permission (id)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO role_permission (role_id, permission_id, created_at, updated_at)
VALUES (1, 1, now(), now()),
       (1, 2, now(), now()),
       (1, 3, now(), now()),
       (1, 4, now(), now()),
       (1, 5, now(), now()),
       (1, 6, now(), now()),
       (1, 7, now(), now()),
       (1, 8, now(), now()),
       (1, 9, now(), now()),
       (1, 10, now(), now()),
       (1, 11, now(), now()),
       (1, 12, now(), now()),
       (1, 13, now(), now()),
       (2, 3, now(), now()),
       (2, 7, now(), now()),
       (2, 14, now(), now());

CREATE TABLE user
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    username   VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    full_name  VARCHAR(255) NOT NULL,
    phone      VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    address    VARCHAR(255) NOT NULL,
    role_id    BIGINT       NOT NULL DEFAULT 1 COMMENT 'Khóa ngoại đến vai trò của người dùng',
    created_at TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at TIMESTAMP    NOT NULL DEFAULT now(),
    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES role (id)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- username: admin
-- password: admin
INSERT INTO user(username, password, full_name, phone, email, address, role_id, created_at, updated_at)
VALUES ("admin",
        "$2y$10$23hS70KrpGt062voer2R1OnTs0XKOTfwURgSNxfqqx4vUXfme4Tk6",
        "Admin",
        "00000",
        "admin@gmail.com",
        "HN",
        1,
        now(),
        now());

CREATE TABLE electricity_prices
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL COMMENT 'Tên bậc giá sử dụng điện',
    min_use    INT          NOT NULL COMMENT 'Mức điện sử dụng thấp nhất (kg)',
    max_use    INT          NOT NULL COMMENT 'Mức điện sử dụng cao nhất (kg)',
    price      FLOAT        NOT NULL DEFAULT 0 COMMENT 'Giá điện',
    status     INT          NOT NULL DEFAULT 1 COMMENT 'Trạng thái: 0 - Không còn áp dụng, 1 - Đang áp dụng',
    created_at TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at TIMESTAMP    NOT NULL DEFAULT now(),
    PRIMARY KEY (id)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO electricity_prices (name, min_use, max_use, price, status, created_at, updated_at)
VALUES ('Bậc 1', 0, 50, 1806.0, 1, now(), now()),
       ('Bậc 2', 51, 100, 1866.0, 1, now(), now()),
       ('Bậc 3', 101, 200, 2167.0, 1, now(), now()),
       ('Bậc 4', 201, 300, 2729.0, 1, now(), now()),
       ('Bậc 5', 301, 400, 3050.0, 1, now(), now()),
       ('Bậc 6', 401, 1000000000, 3151.0, 1, now(), now());

CREATE TABLE electricity_bill
(
    id               BIGINT       NOT NULL AUTO_INCREMENT,
    customer_id      BIGINT       NOT NULL COMMENT 'Khóa ngoại đến khách hàng',
    writing_date     TIMESTAMP    NOT NULL DEFAULT now() COMMENT 'Ngày ghi hóa đơn',
    billing_period   VARCHAR(255) NOT NULL COMMENT 'Chu kỳ thanh toán',
    previous_reading INT          NOT NULL COMMENT 'Số điện cuối của chu kỳ trước',
    current_reading  INT          NOT NULL COMMENT 'Số điện cuối hiện tại',
    used             INT          NOT NULL COMMENT 'Số điện sử dụng = current_reading - previous_reading',
    total_cost       FLOAT		  NOT NULL COMMENT 'Tổng tiền điện',
    payment_status 	 INT          NOT NULL DEFAULT 0 COMMENT 'Trạng thái thanh toán: 0 - Chưa thanh toán, 1 - Đã thanh toán',
	created_at  	 TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at  	 TIMESTAMP    NOT NULL DEFAULT now(),
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer(id)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- Một hóa đơn có nhiều mức giá và ngược lại???

CREATE TABLE revoked_token
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    token      VARCHAR(255) NOT NULL COMMENT 'Mã token bị thu hồi',
    expired_at TIMESTAMP	NOT NULL DEFAULT now(),
    PRIMARY KEY (id)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
