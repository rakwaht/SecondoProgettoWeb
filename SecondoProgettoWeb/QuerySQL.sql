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
username CHAR(30) NOT NULL UNIQUE,
password VARCHAR(30) NOT NULL,
avatar_name VARCHAR(50),
email VARCHAR(45) UNIQUE,
moderator BOOLEAN DEFAULT false,
login_date VARCHAR(30),
PRIMARY KEY(id)
);

# CREW TABLE CREATION

CREATE TABLE secondoprogettoweb.CREW(
id INT NOT NULL AUTO_INCREMENT,
id_admin INT,
name CHAR(40),
description VARCHAR(150),
private BOOLEAN,
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
id INT,
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
id INT,
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
id INT,
id_post INT,
name VARCHAR(50),
PRIMARY KEY(id),
FOREIGN KEY (id_post) REFERENCES POST(id)
);

INSERT INTO secondoprogettoweb.USER(username, password) VALUES ('gino', 'perna');