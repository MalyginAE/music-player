--liquibase formatted sql

--changeset malyginae:1

CREATE TABLE IF NOT EXISTS music
(
    id                 BIGSERIAL PRIMARY KEY,
    music_name         varchar(255) NOT NULL,
    author             varchar(255) NOT NULL,
    image_id           INT UNIQUE   NOT NULL,
    track_id           INT UNIQUE   NOT NULL,
    external_search_id VARCHAR(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS users
(
    id        BIGSERIAL PRIMARY KEY,
    user_role varchar(255) NOT NULL default 'user',
    username  varchar(255) NOT NULL UNIQUE,
    provider  varchar(255) NOT NULL default 'LOCAL'
);

CREATE TABLE IF NOT EXISTS tokens
(
    id            BIGSERIAL PRIMARY KEY,
    refresh_token varchar UNIQUE,
    user_id       BIGINT references users (id) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS likes
(
    id       BIGSERIAL PRIMARY KEY,
    music_id BIGINT references music (id),
    user_id  BIGINT references users (id),
    UNIQUE (music_id, user_id)
);

CREATE TABLE IF NOT EXISTS playlists
(
    id            BIGSERIAL PRIMARY KEY,
    playlist_name varchar(255) NOT NULL default '',
    user_id       BIGINT references users (id),
    UNIQUE (user_id, playlist_name)
);

CREATE TABLE IF NOT EXISTS playlist_music
(
    id          BIGSERIAL PRIMARY KEY,
    playlist_id BIGINT references playlists (id),
    music_id    BIGINT references music (id),
    UNIQUE (music_id, playlist_id)
);

INSERT INTO users(id, user_role, username)
VALUES (10, 'ADMIN', 'andrey.malygin2002@gmail.com');

INSERT INTO music(id, music_name, author, track_id, image_id)
VALUES (1, 'Улетай', 'Три дня дождя', 1, 1);

INSERT INTO music(id, music_name, author, track_id, image_id)
VALUES (2, 'Утренняя гимнастика', 'Владимир Высоцкий', 2, 2);

INSERT INTO music(id, music_name, author, track_id, image_id)
VALUES (3, 'Дым', 'Егор Крид feat. JONY', 3, 3);

INSERT INTO music(id, music_name, author, track_id, image_id)
VALUES (4, 'I Want It That Way', 'PHURS ,BOOTY LEAK, Michelle Ray', 4, 4);

INSERT INTO music(id, music_name, author, track_id, image_id)
VALUES (5, 'I Want It That Way', 'PHURS ,BOOTY LEAK, Michelle Ray', 5, 5);