# DROP TABLES

DROP TABLE secondoprogettoweb.POST_FILE;
DROP TABLE secondoprogettoweb.POST;
DROP TABLE secondoprogettoweb.INVITE;
DROP TABLE secondoprogettoweb.PASSWORD_CHANGE;
DROP TABLE secondoprogettoweb.CREW_USER;
DROP TABLE secondoprogettoweb.CREW;
DROP TABLE secondoprogettoweb.USER;

#DROP DB E RICREO CON UTF-8

DROP DATABASE secondoprogettoweb;
CREATE DATABASE secondoprogettoweb
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

# USER TABLE CREATION

CREATE TABLE secondoprogettoweb.USER(
id INT NOT NULL AUTO_INCREMENT, 
username VARCHAR(30) NOT NULL UNIQUE,
password VARCHAR(33) NOT NULL,
avatar_name VARCHAR(50),
email VARCHAR(45) NOT NULL UNIQUE,
moderator BOOLEAN DEFAULT false,
login_date VARCHAR(30),
last_login_date VARCHAR(30),
PRIMARY KEY(id)
);

# CREW TABLE CREATION

CREATE TABLE secondoprogettoweb.CREW(
id INT NOT NULL AUTO_INCREMENT,
id_admin INT,
name VARCHAR(40),
description VARCHAR(150),
crew_private BOOLEAN,
creation_date VARCHAR(30),
crew_enabled BOOLEAN,
PRIMARY KEY(id),
FOREIGN KEY(id_admin) REFERENCES USER(id)
);

# CREW_USER TABLE CREATION

CREATE TABLE secondoprogettoweb.CREW_USER(
id_user INT,
id_crew INT,
crew_user_enabled BOOLEAN,
PRIMARY KEY(id_user,id_CREW),
FOREIGN KEY (id_user) REFERENCES USER(id),
FOREIGN KEY (id_crew) REFERENCES CREW(id)
);

# PASSWORD_CHANGE TABLE CREATION

CREATE TABLE secondoprogettoweb.PASSWORD_CHANGE(
id VARCHAR(26),
id_user INT,
password_date VARCHAR(30),
PRIMARY KEY(id),
FOREIGN KEY (id_user) REFERENCES USER(id)
);

# INVITE TABLE CREATION

CREATE TABLE secondoprogettoweb.INVITE(
id INT NOT NULL AUTO_INCREMENT,
id_receiver INT,
id_sender INT,
id_crew INT,
invite_enabled BOOLEAN,
PRIMARY KEY(id),
FOREIGN KEY (id_receiver) REFERENCES USER(id),
FOREIGN KEY (id_sender) REFERENCES USER(id),
FOREIGN KEY (id_crew) REFERENCES CREW(id)
);

# POST TABLE CREATION

CREATE TABLE secondoprogettoweb.POST(
id INT NOT NULL AUTO_INCREMENT,
id_writer INT,
id_crew INT,
creation_date VARCHAR(30),
text MEDIUMTEXT,
PRIMARY KEY(id),
FOREIGN KEY (id_writer) REFERENCES USER(id),
FOREIGN KEY (id_crew) REFERENCES CREW(id)
);

# POST FILE CREATION

CREATE TABLE secondoprogettoweb.POST_FILE(
id INT NOT NULL AUTO_INCREMENT,
id_post INT,
name VARCHAR(150),
PRIMARY KEY(id),
FOREIGN KEY (id_post) REFERENCES POST(id)
);

# Inserimento utente gino perna
INSERT INTO secondoprogettoweb.USER(username, password, email, moderator, login_date) VALUES ('gino', 'd9d72431035d8535d1b65ce9a01c2f60','ciao@gino.com',TRUE,'2014-01-09 alle 16:13:21');
INSERT INTO secondoprogettoweb.USER(username, password, email, moderator, login_date) VALUES ('davide', '446fca5553df49ad9c6348cf1ff71d51','davide@example.com',TRUE,'2014-01-09 alle 12:13:21');
INSERT INTO secondoprogettoweb.USER(username, password, email, moderator, login_date) VALUES ('federico', '616706c4d6f7bdf68b30893f860cbb2b','federico@example.com',TRUE,'2014-01-09 alle 21:13:21');
INSERT INTO secondoprogettoweb.USER(username, password, email, moderator, login_date) VALUES ('francesco', '0581938f0767a65b373cea80e905c25f','francesco@example.com',TRUE,'2014-01-09 alle 12:23:21');
INSERT INTO secondoprogettoweb.USER(username, password, email, moderator, login_date) VALUES ('giovanna', '5f4dcc3b5aa765d61d8327deb882cf99','giovanna@example.com',FALSE,'2014-01-09 alle 15:23:21');
INSERT INTO secondoprogettoweb.USER(username, password, email, moderator, login_date) VALUES ('luca', '5f4dcc3b5aa765d61d8327deb882cf99','luca@example.com',FALSE,'2014-01-09 alle 15:13:25');
INSERT INTO secondoprogettoweb.USER(username, password, email, moderator, login_date) VALUES ('maria', '5f4dcc3b5aa765d61d8327deb882cf99','maria@example.com',FALSE,'2014-01-09 alle 15:13:31');
INSERT INTO secondoprogettoweb.USER(username, password, email, moderator, login_date) VALUES ('stefano', '5f4dcc3b5aa765d61d8327deb882cf99','stefano@example.com',FALSE,'2014-01-09 alle 15:13:51');
INSERT INTO secondoprogettoweb.USER(username, password, email, moderator, login_date) VALUES ('luigi', '5f4dcc3b5aa765d61d8327deb882cf99','luigi@example.com',FALSE,'2014-01-09 alle 15:23:21');
INSERT INTO secondoprogettoweb.USER(username, password, email, moderator, login_date) VALUES ('alessandra', '5f4dcc3b5aa765d61d8327deb882cf99','alessandra@example.com',FALSE,'2014-01-09 alle 11:13:21');




