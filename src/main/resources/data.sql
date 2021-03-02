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
    FOREIGN KEY (city_id) REFERENCES city (city_id)
);

INSERT INTO city (city_id, name, country)
VALUES (1, 'Санкт-Петербург', 'Россия'),
       (2, 'Томск', 'Россия'),
       (3, 'Калининград ', 'Россия');

INSERT INTO street (street_id, name, area, city_id)
VALUES (1, 'Шереметьевская', 'Московский', 1),
       (2, 'Пулковское', 'Московский', 1),
       (3, 'Толмачевская', 'Московский', 1),
       (4, 'Алтайская', 'Советский', 2),
       (5, 'Сибирская', 'Советский', 2),
       (6, 'Фрунзе', 'Советский', 2),
       (7, 'Виктора Гюго', 'Ленинградский', 3),
       (8, 'А.Ахматовой', 'Ленинградский', 3),
       (9, 'Адмирала Макарова', 'Ленинградский', 3);