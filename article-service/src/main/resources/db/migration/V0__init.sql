CREATE SCHEMA IF NOT EXISTS article

CREATE SEQUENCE IF NOT EXISTS articles_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE article.t_articles
(
    id         BIGINT NOT NULL,
    title      VARCHAR(255),
    content    VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_articles PRIMARY KEY (id)
);