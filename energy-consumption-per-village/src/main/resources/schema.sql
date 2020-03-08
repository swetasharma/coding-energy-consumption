DROP TABLE IF EXISTS village;
CREATE TABLE village
(
    id              INT         NOT NULL AUTO_INCREMENT,
    villageName     VARCHAR(20) NOT NULL,
    createdDate     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS counter;
CREATE TABLE counter
(
    id              INT         NOT NULL AUTO_INCREMENT,
    counterId       INT         NOT NULL,
    villageId       INT         NOT NULL,
    amount          DOUBLE      NOT NULL,
    createdDate     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    PRIMARY KEY (id)
);