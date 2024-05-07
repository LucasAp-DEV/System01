INSERT INTO usuario (login, password, nome, email, telefone, role)
VALUES
    ('lucas', '$2a$10$M9EXjmb1W4tfAm4UbH9hpeY8OR0jyUEA4M5FlLiYrUd9G/GchBZi6', 'Lucas Aparecido', 'lucas@gmail.com', '44997574461', 'ADMIN'),
    ('leonardo', '$2a$10$M9EXjmb1W4tfAm4UbH9hpeY8OR0jyUEA4M5FlLiYrUd9G/GchBZi6', 'Leonardo Rorato', 'leonardo@gmail.com', '44997574585', 'ADMIN'),
    ('ivan', '$2a$10$M9EXjmb1W4tfAm4UbH9hpeY8OR0jyUEA4M5FlLiYrUd9G/GchBZi6', 'Ivan Rorato', 'ivan@gmail.com', '44997574695', 'USER');

INSERT INTO cidade (name)
VALUES
    ('Curitiba'),
    ('Londrina'),
    ('Maringá'),
    ('Ponta Grossa'),
    ('Cascavel'),
    ('São José dos Pinhais'),
    ('Foz do Iguaçu'),
    ('Colombo'),
    ('Guarapuava'),
    ('Paranaguá'),
    ('Apucarana'),
    ('Toledo'),
    ('Campo Largo'),
    ('Arapongas'),
    ('Pinhais'),
    ('Umuarama'),
    ('Araucária'),
    ('Cambé'),
    ('Piraquara'),
    ('Campo Mourão'),
    ('Araruna');

INSERT INTO local (price, descricao, endereco, cidade_id, locatario_id)
VALUES
    (400, 'teste descrição 1', 'Rua Floresta', 2, 1),
    (450, 'teste descrição 2', 'Rua Oceano', 3, 2),
    (500, 'teste descrição 3', 'Rua acacia', 6, 3);

INSERT INTO contrato (data, status, locador_id, locatario_id, local_id)
VALUES
    ('2024-05-07', 'ABERTO', 1, 3, 1),
    ('2024-05-07', 'ABERTO', 2, 3, 2),
    ('2024-05-07', 'ABERTO', 3, 1, 3);

INSERT INTO feedback (nota, descricao, contrato_id)
VALUES
    (10, 'Excelente serviço prestado', 1),
    (9, 'Boa comunicação e pontualidade', 2),
    (3, 'Alguns problemas durante o serviçoS', 3);


