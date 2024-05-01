--liquibase formatted sql

--changeset malyginae:1

CREATE TABLE IF NOT EXISTS music
(
    id                 SERIAL PRIMARY KEY,
    music_name         varchar(255) NOT NULL,
    author             varchar(255) NOT NULL,
    image_id           INT UNIQUE   NOT NULL,
    track_id           INT UNIQUE   NOT NULL,
    external_search_id VARCHAR(255) UNIQUE
--     user_id  INT references users (id)
--     updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS users
(
    id        BIGSERIAL PRIMARY KEY,
    music_id  INT references music (id),
    user_role varchar(255) NOT NULL default 'user',
    username  varchar(255) NOT NULL,
    password  varchar(255) NOT NULL default '{noop}123',
    provider  varchar(255) NOT NULL default 'LOCAL'
);

CREATE TABLE IF NOT EXISTS likes
(
    id       SERIAL PRIMARY KEY,
    music_id INT references music (id),
    user_id  INT references users (id),
    UNIQUE (music_id, user_id)
);

INSERT INTO users(id, user_role, username)
VALUES (10, 'ADMIN', 'andrey.malygin2002@gmail.com');

INSERT INTO music(id, music_name, author, track_id, image_id)
VALUES (1, 'Улетай', 'Три дня дождя', 1, 1);

INSERT INTO music(id, music_name, author, track_id, image_id)
VALUES (2, 'Владимир Высоцкий', 'Утренняя гимнастика', 2, 2);