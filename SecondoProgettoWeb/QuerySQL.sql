# DROP TABLES

DROP TABLE secondoprogettoweb.POST_FILE;
DROP TABLE secondoprogettoweb.POST;
DROP TABLE secondoprogettoweb.INVITE;
DROP TABLE secondoprogettoweb.PASSWORD_CHANGE;
DROP TABLE secondoprogettoweb.CREW_USER;
DROP TABLE secondoprogettoweb.CREW;
DROP TABLE secondoprogettoweb.USER;

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
id INT,
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
INSERT INTO secondoprogettoweb.USER(username, password, email, moderator, login_date) VALUES ('gino', 'd9d72431035d8535d1b65ce9a01c2f60','ciao@gino.com',TRUE,'26-07-1992');
INSERT INTO secondoprogettoweb.USER(username, password, email, moderator, login_date) VALUES ('davide', '446fca5553df49ad9c6348cf1ff71d51','davide@example.com',TRUE,'26-07-1992');
INSERT INTO secondoprogettoweb.USER(username, password, email, moderator, login_date) VALUES ('federico', '616706c4d6f7bdf68b30893f860cbb2b','federico@example.com',TRUE,'26-07-1992');
INSERT INTO secondoprogettoweb.USER(username, password, email, moderator, login_date) VALUES ('francesco', '0581938f0767a65b373cea80e905c25f','francesco@example.com',TRUE,'26-07-1992');

# inserimento gruppo pubblico
INSERT INTO secondoprogettoweb.CREW(id_admin,name,description,crew_private,creation_date,crew_enabled) VALUES ('1','Public test','descrizione test','0','10/10/10','1');

# inserimento crew privata
INSERT INTO secondoprogettoweb.CREW(id_admin,name,description,crew_private,creation_date,crew_enabled) VALUES ('1','Private test','descrizione test','1','11/11/11','1');

# inserisco associzione crew_utente per test gruppi privati
INSERT INTO secondoprogettoweb.CREW_USER(id_user,id_crew,crew_user_enabled) VALUES ('1','2','1');

# inserisco post per test di show gruppo
INSERT INTO secondoprogettoweb.post(id_writer,id_crew,creation_date,text) VALUES ('1','1','01/03/2013','lore ipsum e porco dio');
