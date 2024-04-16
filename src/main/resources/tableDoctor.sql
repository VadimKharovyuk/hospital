create table client (
    id bigserial not null,
    appointment_date timestamp(6),
    email varchar(255),
    name varchar(255),
    phone varchar(255),
    doctor_id bigint, primary key (id))


create table doctor (
    id bigserial not null,
    name varchar(255),
    schedule varchar(255),
    specialization varchar(255),
    primary key (id))


create table doctor_client (
    doctor_id bigint not null,
    client_id bigint not null,
    primary key (doctor_id, client_id))


    INSERT INTO doctor (name, specialization, schedule) VALUES
                                                        ('Доктор Иванов', 'Терапевт', 'Пн-Пт: 9:00-17:00'),
                                                        ('Доктор Петров', 'Хирург', 'Вт-Сб: 10:00-18:00'),
                                                        ('Доктор Сидорова', 'Педиатр', 'Пн-Ср: 8:00-16:00'),
                                                        ('Доктор Смирнов', 'Офтальмолог', 'Пн-Чт: 8:00-16:00'),
                                                        ('Доктор Козлов', 'Стоматолог', 'Вт-Ср: 9:00-17:00'),
                                                        ('Доктор Федорова', 'Акушер-гинеколог', 'Пн-Пт: 10:00-18:00'),
                                                        ('Доктор Николаев', 'Невролог', 'Ср-Пт: 8:00-16:00'),
                                                        ('Доктор Васильев', 'Кардиолог', 'Пн-Чт: 10:00-18:00'),
                                                        ('Доктор Григорьев', 'Эндокринолог', 'Пн-Ср: 9:00-17:00'),
                                                        ('Доктор Шмило', 'Акушер-гинеколог', 'Вт-Ср: 9:00-17:00');