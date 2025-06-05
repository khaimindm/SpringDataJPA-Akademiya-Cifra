CREATE TABLE dish (
dish_id SERIAL PRIMARY KEY,
dish_name VARCHAR(50) NOT NULL,
dish_price INT NOT NULL,
dish_kitchen_id INT NOT NULL,
dtype VARCHAR(20) NOT NULL
);

CREATE TABLE order_dish (
order_id SERIAL PRIMARY KEY,
order_date DATE NOT NULL,
order_readiness BOOL NOT NULL,
order_full_cost INT NOT NULL,
order_dish_client_id INT NOT NULL
);

CREATE TABLE client (
client_id SERIAL PRIMARY KEY,
client_name VARCHAR(20) NOT NULL
);

CREATE TABLE order_detail (
order_detail_id SERIAL PRIMARY KEY,
order_detail_order_dish_order_id INT NOT NULL,
order_detail_dish_id INT NOT NULL,
quantity INT NOT NULL
);


CREATE TABLE kitchen (
kitchen_id SERIAL PRIMARY KEY,
kitchen_name VARCHAR(20) NOT NULL
);


INSERT INTO dish (dish_name, dish_price, dish_kitchen_id, DTYPE) VALUES ('Margarita_SMALL', 300, 1, 'pizza');
INSERT INTO dish (dish_name, dish_price, dish_kitchen_id, DTYPE) VALUES ('Margarita_MEDIUM', 350, 1, 'pizza');
INSERT INTO dish (dish_name, dish_price, dish_kitchen_id, DTYPE) VALUES ('Margarita_LARGE', 400, 1, 'pizza');
INSERT INTO dish (dish_name, dish_price, dish_kitchen_id, DTYPE) VALUES ('Nigiridzushi_5', 280, 2, 'japanese');
INSERT INTO dish (dish_name, dish_price, dish_kitchen_id, DTYPE) VALUES ('Pepperoni_SMALL', 320, 1, 'pizza');

INSERT INTO order_dish (order_date, order_readiness, order_full_cost, order_dish_client_id) VALUES ('2025-05-21', TRUE, 850, 1);

INSERT INTO client (client_name) VALUES ('Ivan');

INSERT INTO order_detail (order_detail_order_dish_order_id, order_detail_dish_id, quantity) VALUES (1, 1, 1);
INSERT INTO order_detail (order_detail_order_dish_order_id, order_detail_dish_id, quantity) VALUES (1, 4, 1);
INSERT INTO order_detail (order_detail_order_dish_order_id, order_detail_dish_id, quantity) VALUES (1, 5, 1);

INSERT INTO kitchen (kitchen_name) VALUES('Pizza');
INSERT INTO kitchen (kitchen_name) VALUES('Japanese');