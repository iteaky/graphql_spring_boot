DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS street;
DROP TABLE IF EXISTS mall;

CREATE TABLE city
(
    city_id      INT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(250) NOT NULL,
    country VARCHAR(250) NOT NULL,
);

CREATE TABLE street
(
    street_id     INT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(250) NOT NULL,
    area   VARCHAR(250) NOT NULL,
    city_id INT          NOT NULL,
    created_by   VARCHAR(250) NOT NULL,
    FOREIGN KEY (city_id) REFERENCES city (city_id)
);

CREATE TABLE mall
(
    mall_id     INT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(250) NOT NULL,
    building INT          NOT NULL,
    street_id INT          NOT NULL,
    FOREIGN KEY (street_id) REFERENCES street (street_id)
);

INSERT INTO city (city_id, name, country)
VALUES (1, 'Saint-Petersburg', 'Russia'),
       (2, 'Tomsk', 'Russia'),
       (3, 'Kalliningrad ', 'Russia');

INSERT INTO street (street_id, name, area, city_id, created_by)
VALUES (1, 'Sheremetevskaya', 'Moskovsky', 1, 's5yx73'),
       (4, 'Nevsky', 'Centr', 1, 's5yx73'),
       (6, 'Tolmachevskaya', 'Moskovsky', 1,  's5yx73'),
       (7, 'Pulkovskoe', 'Moskovsky', 1, 's5yx73'),
       (10, 'Vosstaniya', 'Centr', 1, 's5yx73'),
       (11, 'Mayakovskaya', 'Centr', 1, 's5yx73'),
       (3, 'Frunze', 'Sovetsky', 2,  's5yx73'),
       (8, 'Altayskaya', 'Sovetsky', 2, 's5yx73'),
       (12, 'Sibirskaya', 'Sovetsky', 2, 's5yx73'),
       (5, 'A. Ahmatovoy', 'Leningradsky', 3, 's5yx73'),
       (2, 'Viktora Gugo', 'Leningradsky', 3,  's5yx73'),
       (9, 'Admirala Makarova', 'Leningradsky', 3, 's5yx73');

INSERT INTO mall (mall_id, name, building, street_id)
VALUES (4, 'Lenta Pulkovskoe', 33, 7),
       (7, 'KFC Pulkovskoe', 30, 7),
       (3, 'Shinomontaj Pulkovskoe', 27, 7),
       (5, 'Kia Pulkovskoe', 25, 7),
       (2, 'Mashtab Sheremetevskaya', 13, 1),
       (6, 'Pulkovo 3 Sheremetevskaya', 15, 1),
       (1, 'Toyota Sheremetevskaya', 17, 1);
