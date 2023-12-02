CREATE SCHEMA IF NOT EXISTS messenger;

CREATE TABLE IF NOT EXISTS users
(
    id       bigserial primary key,
    name     varchar(255) not null unique,
    email    varchar(255) not null,
    password varchar(255) not null
);

CREATE TABLE IF NOT EXISTS posts
(
    id          bigserial primary key,
    title       varchar(255) not null,
    description text         not null,
    created_at  timestamp    not null
);

CREATE TABLE IF NOT EXISTS users_posts
(
    user_id bigint not null,
    post_id bigint not null,
    primary key(user_id, post_id),
    constraint fk_users_posts_users foreign key (user_id) references users (id) on delete cascade on update no action,
    constraint fk_users_posts_posts foreign key (post_id) references posts (id) on delete cascade on update no action
);

create table if not exists users_roles
(
    user_id bigint       not null,
    role    varchar(255) not null,
    primary key (user_id, role),
    constraint fk_users_roles_users foreign key (user_id) references users (id) on delete cascade on update no action
);