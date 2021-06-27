
// Relational Models for User cart management
CREATE TABLE products (
 id INT AUTO_INCREMENT  PRIMARY KEY,
 name VARCHAR(250) NOT NULL,
 price INT NOT NULL,
);

CREATE TABLE Users (
 id INT AUTO_INCREMENT  PRIMARY KEY,
 name VARCHAR(250) NOT NULL,
 email INT NOT NULL,
);

CREATE TABLE cart (
 id INT AUTO_INCREMENT  PRIMARY KEY,
 user_id BIGINT NOT NULL,
 created_at TIMESTAMP,
 FOREIGN KEY (user_id) REFERENCES Users (id),
);

CREATE TABLE cartItems (
 id INT AUTO_INCREMENT  PRIMARY KEY,
 cart_id BIGINT NOT NULL ,
 product_id BIGINT NOT NULL,
 qty INT NOT NULL ,
 FOREIGN KEY (cart_id) REFERENCES cart (id),
);
