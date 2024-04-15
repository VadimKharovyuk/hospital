create table client (id bigserial not null, address varchar(255), appointment_date timestamp(6), contact_info varchar(255), first_name varchar(255), last_name varchar(255), primary key (id))
create table doctor (id bigserial not null, name varchar(255), schedule varchar(255), specialization varchar(255), primary key (id))


    INSERT INTO client (first_name, last_name, address, contact_info, appointment_date)
VALUES ('Иван', 'Иванов', 'ул. Пушкина, д.1', 'ivan@example.com', '2024-04-20 10:00:00'),
       ('Петр', 'Петров', 'ул. Ленина, д.2', 'petr@example.com', '2024-04-21 11:00:00'),
       ('Мария', 'Сидорова', 'ул. Гагарина, д.3', 'maria@example.com', '2024-04-22 12:00:00');


INSERT INTO doctor (name, specialization, schedule)
VALUES ('Доктор Смирнов', 'Офтальмолог', 'Пн-Чт: 8:00-16:00'),
       ('Доктор Козлов', 'Стоматолог', 'Вт-Ср: 9:00-17:00'),
       ('Доктор Федорова', 'Акушер-гинеколог', 'Пн-Пт: 10:00-18:00'),
       ('Доктор Николаев', 'Невролог', 'Ср-Пт: 8:00-16:00'),
       ('Доктор Васильев', 'Кардиолог', 'Пн-Чт: 10:00-18:00'),
       ('Доктор Григорьев', 'Эндокринолог', 'Пн-Ср: 9:00-17:00');

