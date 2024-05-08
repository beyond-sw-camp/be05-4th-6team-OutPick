CREATE TABLE product(
    product_id BIGINT AUTO_INCREMENT NOT NULL,
    category_id VARCHAR(50),
    name VARCHAR(50),
    price BIGINT,
    stock BIGINT,
    content VARCHAR(100),
    incomming_date DATETIME DEFAULT SYSDATE(),
    supplier VARCHAR(50),
    storage VARCHAR(50),
    CONSTRAINT PRIMARY KEY(product_id)
);

INSERT INTO product
(category_id, name, price, stock, content, supplier, storage)
VALUES ('123', '노란반팔', 23400, 450, '이뻐요', '우리집', '우리집'),
('123', '빨강반팔', 23420, 250, '이뻐요2', '우리집2', '우리집2');