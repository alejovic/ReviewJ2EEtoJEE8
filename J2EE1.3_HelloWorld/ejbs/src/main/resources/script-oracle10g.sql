CREATE
USER oc4jadmin IDENTIFIED BY oc4jadmin;

GRANT CONNECT, RESOURCE TO oc4jadmin;

CREATE TABLE OC4JADMIN.HELLOWORLD
(
    ID      INTEGER NOT NULL,
    MESSAGE VARCHAR2(100) NOT NULL
);

ALTER TABLE OC4JADMIN.HELLOWORLD ADD (CONSTRAINT HELLOWORLD_PK PRIMARY KEY (ID));

CREATE SEQUENCE OC4JADMIN.HELLOWORLD_SEQ;

CREATE
OR REPLACE TRIGGER OC4JADMIN.HELLOWORLD_ON_INSERT
  BEFORE INSERT ON OC4JADMIN.HELLOWORLD
  FOR EACH ROW
BEGIN
SELECT OC4JADMIN.HELLOWORLD_SEQ.nextval
INTO :new.ID
FROM dual;
END;

INSERT INTO OC4JADMIN.HELLOWORLD (MESSAGE)
VALUES ('hello');