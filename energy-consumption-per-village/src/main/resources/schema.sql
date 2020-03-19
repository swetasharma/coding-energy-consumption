DROP TABLE IF EXISTS village;
CREATE TABLE village
(
    id              INT         NOT NULL AUTO_INCREMENT,
    name            VARCHAR(20) NOT NULL,
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
    netAmount       DOUBLE      NOT NULL DEFAULT 0.0,
    createdDate     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS counterqueue;
CREATE TABLE counterqueue
(
    id              INT         NOT NULL AUTO_INCREMENT,
    counterId       INT         NOT NULL,
    amount          DOUBLE      NOT NULL,
    processed       INT         NOT NULL DEFAULT 0,
    createdDate     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    PRIMARY KEY (id)
);