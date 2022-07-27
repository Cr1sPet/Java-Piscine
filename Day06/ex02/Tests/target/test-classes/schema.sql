
DROP TABLE IF EXISTS product;

CREATE TABLE IF NOT EXISTS product (
                                     id INTEGER IDENTITY PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL ,
                                     price INT NOT NULL
);
