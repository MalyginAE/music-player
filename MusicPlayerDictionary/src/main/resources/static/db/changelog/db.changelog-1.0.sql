--liquibase formatted sql

--changeset malyginae:1

CREATE TABLE IF NOT EXISTS music_player_dictionaries
(
    id SERIAL PRIMARY KEY,

    music_id INT UNIQUE NOT NULL,
    image_id INT UNIQUE NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS "user"(
    id SERIAL PRIMARY KEY,
    role varchar(255) NOT NULL default 'user',
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL default '{noop}123',
    provider varchar(255) NOT NULL default 'LOCAL'
);
