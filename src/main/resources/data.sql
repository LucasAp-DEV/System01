INSERT INTO usuario (login, password, nome, email, telefone, role)
VALUES
    ('lucas', '$2a$10$M9EXjmb1W4tfAm4UbH9hpeY8OR0jyUEA4M5FlLiYrUd9G/GchBZi6', 'Lucas Aparecido', 'lucas@gmail.com', '997574461', 'LOCADOR'),
    ('leonardo', '$2a$10$M9EXjmb1W4tfAm4UbH9hpeY8OR0jyUEA4M5FlLiYrUd9G/GchBZi6', 'Leonardo Rorato', 'leonardo@gmail.com', '997574585', 'LOCADOR'),
    ('ivan', '$2a$10$M9EXjmb1W4tfAm4UbH9hpeY8OR0jyUEA4M5FlLiYrUd9G/GchBZi6', 'Ivan Rorato', 'ivan@gmail.com', '997574695', 'LOCATARIO');

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

INSERT INTO local (price, descricao, endereco, cidade_id, locatario_id, status, disponibilidade)
VALUES
    (400, 'Edícula para eventos familiares tem piscina, 4 quartos, 2 banheiros e sauna.', 'Rua Floresta', 2, 1, 'NORMAL',1),
    (450, 'Local amplo, perfeito para festas de formatura e casamentos.', 'Rua Oceano', 3, 2, 'PATROCINADO',1),
    (500, 'Salão de eventos com amplo espaço, perfeito para formaturas e festas de aniversários.', 'Rua acacia', 6, 3, 'NORMAL',1),
    (550, 'Campo de grama sintética, perfeito para jogos com amigos e crianças.', 'Rua Marfin', 8, 3, 'PATROCINADO',1);

INSERT INTO contrato (data, status, locador_id, locatario_id, local_id)
VALUES
    ('2024-05-05', 'ABERTO', 2, 1, 1),
    ('2024-05-30', 'ABERTO', 3, 2, 2),
    ('2024-05-20', 'ABERTO', 1, 3, 3),
    ('2024-05-10', 'ABERTO', 1, 3, 4);

INSERT INTO feedback (nota, descricao, contrato_id)
VALUES
    (10, 'Excelente serviço prestado', 1),
    (9, 'Boa comunicação e pontualidade', 2),
    (3, 'Alguns problemas durante o serviçoS', 3),
    (5, 'Alguns problemas durante o serviçoS', 4);





