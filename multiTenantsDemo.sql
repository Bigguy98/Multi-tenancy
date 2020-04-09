# each Databse should have this
CREATE TABLE book (
	book_id varchar(20),	
	title varchar(50) CONSTRAINT title_not_null NOT NULL,
	author varchar(50) CONSTRAINT author_not_null NOT NULL,
	publishYear number,
	rating number,
	CONSTRAINT book_pk PRIMARY KEY (book_id),
	CONSTRAINT check_rating CHECK (rating IS NULL OR (rating >=1 and rating <=10))
);
CREATE TABLE reader (
	reader_id varchar(20),
	name varchar(50) CONSTRAINT reader_name_not_null NOT NULL,
	age number CONSTRAINT age_not_null NOT NULL,
	email varchar(50),
	address varchar(50),
	CONSTRAINT reader_pk PRIMARY KEY (reader_id)
);

create sequence SEQUENCE_GENERATOR
increment by 20 
start with 10
/


# only in Default Database
CREATE TABLE DATASOURCECONFIG (
   id bigint PRIMARY KEY,
   driverclassname varchar(255),
   url varchar(255),
   name varchar(255),
   username varchar(255),
   password varchar(255),
   initialize BOOLEAN
);