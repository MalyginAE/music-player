CREATE DATABASE music_player_dictionaries;
CREATE  TABLE IF NOT EXISTS music_player_dictionaries(
    id SERIAL PRIMARY KEY ,
    updated_at TIMESTAMP
);

SELECT '{"Inna":"Andrey"}'::json;

CREATE FUNCTION inna()