CREATE SCHEMA client;

GO

create table client.users
(
    id  bigserial not null,
    login varchar(255),
    name varchar(255),
    password varchar(255),
    status varchar(255),
    surname varchar(255),
    primary key (id)
)

GO

create table client.roles
(
    id  bigserial not null,
    name varchar(255),
    primary key (id)
)

GO

create table client.profiles
(
    id  bigserial not null,
    primary key (id)
)

GO

create table client.users_roles (
    user_id int8 not null,
    role_id int8 not null
)

GO

alter table if exists users_roles add constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references client.roles

GO

alter table if exists users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references client.users

GO

insert into client.roles (name)
values ('ADMIN'),
       ('USER')

GO

create table users_roles (
                             user_id               int8 NOT NULL,
                             role_id               int8 NOT NULL,
                             primary key (user_id, role_id),
                             FOREIGN KEY (user_id)  REFERENCES client.users (id),
                             FOREIGN KEY (role_id)  REFERENCES client.roles (id)
)


-- GO
--
-- insert into client.users (login, password, name, surname, status)
-- values ('1', '$2y$12$6xsSxUKsOFUvcDc3wVbuZ.JsW5Kg/4fvhcc7JDYuoarpWI97yf9sO', 'admin', 'ACTIVE'),
--        ('2', '$2y$12$6xsSxUKsOFUvcDc3wVbuZ.JsW5Kg/4fvhcc7JDYuoarpWI97yf9sO', 'user', 'ACTIVE'),
--        ('3', '$2y$12$6xsSxUKsOFUvcDc3wVbuZ.JsW5Kg/4fvhcc7JDYuoarpWI97yf9sO', 'user', 'BANNED');



-- GO
--
-- insert into users_roles (user_id, role_id)
-- values
-- (1, 1),
-- (2, 2);


