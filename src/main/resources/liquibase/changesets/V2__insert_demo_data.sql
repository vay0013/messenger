insert into users (name, email, password)
values ('John Wick', 'J0hnw1ck@gmail.com', '$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W'),
       ('Mike Smith', 'mikesmith@yahoo.com', '$2a$10$fFLij9aYgaNCFPTL9WcA/uoCRukxnwf.vOQ8nrEEOskrCNmGsxY7m');

insert into posts (title, description, created_at)
values ('Buy cheese', 'i go to shop', '2023-01-29 12:00:00'),
       ('Do homework', 'Math, Physics, Literature', '2023-01-31 00:00:00'),
       ('Clean rooms', 'Cleaning room to day', '2023-01-23 02:00:00'),
       ('Call Mike', 'Ask about meeting', '2023-02-01 00:00:00');

insert into users_posts (post_id, user_id)
values (1, 2),
       (2, 2),
       (3, 2),
       (4, 1);

insert into users_roles (user_id, role)
values (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');