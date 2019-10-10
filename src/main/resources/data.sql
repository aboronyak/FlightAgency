-----------------------------
-- Airline Table and inserts
-----------------------------
DROP TABLE IF EXISTS airline;
CREATE TABLE airline
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(250) NOT NULL
);

INSERT INTO airline (name)
VALUES ('Wizzair'),
       ('Railjet'),
       ('KLM');
-----------------------------
-- Airline Table and inserts
-----------------------------
DROP TABLE IF EXISTS city;
CREATE TABLE city
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(250) NOT NULL,
    population  NUMERIC
);

INSERT INTO city (name, population)
VALUES ('Zalaegerszeg', 60000),
       ('Budapest', 2000000),
       ('Páty', 6000),
       ('Esztergom', 80000),
       ('Debrecen', 80001),
       ('Siófok', 50000),
       ('Győr', 120000);


-----------------------------
-- Flight Table and inserts
-----------------------------
DROP TABLE IF EXISTS flight;
CREATE TABLE flight
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(250) NOT NULL,
    city_id_from INT,
    city_id_to   INT,
    distance   NUMERIC,
    time_interval NUMERIC,
    airline_id INT,
    FOREIGN KEY(city_id_from) REFERENCES city,
    FOREIGN KEY(city_id_to) REFERENCES city,
    FOREIGN KEY(airline_id) REFERENCES airline
);

INSERT INTO flight (name, city_id_from, city_id_to, distance, time_interval, airline_id)
VALUES ('RJ01', (SELECT id FROM city c WHERE c.name = 'Zalaegerszeg'), (SELECT id FROM city c WHERE c.name = 'Budapest'), 250, 200, (SELECT id FROM airline c WHERE c.name = 'Railjet') ),
       ('RJ02', (SELECT id FROM city c WHERE c.name = 'Zalaegerszeg'), (SELECT id FROM city c WHERE c.name = 'Páty'), 230, 25, (SELECT id FROM airline c WHERE c.name = 'Railjet') ),
       ('RJ03', (SELECT id FROM city c WHERE c.name = 'Budapest'), (SELECT id FROM city c WHERE c.name = 'Esztergom'), 20, 5, (SELECT id FROM airline c WHERE c.name = 'Railjet') ),
       ('RJ04', (SELECT id FROM city c WHERE c.name = 'Budapest'), (SELECT id FROM city c WHERE c.name = 'Esztergom'), 10, 5, (SELECT id FROM airline c WHERE c.name = 'Railjet') ),
       ('RJ05', (SELECT id FROM city c WHERE c.name = 'Siófok'), (SELECT id FROM city c WHERE c.name = 'Győr'), 5, 5, (SELECT id FROM airline c WHERE c.name = 'Railjet') ),
       ('WZ01', (SELECT id FROM city c WHERE c.name = 'Zalaegerszeg'), (SELECT id FROM city c WHERE c.name = 'Budapest'), 240, 25, (SELECT id FROM airline c WHERE c.name = 'Wizzair') ),
       ('WZ02', (SELECT id FROM city c WHERE c.name = 'Budapest'), (SELECT id FROM city c WHERE c.name = 'Debrecen'), 10, 5, (SELECT id FROM airline c WHERE c.name = 'Wizzair') ),
       ('WZ03', (SELECT id FROM city c WHERE c.name = 'Páty'), (SELECT id FROM city c WHERE c.name = 'Zalaegerszeg'), 100, 100, (SELECT id FROM airline c WHERE c.name = 'Wizzair') ),
       ('KL01', (SELECT id FROM city c WHERE c.name = 'Páty'), (SELECT id FROM city c WHERE c.name = 'Siófok'), 15, 130, (SELECT id FROM airline c WHERE c.name = 'KLM') ),
       ('KL02', (SELECT id FROM city c WHERE c.name = 'Siófok'), (SELECT id FROM city c WHERE c.name = 'Győr'), 30, 120, (SELECT id FROM airline c WHERE c.name = 'KLM') ),
       ('KL03', (SELECT id FROM city c WHERE c.name = 'Győr'), (SELECT id FROM city c WHERE c.name = 'Budapest'), 37, 120, (SELECT id FROM airline c WHERE c.name = 'KLM') )
;