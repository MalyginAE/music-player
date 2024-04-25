--liquibase formatted sql

--changeset malyginae:1

CREATE TABLE IF NOT EXISTS music_player_dictionaries
(
    id         SERIAL PRIMARY KEY,

    music_id   INT UNIQUE NOT NULL,
    image_id   INT UNIQUE NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    user_role     varchar(255) NOT NULL default 'user',
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL default '{noop}123'
);

INSERT INTO users VALUES (10,'ADMIN', 'andrey.malygin2002@gmail.com', '');