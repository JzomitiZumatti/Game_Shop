CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    birthday DATE NOT NULL,
    amount   INT,
    nickname VARCHAR(50),
    password VARCHAR(50)
);

CREATE TABLE games
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(50),
    release_date DATE NOT NULL,
    rating       INT,
    cost         INT,
    description  TEXT
);

INSERT INTO games (name, release_date, rating, cost, description)
VALUES ('Alan Wake 2', '2023-10-20', 9, 50, 'Action adventure, Horror, Shooter'),
       ('Red Dead Redemption 2', '2019-05-12', 10, 20, 'Open world, Narrative, Action'),
       ('Grand Theft Auto V', '2015-04-14', 10, 20, 'Adventure, Action'),
       ('Resident Evil Village', '2021-05-07', 8, 16, 'Survival Horror, Horror, First Person View'),
       ('Mafia 2', '2010-08-23', 7, 30, 'Action, Open World, Crime, Shooter'),
       ('Far Cry 6', '2021-10-07', 8, 15, 'Open World, Adventure, Shooter, Action,'),
       ('Assassins Creed Valhalla', '2020-10-10', 8, 15, 'Open World, RPG, Stealth, Action'),
       ('Hitman 3', '2021-01-20', 9, 30, 'Stealth, Action'),
       ('Counter-Strike 1.6', '2003-09-12', 10, 10, 'Action, FPS, Shooter, Tactical'),
       ('Diablo 4', '2023-06-05', 9, 70, 'Action RPG, Hack and Slash, Isometric, Action'),
       ('Half-Life 2', '2004-11-16', 10, 10, 'FPS, Action, Classic'),
       ('The Witcher 3: Wild Hunt', '2015-05-19', 9, 40, 'Open World, RPG, Story Rich'),
       ('Cyberpunk 2077', '2020-12-10', 9, 60, 'Cyberpunk, Open World, Nudity, Future'),
       ('Dead Island 2', '2023-04-21', 7, 42, 'Horror, Action, Zombies');

CREATE TABLE users_games
(
    users_id INTEGER,
    games_id INTEGER,
    PRIMARY KEY (users_id, games_id),
    FOREIGN KEY (users_id) REFERENCES users (id),
    FOREIGN KEY (games_id) REFERENCES games (id)
);