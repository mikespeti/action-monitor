CREATE TABLE T_PERSON (
  ID   BIGINT AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(255)
);

-- triggers
CREATE TRIGGER TR_PERSON_INSERT
AFTER INSERT
ON T_PERSON
FOR EACH ROW
CALL "com.betvictor.action_monitor.db.h2.triggers.PersonAfterInsertTrigger";

CREATE TRIGGER TR_PERSON_UPDATE
AFTER UPDATE
ON T_PERSON
FOR EACH ROW
CALL "com.betvictor.action_monitor.db.h2.triggers.PersonAfterUpdateTrigger";

CREATE TRIGGER TR_PERSON_DELETE
AFTER DELETE
ON T_PERSON
FOR EACH ROW
CALL "com.betvictor.action_monitor.db.h2.triggers.PersonAfterDeleteTrigger";