-- Roles
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_USER');
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_ADMIN');
INSERT INTO roles (role_id, name) VALUES (default,'ROLE_KITCHEN');
INSERT INTO roles (role_id, name) VALUES (default,'ROLE_MOTORIST');

-- Users
INSERT INTO users (user_id, username, password) VALUES (default, 'user', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, password) VALUES (default, 'admin', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, password) VALUES (default, 'kitchen', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, password) VALUES (default, 'motorist', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');

-- Roles_Users
INSERT INTO roles_users (role_id, user_id) VALUES (1, 1); 
INSERT INTO roles_users (role_id, user_id) VALUES (2, 2);
INSERT INTO roles_users (role_id, user_id) VALUES (3, 3);  
INSERT INTO roles_users (role_id, user_id) VALUES (4, 4);  

-- Profiles
INSERT INTO profiles (email, user_id) VALUES ('user@mail.com', 1);
INSERT INTO profiles (email, user_id) VALUES ('admin@mail.com', 2);
INSERT INTO profiles (email, user_id) VALUES ('kitchen@mail.com', 3);
INSERT INTO profiles (email, user_id) VALUES ('motorist@mail.com', 4);

-- Customers
INSERT INTO customers (username, password, email, first_name, last_name) VALUES ('customeruser', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO', 'customeruser@mail.com', 'Customer', 'User');
INSERT INTO customers (username, password, email, first_name, last_name) VALUES ('customeradmin', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO', 'customeradmin@mail.com', 'Customer', 'Admin');

-- Addresses
INSERT INTO addresses (user_id, customer_id, address, postal_code, city) VALUES 
(1, 1, '123 User St', '12345', 'User City'),
(2, 2, '456 Admin Ave', '67890', 'Admin City');

--- Products
INSERT INTO products (name, description, price, product_type, image, available) VALUES
('Pizza Margherita', 'Pizza clásica con salsa de tomate, mozzarella y albahaca fresca.', 12.99, 'PIZZA', 'pizza-margherita.jpg', TRUE),
('Aqua Panna', 'Agua mineral natural de manantial, suave y refrescante.', 3.00, 'BEBIDA', 'aqua-panna.jpg', TRUE),
('Tiramisú', 'Delicioso postre italiano de café y mascarpone.', 5.99, 'POSTRE', 'tiramisú.jpg', TRUE),
('Pizza Pepperoni', 'Pizza con salsa de tomate, mozzarella y pepperoni crujiente.', 14.99, 'PIZZA', 'pizza-pepperoni.jpg', FALSE),
('Coca-Cola', 'Refresco clásico con burbujas y sabor a cola.', 2.50, 'BEBIDA', 'coca-cola.jpg', FALSE),
('Panna Cotta', 'Postre italiano de crema cocida con un toque de vainilla.', 4.50, 'POSTRE', 'panna-cotta.jpg', FALSE);

-- Pedidos
INSERT INTO orders (user_id, order_number, order_type_code, payment_id, order_status, date_order) VALUES 
(1, 'ORD001', 'ONLINE', 1, 'PENDING', '2023-10-01'),
(2, 'ORD002', 'IN_STORE', 2, 'COMPLETED', '2023-10-02'),
(3, 'ORD003', 'DELIVERY', 3, 'CANCELLED', '2023-10-03');

-- Detalles de los pedidos
INSERT INTO order_details (order_id, product_id, product_quantity) VALUES 
(1, 1, 2),
(2, 2, 1),
(3, 3, 3);