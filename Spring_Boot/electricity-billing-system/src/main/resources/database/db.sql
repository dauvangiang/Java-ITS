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

INSERT INTO permission (id, name, title, created_at, updated_at)
VALUES (1, "CUSTOMER_MANAGEMENT", "Quản lý khách hàng", now(), now()),
		(2, "ELECTRICITY_BILL_MANAGEMENT", "Quản lý hóa đơn tiền điện", now(), now()),
        (3, "ELECTRICITY_PRICES_MANAGEMENT", "Quản lý bảng giá điện", now(), now()),
        (4, "ROLE_PERMISSION_MANAGEMENT", "Quản lý quyền hạn", now(), now()),
        (5, "USER_MANAGEMENT", "Quản lý tài khoản", now(), now());

CREATE TABLE role_permission
(
    id            BIGINT    NOT NULL AUTO_INCREMENT,
    role_id       BIGINT    NOT NULL COMMENT 'Khóa ngoại đến vai trò',
    permission_id BIGINT    NOT NULL COMMENT 'Khóa ngoại đến quyền hạn',
    can_write  	  BIT		NOT NULL DEFAULT 0 COMMENT "Quyền ghi: 0 - Không được ghi, 1 - Được ghi",
    can_view  	  BIT		NOT NULL DEFAULT 0 COMMENT "Quyền xem: 0 - Không được xem, 1 - Được xem",
    can_delete    BIT		NOT NULL DEFAULT 0 COMMENT "Quyền xóa: 0 - Không được xóa, 1 - Được xóa",
    created_at    TIMESTAMP NOT NULL DEFAULT now(),
    updated_at    TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES role (id),
    FOREIGN KEY (permission_id) REFERENCES permission (id)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO role_permission (id, role_id, permission_id, can_write, can_view, can_delete, created_at, updated_at)
VALUES (1, 1, 1, b'1', b'1', b'1', now(), now()), -- admin: Khach hang - ghi xem xoa
       (2, 1, 2, b'1', b'1', b'0', now(), now()), -- admin: Hoa don - ghi, xem
       (3, 1, 3, b'1', b'1', b'1', now(), now()), -- admin: Gia dien - ghi, xem, xoa
       (4, 1, 4, b'1', b'1', b'1', now(), now()), -- admin: Quyen han - ghi, xem, xoa
       (5, 1, 5, b'1', b'1', b'1', now(), now()), -- admin: Nguoi dung - ghi, xem xoa
       (6, 2, 1, b'0', b'1', b'0', now(), now()),
       (7, 2, 2, b'1', b'1', b'1', now(), now()),
       (8, 2, 3, b'0', b'1', b'0', now(), now()),
       (9, 2, 5, b'1', b'1', b'1', now(), now());

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
