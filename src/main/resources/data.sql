INSERT INTO USERS(password,user_name,active) VALUES ('123','sencerseven',1);
INSERT INTO USERS_DETAIL(description,profile_img,user_id) VALUES('deneme yazi','https://www.jeancoutu.com/globalassets/revamp/photo/conseils-photo/20160302-01-reseaux-sociaux-profil/image-principale-919b9d.png','1');
INSERT INTO ROLE (role) VALUES ('ADMIN');

INSERT INTO USER_ROLE(user_id,role_id) VALUES (1,1);

INSERT INTO PARAMETER(key,value) VALUES ('ABOUT','Bahçeşehir Üniversitesi Yazılım Mühendisliği mezunuyum java aşoğıyım.');