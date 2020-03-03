CREATE DATABASE IF NOT EXISTS energyConsumptionDB;

CREATE TABLE IF NOT EXISTS village (
    id INT NOT NULL AUTO_INCREMENT,
    villageName varchar(30) NOT NULL ,
    created_date date NOT NULL ,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS counter (
    counterId INT NOT NULL identity primary key,
    counter INT NOT NULL references village(id),
    amount double,
    created_date date
);