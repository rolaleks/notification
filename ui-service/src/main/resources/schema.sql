CREATE SCHEMA client1;

create table client1.users
(
    id         bigserial,
    login      varchar(30) not null unique,
    password   varchar(80) not null,
    email      varchar(50),
    first_name varchar(50),
    last_name  varchar(50),
    role1       varchar(20) not null,
    status     varchar(20) not null,
    primary key (id)
);

insert into client1.users (login, password, email, first_name, last_name, role1, status)
values ('1', '$2y$12$6xsSxUKsOFUvcDc3wVbuZ.JsW5Kg/4fvhcc7JDYuoarpWI97yf9sO', 'admin@gmail.com', 'admin', 'admin', 'ADMIN',
        'ACTIVE'),
       ('2', '$2y$12$6xsSxUKsOFUvcDc3wVbuZ.JsW5Kg/4fvhcc7JDYuoarpWI97yf9sO', 'user@gmail.com', 'user', 'user', 'USER',
        'ACTIVE')   ,
       ('73', '$2y$12$6xsSxUKsOFUvcDc3wVbuZ.JsW5Kg/4fvhcc7JDYuoarpWI97yf9sO', 'user3@gmail.com', 'user', 'user', 'USER',
        'BANNED');
