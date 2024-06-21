create table client
(
    id               bigserial not null,
    name             varchar(255),
    email            varchar(255),
    phone            varchar(255),
    appointment_date timestamp,
    doctor_id        bigint,
    primary key (id),
    foreign key (doctor_id) references doctor (id) on delete cascade
)

create table disease
(
    id         bigserial not null,
    name       varchar(255),
    start_date DATE,
    end_date   DATE,
    patient_id bigint,
    primary key (id),
    foreign key (patient_id) references patient (id) on delete cascade
)

create table doctor
(
    id             bigserial not null,
    name           varchar(255),
    specialization varchar(255),
    schedule       varchar(255),
    primary key (id)
)

create table doctor_client
(
    doctor_id bigint,
    client_id bigint,
    primary key (doctor_id, client_id),
    foreign key (doctor_id) references doctor (id) on delete cascade,
    foreign key (client_id) references client (id) on delete cascade
)

create table medication
(
    id         bigserial not null,
    name       varchar(255),
    dosage     varchar(255),
    period varchar (255),
    patient_id bigint,
    primary key (id),
    foreign key (patient_id) references patient (id) on delete cascade
)

create table patient
(
    id      bigserial not null,
    name    varchar(255),
    age     integer   not null,
    gender  varchar(255),
    contact varchar(255),
    primary key (id)
)
create table procedure
(
    id         bigserial not null,
    name       varchar(255),
    date       varchar(255),
    patient_id bigint,
    primary key (id),
    foreign key (patient_id) references patient (id) on delete cascade
)
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