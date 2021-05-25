/*
    $Id: sample.sql,v 1.5 2005/05/02 15:07:27 unsaved Exp $
    Examplifies use of SqlTool.
    PCTASK Table creation
*/

/* Ignore error for these two statements */
\c true
DROP TABLE ADDRESS;
DROP TABLE MEMBER;
DROP TABLE CATEGORY;
DROP TABLE WRITER;
DROP TABLE BOOK;
\c false



\p Creating table ADDRESS
CREATE TABLE ADDRESS (
        POSTALCODE varchar,
        STREET varchar,
        NUMBER varchar,
        CITY varchar
);


\p Creating table MEMBER
CREATE TABLE MEMBER (
		IDMEMBER integer identity PRIMARY KEY ,
        FIRSTNAME varchar,
        LASTNAME varchar,
        PHONENUMBER varchar,
        EMAIL varchar,
        UNIQUE (IDMEMBER)
);


\p Creating table CATEGORY
CREATE TABLE CATEGORY (
		IDCATEGORY integer identity PRIMARY KEY ,
        GENRE varchar,
        UNIQUE (IDCATEGORY)
);

\p Creating table WRITER
CREATE TABLE WRITER (
		IDWRITER integer identity PRIMARY KEY ,
        FIRSTNAME varchar,
        LASTNAME varchar,
        BIOGRAPHY varchar,
        UNIQUE (IDWRITER)
);


\p Creating table BOOK
CREATE TABLE BOOK (
		IDBOOK integer identity PRIMARY KEY ,
        ISBN varchar,
        TITLE varchar,
        LANGUAGE varchar,
        DESCRIPTION varchar,
        NUMBEROFPAGES integer,
        UNIQUE (IDBOOK)
);


\p Inserting test records


INSERT INTO ADDRESS(POSTALCODE, STREET, NUMBER,CITY) VALUES (
'1950', 'Place des Rats',4,'Sion');


INSERT INTO MEMBER(FIRSTNAME, LASTNAME, IDMEMBER, PHONENUMBER,EMAIL) VALUES (
'Kevin', 'Coppey',2,'0798543125', 'kevin@yahoo.it');

INSERT INTO ADDRESS(POSTALCODE, STREET, NUMBER,CITY) VALUES (
'1950', 'Chemin du Desespoir',8,'Sion');

INSERT INTO MEMBER(FIRSTNAME, LASTNAME, IDMEMBER, PHONENUMBER,EMAIL) VALUES (
'Thomas', 'Luyet',3,'0798483545', 'thomas@hes.ch');

INSERT INTO CATEGORY(IDCATEGORY,GENRE) VALUES (
10,'Horreur');

INSERT INTO CATEGORY(IDCATEGORY,GENRE) VALUES (
20,'Romance');


INSERT INTO WRITER(IDWRITER, FIRSTNAME, LASTNAME, BIOGRAPHY) VALUES (
13,'Edgar Alan','Poe','Auteur de l excellent Hello Kitty magazine');

INSERT INTO WRITER(IDWRITER, FIRSTNAME, LASTNAME, BIOGRAPHY) VALUES (
40,'Maverick','O Banen','Le seul, l unique');

INSERT INTO BOOK(IDBOOK, ISBN, TITLE, LANGUAGE, DESCRIPTION, NUMBEROFPAGES) VALUES (
1,'201f', 'La Souris','Francais','Un drame sordide',300);

INSERT INTO BOOK(IDBOOK, ISBN, TITLE, LANGUAGE, DESCRIPTION, NUMBEROFPAGES) VALUES (
2,'202f', 'Le Chat','Francais','Une histoire d amour',150);	
	
commit;
