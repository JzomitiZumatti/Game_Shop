CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    birthday VARCHAR(50),
    amount   INT,
    nickname VARCHAR(50),
    password VARCHAR(50)
);

CREATE TABLE games
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(50),
    release_date VARCHAR(50),
    rating       INT,
    cost         INT,
    description  TEXT
);

INSERT INTO games (name, release_date, rating, cost, description)
VALUES ('Alan Wake 2', '27.10.2023', 9, 50, 'Action adventure, Horror, Shooter'),
       ('Red Dead Redemption 2', '05.12.2019', 10, 20, 'Open world, Narrative, Action'),
       ('Grand Theft Auto V', '14.04.2015', 10, 20, 'Adventure, Action'),
       ('Resident Evil Village', '07.05.2021', 8, 16, 'Survival Horror, Horror, First Person View'),
       ('Mafia 2', '23.08.2010', 7, 30, 'Action, Open World, Crime, Shooter'),
       ('Far Cry 6', '07.10.2021', 8, 15, 'Open World, Adventure, Shooter, Action,'),
       ('Assassins Creed Valhalla', '10.10.2020', 8, 15, 'Open World, RPG, Stealth, Action'),
       ('Hitman 3', '20.01.2021', 9, 30, 'Stealth, Action'),
       ('Counter-Strike 1.6', '12.09.2003', 10, 10, 'Action, FPS, Shooter, Tactical'),
       ('Diablo 4', '05.06.2023', 9, 70, 'Action RPG, Hack and Slash, Isometric, Action'),
       ('Half-Life 2', '16.11.2004', 10, 10, 'FPS, Action, Classic'),
       ('The Witcher 3: Wild Hunt', '19.05.2015', 9, 40, 'Open World, RPG, Story Rich'),
       ('Cyberpunk 2077', '10.12.2020', 9, 60, 'Cyberpunk, Open World, Nudity, Future'),
       ('Dead Island 2', '21.04.2023', 7, 42, 'Horror, Action, Zombies');

CREATE TABLE users_games
(
    users_id INTEGER,
    games_id INTEGER,
    PRIMARY KEY (users_id, games_id),
    FOREIGN KEY (users_id) REFERENCES users (id),
    FOREIGN KEY (games_id) REFERENCES games (id)
);