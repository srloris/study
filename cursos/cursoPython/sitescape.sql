create database sitescape;

use sitescape;

create table SS_Musics(
	id int primary key auto_increment,
    name varchar(100) not null,
    author varchar(100) not null,
    genre_id int not null
);

create table SS_Users(
	id int primary key auto_increment,
    name varchar(100) not null,
    user varchar(100) not null unique,
    password varchar(100) not null
);

create table SS_Genres(
	id int primary key auto_increment,
	name varchar(100) not null unique
);

ALTER TABLE SS_Musics ADD CONSTRAINT 
FOREIGN KEY (genre_id)
REFERENCES SS_Genres (id);

insert into SS_Genres(name) values
("MPB");

insert into SS_Musics (name, author, genre_id) values
("Pais e Filhos", "Legião Urbana", 1);