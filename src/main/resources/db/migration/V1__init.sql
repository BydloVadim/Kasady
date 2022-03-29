create table users
(
    id         bigserial,
    username   varchar(30) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (id)
);

create table roles
(
    id         bigserial,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (id)
);

CREATE TABLE users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$12$MfZpBD.dd9AMfuxlpWPFpeKXPZItJ1UQqHhdXIwt4j3UpR9xQQ9py', 'user@gmail.com'),
       ('admin', '$2a$12$MfZpBD.dd9AMfuxlpWPFpeKXPZItJ1UQqHhdXIwt4j3UpR9xQQ9py', 'admin@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 1),
       (2, 2);

create table categories
(
    id         bigserial primary key,
    title      varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into categories (title)
values ('Food');

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       numeric(19, 2) not null,
    category_id bigint references categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);



insert into products (title, price, category_id)
values ('Bread', 24, 1),
       ('Milk', 65, 1),
       ('Cheese', 320, 1),
       ('Butter', 322, 1),
       ('Sausage', 22, 1),
       ('Soup', 38, 1),
       ('Chocolate', 456, 1),
       ('Sauce', 1009, 1),
       ('Tomato', 345345, 1),
       ('Pineapple', 7457, 1),
       ('Apple', 9807807, 1),
       ('Apricot', 35345, 1),
       ('Jam', 13123, 1),
       ('Cucumber', 90889, 1),
       ('Rice', 3242, 1),
       ('Potato', 4356456, 1),
       ('Onion', 1, 1);

create table orders
(
    id         bigserial primary key,
    price      numeric(19, 2) not null,
    user_id    bigint references users (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,

    price_per_product numeric(19, 2) not null,
    price             numeric(19, 2) not null,
    product_id        bigint references products (id),
    order_id          bigint references orders (id),
    quantity          int,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);