-- Roles
INSERT INTO roles (role_id, name) VALUES (default,'ROLE_USER');
INSERT INTO roles (role_id, name) VALUES (default,'ROLE_ADMIN');
INSERT INTO roles (role_id, name) VALUES (default,'ROLE_KITCHEN');
INSERT INTO roles (role_id, name) VALUES (default,'ROLE_MOTORIST');

-- Users
-- Users
INSERT INTO users (user_id, username, password) VALUES (default, 'user', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, password) VALUES (default, 'admin', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, password) VALUES (default, 'kitchen', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, password) VALUES (default, 'motorist', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, password) VALUES (default, 'L_user1', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, password) VALUES (default, 'L_user2', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, password) VALUES (default, 'L_user3', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
-- Roles_Users
INSERT INTO roles_users (role_id, user_id) VALUES (1, 1); 
INSERT INTO roles_users (role_id, user_id) VALUES (2, 2);
INSERT INTO roles_users (role_id, user_id) VALUES (3, 3);  
INSERT INTO roles_users (role_id, user_id) VALUES (4, 4);

INSERT INTO roles_users (role_id, user_id) VALUES (1, 5);
INSERT INTO roles_users (role_id, user_id) VALUES (1, 6);
INSERT INTO roles_users (role_id, user_id) VALUES (1, 7);  

-- Profiles

INSERT INTO profiles (email, user_id) VALUES ('user@mail.com', 1);
INSERT INTO profiles (email, user_id) VALUES ('admin@mail.com', 2);
INSERT INTO profiles (email, user_id) VALUES ('kitchen@mail.com', 3);
INSERT INTO profiles (email, user_id) VALUES ('motorist@mail.com', 4);

INSERT INTO profiles (email, user_id) VALUES ('user_1@mail.com', 5);
INSERT INTO profiles (email, user_id) VALUES ('user_2@mail.com', 6);
INSERT INTO profiles (email, user_id) VALUES ('use_3r@mail.com', 7);
-- Customers
INSERT INTO customers (username, password, email, first_name, last_name) VALUES ('customeruser', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO', 'customeruser@mail.com', 'Customer', 'User');
INSERT INTO customers (username, password, email, first_name, last_name) VALUES ('customeradmin', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO', 'customeradmin@mail.com', 'Customer', 'Admin');

-- Addresses
INSERT INTO addresses (user_id, customer_id, address, postal_code, city) VALUES 
(1, 1, '123 User St', '12345', 'User City'),
(2, 2, '456 Admin Ave', '67890', 'Admin City');

ALTER TABLE products MODIFY image VARCHAR(1000);
--- Products
INSERT INTO products (name, description, price, product_type, image, available) VALUES
('Pizza Romana', 'Pizza romana con masa fina, crujiente y ligera. Ingredientes: salsa de tomate, mozzarella, albahaca, aceite de oliva, prosciutto, champiñones, alcachofas y aceitunas.', 12.00, 'PIZZA', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2FpizzaRomana.png?alt=media&token=6ea1d86d-f741-4df8-bd98-cb4269a3fa62', TRUE),
('Pizza Stella', 'Pizza en forma de estrella con salsa de tomate, mozzarella, ricotta en los bordes y albahaca fresca.', 14.00, 'PIZZA', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2FpizzaStella.png?alt=media&token=277d0342-03d0-42a6-9eb1-d72bffe83fef', TRUE),
('Pizza Arrabbiata', 'Pizza picante con salsa arrabbiata, mozzarella, aceitunas, cebolla, albahaca y pancetta.', 13.00, 'PIZZA', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2FpizzaArrabbiata.png?alt=media&token=474f6005-918b-4e01-abf5-6858bbdbc13c', TRUE),
('Pizza Calzone', 'Pizza calzone rellena de mozzarella, ricotta, jamón, salami, champiñones y salsa de tomate.', 12.00, 'PIZZA', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2FpizzaCalzone.png?alt=media&token=d0a8b289-08bb-4eef-9311-1cb0080f6bc6', TRUE),
('Pizza Marinara', 'Pizza Marinara con salsa de tomate, ajo, orégano y aceite de oliva virgen extra. Sin queso.', 15.00, 'PIZZA', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2FPizzaMarinara.png?alt=media&token=ddb4d9e4-c6fb-4c0a-a5b2-c894885c9d5a', TRUE),
('Pizza Pepperoni', 'Pizza con salsa de tomate, mozzarella y pepperoni crujiente.', 11.00, 'PIZZA', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2Fpizza2.png?alt=media&token=148ac6dd-87e5-4a4a-8afd-04b94d498ac9', TRUE),
('Lambrusco', 'Vino tinto espumoso de Emilia-Romaña con notas de cereza y frambuesa.', 7.00, 'BEBIDA', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2Flambrusco.png?alt=media&token=486a79d9-bcee-445f-b5b8-aef3a958f83e', TRUE),
('Limoncello', 'Licor italiano de limón con sabor dulce y cítrico, originario de la costa de Amalfi.', 12.00, 'BEBIDA', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2Flimoncello.jpg?alt=media&token=48edc5cc-37ae-4ffa-965e-2fe5c45aac2a', TRUE),
('Grappa', 'Aguardiente italiano fuerte hecho a partir de restos de uva después de la vinificación.', 14.00, 'BEBIDA', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2Fgrappa.png?alt=media&token=ceaff801-dab3-45ea-9488-ad41adb55c56', TRUE),
('Fernet', 'Licor amargo italiano hecho con una mezcla de hierbas y especias, popular como digestivo.', 13.00, 'BEBIDA', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2Ffernet.png?alt=media&token=d231a267-6566-4f74-b7d3-1678907ef9a7', TRUE),
('Spritz', 'Cóctel italiano refrescante con prosecco, Aperol y agua con gas, servido con hielo.', 13.00, 'BEBIDA', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2Fspritz.png?alt=media&token=d28d6eb1-e8d5-4362-b5ee-071cae23e616', TRUE),
('Malavita', 'Cóctel con gin, vermouth rojo y bitter, servido con una rodaja de cítrico o cereza.', 15.00, 'BEBIDA', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2Fmalavita.png?alt=media&token=ad0935ae-22c4-4b9d-bbdc-eea82d359720', TRUE),
('Panna Cotta', 'Postre italiano de crema cocida con un toque de vainilla.', 4.50, 'POSTRE', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2FpannaCota.png?alt=media&token=e73ae76a-e33d-4ef8-b47a-987b27a09a21', FALSE),
('Tiramisú', 'Bizcochos de soletilla con crema de mascarpone, espolvoreados con cacao en polvo.', 9.00, 'POSTRE', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2Ftiramisu.png?alt=media&token=946d986c-cb78-4ee4-b28a-edbc206bafcb', TRUE),
('Torta Caprese', 'Torta italiana sin gluten con chocolate negro y almendras molidas.', 7.00, 'POSTRE', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2Ftortacaprese.png?alt=media&token=6403f473-95d3-4da3-935d-443428164a2c', TRUE),
('Torta della Nonna', 'Tarta italiana con masa quebrada rellena de crema pastelera y cubierta de almendras.', 4.00, 'POSTRE', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2FLatortadellanonna.png?alt=media&token=fdd0eb04-d151-4718-bd56-6ac08272ce18', TRUE),
('Gelato', 'Helado italiano denso y cremoso, hecho con ingredientes naturales y de baja grasa.', 7.00, 'POSTRE', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2Fgelato.png?alt=media&token=154c1203-a35e-4c1c-a54d-e7b0ac4319af', TRUE),
('Costrata', 'La crostata de mermelada es un postre tradicional italiano que se caracteriza por su masa crujiente y su delicioso relleno de mermelada. Este dulce es popular en muchas regiones de Italia.', 7.00, 'POSTRE', 'https://firebasestorage.googleapis.com/v0/b/pizzeria-paca.appspot.com/o/uploads%2F069e547c-af59-4fde-89c3-8adc4a0227f4_crostatademermelada.webp?alt=media&token=18043326-b414-45fc-a45b-dee646ed21be', TRUE);


-- Pedidos
INSERT INTO orders (user_id, order_number, order_type_code, payment_id, order_status, date_order, order_total_paid) VALUES 
(1, 'ORD001', 'ONLINE', 1, 'PENDING', '2023-10-01', '13.45'),
(2, 'ORD002', 'IN_STORE', 2, 'COMPLETED', '2023-10-02', '23.90'),
(3, 'ORD003', 'DELIVERY', 3, 'CANCELLED', '2023-10-03', '33.80');

-- Detalles de los pedidos
INSERT INTO order_details (order_id, product_id, product_quantity, product_price) VALUES 
(1, 1, 2, '13.45'),
(2, 2, 1, '23.90'),
(3, 3, 3, '33.80');