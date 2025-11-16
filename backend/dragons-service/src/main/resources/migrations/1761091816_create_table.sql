CREATE TYPE color AS ENUM ('GREEN', 'RED', 'WHITE', 'BROWN');
CREATE TYPE dragon_type AS ENUM ('WATER', 'UNDERGROUND', 'AIR', 'FIRE');
CREATE TYPE dragon_character AS ENUM ('CUNNING', 'WISE', 'GOOD', 'CHAOTIC_EVIL', 'FICKLE');

CREATE TABLE location
(
    id   SERIAL PRIMARY KEY,
    x    DOUBLE PRECISION,
    y    INTEGER NOT NULL,
    name VARCHAR(416)
);

CREATE TABLE dragon_head
(
    id          SERIAL PRIMARY KEY,
    tooth_count FLOAT
);

CREATE TABLE dragon_cave
(
    id                  SERIAL PRIMARY KEY,
    depth               FLOAT,
    number_of_treasures BIGINT NOT NULL CHECK (number_of_treasures > 0)
);

CREATE TABLE coordinates
(
    id SERIAL PRIMARY KEY,
    x  FLOAT            NOT NULL CHECK (x <= 187),
    y  DOUBLE PRECISION NOT NULL
);

CREATE TABLE person
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255)     NOT NULL CHECK (trim(name) <> ''),
    eye_color   color            NOT NULL,
    hair_color  color,
    location_id BIGINT           REFERENCES location (id) ON DELETE SET NULL,
    height      DOUBLE PRECISION CHECK (height IS NULL OR height > 0),
    weight      DOUBLE PRECISION NOT NULL CHECK (weight > 0),
    passport_id VARCHAR(255)     NOT NULL UNIQUE
);

CREATE TABLE dragon
(
    id             SERIAL PRIMARY KEY,
    name           VARCHAR(255) NOT NULL CHECK (trim(name) <> ''),
    coordinates_id BIGINT       NOT NULL REFERENCES coordinates (id) ON DELETE CASCADE,
    creation_date  TIMESTAMPTZ  NOT NULL DEFAULT now(),
    cave_id        BIGINT       NOT NULL REFERENCES dragon_cave (id) ON DELETE CASCADE,
    killer_id      BIGINT       REFERENCES person (id) ON DELETE SET NULL,
    age            INTEGER      NOT NULL CHECK (age > 0),
    color          color        NOT NULL,
    type           dragon_type  NOT NULL,
    character      dragon_character,
    head_id        BIGINT       REFERENCES dragon_head (id) ON DELETE SET NULL
);
