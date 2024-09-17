-- Roles
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_USER');
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_ADMIN');

-- Users
INSERT INTO users (user_id, username, password) VALUES (default, 'user', '$2a$12$K0PNWuP6xtBLdt8iFc.Jee6eJuCOHJx/y7gpQdp.I5EXg0Ub.JnEa');
INSERT INTO users (user_id, username, password) VALUES (default, 'admin', '$$2a$12$K0PNWuP6xtBLdt8iFc.Jee6eJuCOHJx/y7gpQdp.I5EXg0Ub.JnEa');

-- Roles_Users (Ensure role_id and user_id are correctly mapped)
INSERT INTO roles_users (role_id, user_id) VALUES (1, 1);  
INSERT INTO roles_users (role_id, user_id) VALUES (2, 2); 