# inserimento gruppo pubblico
INSERT INTO secondoprogettoweb.CREW(id_admin,name,description,crew_private,creation_date,crew_enabled) VALUES ('1','[Programmazione per il Web]','Pagina del corso. Qui trovate info e aiuto.','0','2014-01-07 alle 14:06:44','1');
INSERT INTO secondoprogettoweb.CREW(id_admin,name,description,crew_private,creation_date,crew_enabled) VALUES ('2','[Architettura degli Elaboratori]','Pagina del corso. Qui trovate info e aiuto.','0','2014-01-07 alle 14:06:44','1');

# inserimento crew privata
INSERT INTO secondoprogettoweb.CREW(id_admin,name,description,crew_private,creation_date,crew_enabled) VALUES ('3','[Algoritmi e Strutture Dati]','Pagina del corso. Qui trovate info e aiuto.','1','2014-01-02 alle 14:14:00','1');
INSERT INTO secondoprogettoweb.CREW(id_admin,name,description,crew_private,creation_date,crew_enabled) VALUES ('3','[Analisi I]','Pagina del corso. Qui trovate info e aiuto.','1','2014-01-02 alle 14:14:00','1');
INSERT INTO secondoprogettoweb.CREW(id_admin,name,description,crew_private,creation_date,crew_enabled) VALUES ('1','[Analisi II]','Pagina del corso. Qui trovate info e aiuto.','1','2014-01-02 alle 14:14:00','1');

# inserisco associzione crew_utente per test gruppi privati
INSERT INTO secondoprogettoweb.CREW_USER(id_user,id_crew,crew_user_enabled) VALUES ('1','1','1');
INSERT INTO secondoprogettoweb.CREW_USER(id_user,id_crew,crew_user_enabled) VALUES ('2','1','1');

INSERT INTO secondoprogettoweb.CREW_USER(id_user,id_crew,crew_user_enabled) VALUES ('2','2','1');
INSERT INTO secondoprogettoweb.CREW_USER(id_user,id_crew,crew_user_enabled) VALUES ('3','2','1');

INSERT INTO secondoprogettoweb.CREW_USER(id_user,id_crew,crew_user_enabled) VALUES ('1','3','1');
INSERT INTO secondoprogettoweb.CREW_USER(id_user,id_crew,crew_user_enabled) VALUES ('2','3','1');
INSERT INTO secondoprogettoweb.CREW_USER(id_user,id_crew,crew_user_enabled) VALUES ('3','3','1');

INSERT INTO secondoprogettoweb.CREW_USER(id_user,id_crew,crew_user_enabled) VALUES ('4','4','1');
INSERT INTO secondoprogettoweb.CREW_USER(id_user,id_crew,crew_user_enabled) VALUES ('6','4','1');
INSERT INTO secondoprogettoweb.CREW_USER(id_user,id_crew,crew_user_enabled) VALUES ('3','4','1');

INSERT INTO secondoprogettoweb.CREW_USER(id_user,id_crew,crew_user_enabled) VALUES ('1','5','1');
INSERT INTO secondoprogettoweb.CREW_USER(id_user,id_crew,crew_user_enabled) VALUES ('2','5','1');
INSERT INTO secondoprogettoweb.CREW_USER(id_user,id_crew,crew_user_enabled) VALUES ('3','5','1');

# inserisco post per test di show gruppo
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('1','1','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('2','1','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('2','1','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('1','1','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('2','1','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('1','1','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('2','1','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('1','1','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('2','1','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('1','1','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');

INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('3','2','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('3','2','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('2','2','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('3','2','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('2','2','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('3','2','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('2','2','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('3','2','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('2','2','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('3','2','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');

INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('1','3','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('2','3','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('3','3','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('1','3','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('2','3','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('3','3','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('2','3','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('3','3','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('2','3','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('1','3','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');

INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('4','4','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('6','4','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('3','4','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('3','4','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('4','4','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('3','4','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('6','4','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('3','4','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('6','4','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('4','4','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');

INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('1','5','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('2','5','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('3','5','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('1','5','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('2','5','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('3','5','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('2','5','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('1','5','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('3','5','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('1','5','2014-01-01 alle 22:06:34','Bacon ipsum dolor sit amet beef ribs shankle pork belly pork leberkas cow pork loin boudin ham pig tongue landjaeger filet mignon tenderloin frankfurter. Chicken brisket doner biltong, shoulder venison rump. Tongue brisket pig shoulder rump bacon. Hamburger chicken beef pork chop, jowl tail tongue chuck doner spare ribs.');
