drop database if exists hobbylistdb;
create database hobbylistdb;
 
use hobbylistdb;
 
create table users (
	username				varchar(20) not null primary key,
	userpass				char(32) not null,
	name					varchar(70) not null,
	email					varchar(255) not null
);
 
create table user_roles (
	username				varchar(20) not null,
	rolename 				varchar(20) not null,
	foreign key(username) 	references users(username) on delete cascade,
	primary key (username, rolename)
);

create table genre (
	genreid					int not null primary key auto_increment,
	genrename				varchar(20) not null
);

create table platforms (
	platformid				int not null primary key auto_increment,
	platformname			varchar(20) not null
);

create table games (
	gameid	 				int not null primary key auto_increment,
	username				varchar(20) not null,
	title 					varchar(100) not null,	
	synopsis				varchar(500) not null,
	genreid					int not null,
	company					varchar(100),
	year					varchar(20) not null,
	imageurl				varchar(200) not null,
	creation_timestamp		datetime not null default current_timestamp,
	foreign key(genreid)	references genre(genreid)
);

create table platformsgames (
	gameid					int not null,
	platformid				int not null,
	foreign key (gameid) 		references games(gameid),
	foreign key (platformid) 	references platforms(platformid),
	primary key (gameid, platformid)
);

create table favs (
	favid					int not null primary key auto_increment,
	gameid					int not null,
	username				varchar(20) not null,
	rank 					int,
	foreign key(gameid) 	references games(gameid),
	foreign key(username) 	references users(username)
);

create table state (
	stateid					int not null primary key auto_increment,
	statename				varchar(20) not null
);

create table invites (
	invid		 			int not null primary key auto_increment,
	sender					varchar(20) not null,
	receiver				varchar(20) not null,
	gameid 					int not null,
	stateid					int not null,
	creation_timestamp		datetime not null default current_timestamp,
	foreign key(sender)		references users(username),
	foreign key(receiver)	references users(username),
	foreign key (gameid) 	references games(gameid),
	foreign key(stateid)	references state(stateid)
);
