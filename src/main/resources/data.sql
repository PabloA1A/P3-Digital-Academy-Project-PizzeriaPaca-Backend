-- Roles
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_USER');
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_ADMIN');

-- Users
INSERT INTO users (user_id, username, password) VALUES (default, 'user', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, password) VALUES (default, 'admin', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, password) VALUES (default, 'johndoe', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');

-- Roles_Users
INSERT INTO roles_users (role_id, user_id) VALUES (1, 1); 
INSERT INTO roles_users (role_id, user_id) VALUES (2, 2);
INSERT INTO roles_users (role_id, user_id) VALUES (1, 3); 

-- Profiles
INSERT INTO profiles (email, user_id, first_name, last_name, address, postal_code, city) VALUES
('user@mail.com', 1, 'User', 'Test', 'Street 1', '11111', 'City1'),
('admin@mail.com', 2, 'Admin', 'Test', 'Street 2', '22222', 'City2');
INSERT INTO profiles (email, user_id, first_name, last_name, address, postal_code, city) VALUES
('john.doe@example.com', 3, 'John', 'Doe', '123 Main St', '12345', 'Cityville');
