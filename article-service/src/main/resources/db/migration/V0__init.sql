CREATE SCHEMA IF NOT EXISTS article;
CREATE TABLE article.t_articles
(
    id         BIGINT NOT NULL,
    title      VARCHAR(255),
    content    VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_t_articles PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS t_articles_seq START WITH 1 INCREMENT BY 50;