
BEGIN
INSERT INTO User (ID, USERNAME) VALUES (1, 'flyingpan');
INSERT INTO User (ID, USERNAME) VALUES (2, 'musteruser');
COMMIT

BEGIN
INSERT INTO Task (ID, OWNER_ID, TITLE, OLVERSION, STATUS) VALUES (1, 1, 'first task', 0, 'NEW');
INSERT INTO Task (ID, OWNER_ID, TITLE, OLVERSION, STATUS) VALUES (2, 1, 'second task', 0, 'NEW');
COMMIT