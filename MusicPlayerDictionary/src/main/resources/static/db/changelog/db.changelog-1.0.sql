--liquibase formatted sql

--changeset malyginae:1

CREATE TABLE IF NOT EXISTS music_player_dictionaries
(
    id SERIAL PRIMARY KEY,

    music_id INT UNIQUE NOT NULL,
    image_id INT UNIQUE NOT NULL,
    updated_at TIMESTAMP
);
