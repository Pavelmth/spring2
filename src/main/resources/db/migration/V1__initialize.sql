DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id                    bigserial,
  username              varchar(50) NOT NULL,
  password              varchar(80) NOT NULL,
  first_name            VARCHAR(50) NOT NULL,
  last_name             VARCHAR(50) NOT NULL,
  email                 VARCHAR(50) NOT NULL,
  phone                 VARCHAR(15) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
  id                    serial,
  name                  VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS users_roles;
CREATE TABLE users_roles (
  user_id               INT NOT NULL,
  role_id               INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id)
  REFERENCES users (id),
  FOREIGN KEY (role_id)
  REFERENCES roles (id)
);

INSERT INTO roles (name)
VALUES
('ROLE_USER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

INSERT INTO users (username, password, first_name, last_name, email, phone)
VALUES
('admin','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','Admin','Admin','admin@gmail.com','+79881111111');

INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(1, 3);

DROP TABLE IF EXISTS products;
CREATE TABLE products (id bigserial PRIMARY KEY, title varchar(255), price numeric(8, 2));
INSERT INTO products (title, price) VALUES
('Cheese', 320.0),
('Milk', 90.0),
('Apples', 120.0);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (id bigserial PRIMARY KEY, user_id bigint, price numeric(8, 2), status varchar(255), created_at timestamp, updated_at timestamp, FOREIGN KEY (user_id) REFERENCES users (id));

DROP TABLE IF EXISTS order_items;
CREATE TABLE order_items (id bigserial PRIMARY KEY, order_id bigint, product_id bigint, quantity int, item_price numeric(8, 2),
total_price numeric(8, 2), FOREIGN KEY (order_id) REFERENCES orders(id), FOREIGN KEY (product_id) REFERENCES products(id));