source hobbylistdb-schema.sql;

insert into users values('ruben', MD5('ruben'), 'Ruben', 'ruben@acme.com');
insert into user_roles values ('ruben', 'admin');

insert into users values('marc', MD5('marc'), 'Marc', 'marc@acme.com');
insert into user_roles values ('marc', 'registered');

insert into genre (genrename) values ('shooter');
insert into genre (genrename) values ('plataformas');
insert into genre (genrename) values ('rpg');
insert into genre (genrename) values ('puzle');
insert into genre (genrename) values ('accion');
insert into genre (genrename) values ('estrategia');
insert into genre (genrename) values ('deportes');
insert into genre (genrename) values ('conduccion');

insert into platforms (platformname) values ('ps3');
insert into platforms (platformname) values ('ps4');
insert into platforms (platformname) values ('3ds');
insert into platforms (platformname) values ('wii u');
insert into platforms (platformname) values ('psvita');
insert into platforms (platformname) values ('xbox360');
insert into platforms (platformname) values ('xboxone');

insert into games (username, title, synopsis, genreid, company, year, imageurl) values ('ruben', 'Paper Mario', 'Mario con pegatinas',2,'nintendo','2011','https://cdn02.nintendo-europe.com/media/images/05_packshots/games_13/nintendo_3ds_6/PS_3DS_PaperMarioStickerStar_enGB.png');
insert into games (username, title, synopsis, genreid, company, year, imageurl) values ('ruben', 'Tomb Raider', 'Mola porque puedes encerrar al mayordomo en el frigorifico',5,'eidos','1999','http://img.gamefaqs.net/box/5/4/2/174542_front.jpg');
insert into games (username, title, synopsis, genreid, company, year, imageurl) values ('ruben', 'Star Ocean 4', 'El mejor RPG de la anterior generación',3,'TriAce','2009','https://upload.wikimedia.org/wikipedia/ru/8/88/Star_Ocean_The_Last_Hope_PS3_Cover.jpg');
insert into games (username, title, synopsis, genreid, company, year, imageurl) values ('ruben', 'Final Fantasy XV', 'No se de que va porque no ha salido y nunca lo hara',3,'SquareEnix','2020','http://i1063.photobucket.com/albums/t520/jamesrob12/ff15boxps4_zps87bec7c0.jpg');
insert into games (username, title, synopsis, genreid, company, year, imageurl) values ('ruben', 'Metal Gear 4', 'Tiros y sigilo, en ese orden, como los pros',5,'Konami','2008','http://i11c.3djuegos.com/juegos/1421/metal_gear_solid_4_guns_of_the_patriots/fotos/ficha/metal_gear_solid_4_guns_of_the_patriots-1686810.jpg');
insert into games (username, title, synopsis, genreid, company, year, imageurl) values ('ruben', 'Kirby', 'Kirby se come lo que sea y consigue sus poderes',2,'Nintendo','1992','https://pbs.twimg.com/media/B2Phpd6CAAAhaf5.png');
insert into games (username, title, synopsis, genreid, company, year, imageurl) values ('ruben', 'Borderlands', 'Recorre Pandora completando misiones y disparando a todo lo que se mueva',1,'Gearbox','2009','http://upload.wikimedia.org/wikipedia/en/0/01/Borderlandscover.jpg');

insert into platformsgames values (1,3);
insert into platformsgames values (2,1);
insert into platformsgames values (2,2);
insert into platformsgames values (2,6);
insert into platformsgames values (2,7);
insert into platformsgames values (3,1);
insert into platformsgames values (3,7);
insert into platformsgames values (4,2);
insert into platformsgames values (4,6);
insert into platformsgames values (5,1);
insert into platformsgames values (6,3);
insert into platformsgames values (6,4);
insert into platformsgames values (7,1);
insert into platformsgames values (7,2);
insert into platformsgames values (7,5);
insert into platformsgames values (7,6);
insert into platformsgames values (7,7);

insert into favs (gameid, username, rank) values (1, 'ruben', 9);
insert into favs (gameid, username, rank) values (2, 'ruben', 9);
insert into favs (gameid, username, rank) values (3, 'ruben', 9);
insert into favs (gameid, username, rank) values (4, 'ruben', 6);
insert into favs (gameid, username, rank) values (5, 'ruben', 9);
insert into favs (gameid, username, rank) values (6, 'ruben', 9);
insert into favs (gameid, username, rank) values (1, 'marc', 8);
insert into favs (gameid, username, rank) values (2, 'marc', 6);
insert into favs (gameid, username, rank) values (4, 'marc', 5);
insert into favs (gameid, username, rank) values (5, 'marc', 7);
insert into favs (gameid, username, rank) values (6, 'marc', 7);

insert into state (statename) values ('accepted');
insert into state (statename) values ('reject');
insert into state (statename) values ('pendent');

insert into messages (sender, receiver, subject, content) values ('ruben', 'marc', 'hola', 'que tal');
insert into messages (sender, receiver, subject, content) values ('ruben', 'marc', 'blabla', 'me comi a mi gemelo en el utero');
insert into messages (sender, receiver, subject, content) values ('ruben', 'marc', 'hola', 'caracola');
insert into messages (sender, receiver, subject, content) values ('ruben', 'marc', 'lerele', 'yippee ki yay');
insert into messages (sender, receiver, subject, content) values ('marc', 'ruben', 'hola', 'la vaca hace muuuuu');