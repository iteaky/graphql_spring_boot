DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS street;

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

INSERT INTO city (city_id, name, country)
VALUES (1, 'Saint-Petersburg', 'Russia'),
       (2, 'Tomsk', 'Russia'),
       (3, 'Kalliningrad ', 'Russia');

INSERT INTO street (street_id, name, area, city_id, created_by)
VALUES (1, 'Sheremetevskaya', 'Moskovsky', 1, 's5yx73'),
       (2, 'Viktora Gugo', 'Leningradsky', 3,  's5yx73'),
       (3, 'Frunze', 'Sovetsky', 2,  's5yx73'),
       (4, 'Nevsky', 'Centr', 1, 's5yx73'),
       (5, 'A. Ahmatovoy', 'Leningradsky', 3, 's5yx73'),
       (6, 'Tolmachevskaya', 'Moskovsky', 1,  's5yx73'),
       (7, 'Pulkovskoe', 'Moskovsky', 1, 's5yx73'),
       (8, 'Altayskaya', 'Sovetsky', 2, 's5yx73'),
       (9, 'Admirala Makarova', 'Leningradsky', 3, 's5yx73'),
       (10, 'Vosstaniya', 'Centr', 1, 's5yx73'),
       (11, 'Mayakovskaya', 'Centr', 1, 's5yx73'),
       (12, 'Sibirskaya', 'Sovetsky', 2, 's5yx73');
