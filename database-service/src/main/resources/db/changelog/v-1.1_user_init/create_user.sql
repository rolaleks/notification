CREATE SCHEMA client;

GO

create table client.users
(
    id       bigserial,
    login    varchar(30) not null unique,
    password varchar(80) not null,
    name     varchar(50),
    surname  varchar(50),
    status   varchar(20) not null,
    primary key (id)
);

GO

insert into client.users (login, password, name, surname, status)
values ('1', '$2y$12$6xsSxUKsOFUvcDc3wVbuZ.JsW5Kg/4fvhcc7JDYuoarpWI97yf9sO', 'admin', 'ACTIVE'),
       ('2', '$2y$12$6xsSxUKsOFUvcDc3wVbuZ.JsW5Kg/4fvhcc7JDYuoarpWI97yf9sO', 'user', 'ACTIVE'),
       ('3', '$2y$12$6xsSxUKsOFUvcDc3wVbuZ.JsW5Kg/4fvhcc7JDYuoarpWI97yf9sO', 'user', 'BANNED');


GO

create table client.roles
(
    id   bigserial,
    name varchar(30) not null unique,
    primary key (id)
);

GO

insert into client.roles (name)
values ('ADMIN'),
       ('USER');


GO

create table users_roles (
                             user_id               INT NOT NULL,
                             role_id               INT NOT NULL,
                             primary key (user_id, role_id),
                             FOREIGN KEY (user_id)  REFERENCES client.users (id),
                             FOREIGN KEY (role_id)  REFERENCES client.roles (id)
);

GO

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);


