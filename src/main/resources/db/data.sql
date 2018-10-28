DELETE FROM clients;
DELETE FROM autos;

INSERT INTO clients (name,birthday) VALUES ('Genya','1989-01-22');
INSERT INTO clients (name,birthday) VALUES ('Tanya','1994-12-25');
INSERT INTO clients (name,birthday) VALUES ('Lusi','2018-06-07');
INSERT INTO clients (name,birthday) VALUES ('RuSoft','2005-05-11');

INSERT INTO autos (name, birthday,client_id) VALUES ('Mazda', '2000-05-11',1);
INSERT INTO autos (name, birthday,client_id) VALUES ('Lada', '2015-09-23',2);
INSERT INTO autos (name, birthday,client_id) VALUES ('Tesla', '2011-11-28',3);
INSERT INTO autos (name, birthday,client_id) VALUES ('Audi', '2007-10-01',4);
INSERT INTO autos (name, birthday) VALUES ('Mercedes', '2014-12-23');



