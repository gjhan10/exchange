CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE users_roles (
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);


-- Insertar roles
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

-- Insertar usuarios de prueba
INSERT INTO users (username, password, enabled)
VALUES ('jhan@gmail.com', '$2a$10$Z5/t8VuugQiTQvljDJ3fcOXnAXLcEZvm1GoOV/EfbS1J54tOVAQAe', TRUE);

INSERT INTO users (username, password, enabled)
VALUES ('jeyson@gmail.com', '$2a$10$Z5/t8VuugQiTQvljDJ3fcOXnAXLcEZvm1GoOV/EfbS1J54tOVAQAe', TRUE);

-- Asignar roles a los usuarios
INSERT INTO users_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username = 'jhan@gmail.com' AND r.name = 'ROLE_ADMIN';

INSERT INTO users_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username = 'jhan@gmail.com' AND r.name = 'ROLE_USER';
