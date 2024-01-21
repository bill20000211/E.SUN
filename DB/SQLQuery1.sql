-- 建立使用者
CREATE TABLE users (
    user_id INT PRIMARY KEY IDENTITY(1,1),
    phone_number VARCHAR(20) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    user_name VARCHAR(255),
    registration_time DATETIME,
    last_login_time DATETIME
);

-- 建立書籍資料
CREATE TABLE books (
    isbn VARCHAR(20) PRIMARY KEY,
    name VARCHAR(255),
    author VARCHAR(255),
    introduction TEXT
);

-- 建立書籍庫存
CREATE TABLE inventory (
    inventory_id INT PRIMARY KEY IDENTITY(1,1),
    isbn VARCHAR(20) NOT NULL,
    store_time DATETIME,
    status VARCHAR(20),
    FOREIGN KEY (isbn) REFERENCES books (isbn)
);

-- 建立借閱紀錄
CREATE TABLE borrowing_record (
    record_id INT PRIMARY KEY IDENTITY(1,1),
    user_id INT,
    inventory_id INT,
    borrowing_time DATETIME,
    return_time DATETIME,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (inventory_id) REFERENCES inventory (inventory_id)
);
