CREATE TABLE drivers(
    id SERIAL PRIMARY KEY ,
    name varchar(100),
    surname varchar(100),
    patronymic varchar(100),
    passport varchar(30),
    birthDate DATE,
    driving_exp integer,
    driving_permit varchar(5)
);

CREATE TABLE balance(
    id SERIAL PRIMARY KEY ,
    green_dollars bigint,
    red_dollars bigint,
    blue_dollars bigint,
    driver_id bigint REFERENCES drivers(id)
);
CREATE TABLE cars
(
    id SERIAL PRIMARY KEY,
    vin varchar(20),
    regNumber varchar(20),
    manufacturer varchar(100) NULL ,
    car_model varchar(100) NULL ,
    driver_id bigint REFERENCES drivers(id),
    year_of_issue DATE
);

CREATE TABLE details(
    id SERIAL PRIMARY KEY ,
    serialNumber varchar(255),
    car_id bigint REFERENCES cars(id)
);

INSERT INTO drivers(name, surname, patronymic, passport, birthDate, driving_exp, driving_permit)
VALUES ('Ivan', 'Ivanov', 'Ivanovich', '2314876827', '2000-09-04', 3, 'A1');

INSERT INTO drivers(name, surname, patronymic, passport, birthDate, driving_exp, driving_permit)
VALUES ('Oleg', 'Olegov', 'Olegovich', '1234446345', '1991-12-30', 9, 'B');

INSERT INTO drivers(name, surname, patronymic, passport, birthDate, driving_exp, driving_permit)
VALUES ('Elena', 'Ivanova', 'Nikolaevna', '5412654785', '1999-09-04', 3, 'B1');

INSERT INTO drivers(name, surname, patronymic, passport, birthDate, driving_exp, driving_permit)
VALUES ('Mariya', 'Popova', 'Alexandrovna', '5246854216', '2001-09-04', 1, 'B1');


INSERT INTO cars(vin, regNumber, manufacturer, car_model, driver_id, year_of_issue)
VALUES ('asaojfo1j3o41', 'a222asru', 'volvo', null, 1, '2000-12-12');

INSERT INTO cars(vin, regNumber, manufacturer, car_model, driver_id, year_of_issue)
VALUES ('asdagsdgasd', 'b222asru', 'nissan', null, 2, '1999-12-12');

INSERT INTO cars(vin, regNumber, manufacturer, car_model, driver_id, year_of_issue)
VALUES ('asghsdfg123', 'a412aseu', 'volvo', null, 3, '2012-12-12');

INSERT INTO cars(vin, regNumber, manufacturer, car_model, driver_id, year_of_issue)
VALUES ('gsdfsdg2341', 's653asdat', 'volvo', null, 4, '2020-12-12');


INSERT INTO balance(green_dollars, red_dollars, blue_dollars, driver_id)
VALUES (200, 500, 400, 1);

INSERT INTO balance(green_dollars, red_dollars, blue_dollars, driver_id)
VALUES (100, 0, 200, 2);

INSERT INTO balance(green_dollars, red_dollars, blue_dollars, driver_id)
VALUES (200, 500, 400, 3);

INSERT INTO balance(green_dollars, red_dollars, blue_dollars, driver_id)
VALUES (200, 500, 400, 4);

INSERT INTO details(serialNumber, car_id) VALUES ('wwasd21231s', 1);
INSERT INTO details(serialNumber, car_id) VALUES ('fasd12314ad22', 1);
INSERT INTO details(serialNumber, car_id) VALUES ('asdaf242135fsdf1', 2);
INSERT INTO details(serialNumber, car_id) VALUES ('asfgaeraf2314', 2);
INSERT INTO details(serialNumber, car_id) VALUES ('asdfgrgrtws1', 3);
INSERT INTO details(serialNumber, car_id) VALUES ('sdgsfghertsd134', 3);
INSERT INTO details(serialNumber, car_id) VALUES ('fhdhdfsdf23542456342f', 4);

