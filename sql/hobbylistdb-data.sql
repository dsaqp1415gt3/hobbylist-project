source hobbylistdb-schema.sql;

insert into users values('ruben', MD5('ruben'), 'Ruben', 'ruben@acme.com');
insert into user_roles values ('ruben', 'registered');

insert into users values('marc', MD5('marc'), 'Marc', 'marc@acme.com');
insert into user_roles values ('marc', 'registered');

insert into hobbies (classification, title, synopsis, genre) values ('Movie', 'Matrix', 'Una peli muy buena con tiros y camaras lentas','accion');
insert into hobbies (classification, title, synopsis, genre) values ('Movie', 'El libro de la selva', 'Drama Disney donde un niño vive en la selva pero no tiene dinero para comprarse un libro','animacion');
insert into hobbies (classification, title, synopsis, genre) values ('Movie', 'Robin Hood', 'Robin Hood, no tiene mucho misterio','accion');
insert into hobbies (classification, title, synopsis, genre) values ('Movie', 'Harry Potter 4', 'Harry es un mago al que su mejor amigo le roba la niña repelente que hoy en dia esta mu buena','fantasia');
insert into hobbies (classification, title, synopsis, genre) values ('Movie', 'Saint Seiya', 'Dame tu fuerza, Pegaso!','shonen');
insert into hobbies (classification, title, synopsis, genre) values ('Movie', 'Babe, el cerdito valiente', 'Peliculon sobre la superacion y la confianza en uno mismo','comedia');
insert into hobbies (classification, title, synopsis, genre) values ('Movie', 'Kill Bill vol1', 'Tarantino + Katanas = <3','accion');
insert into hobbies (classification, title, synopsis, genre) values ('Movie', 'Top Gun', 'Tu ego extiende cheques que tu cuerpo no puede pagar','accion');
insert into hobbies (classification, title, synopsis, genre) values ('Book', 'Battle Royale', 'El gobierno envia 50 chavales a una isla para que se maten entre ellos','cienciaficcion');
insert into hobbies (classification, title, synopsis, genre) values ('Book', 'Apocalipsis', 'Fin del mundo de la pluma de Stephen King','terror');
insert into hobbies (classification, title, synopsis, genre) values ('Book', 'Diez negritos', 'Diez desconocidos son citados en una mansion apartada en una isla abandonada. Van muriendo uno a uno tal como describe una cancion infantil','intriga');
insert into hobbies (classification, title, synopsis, genre) values ('Book', 'El Hobbit', 'Un hobbit y un anillo','fantasia');
insert into hobbies (classification, title, synopsis, genre) values ('Game', 'Star Ocean', 'El mejor RPG de la anterior generación','rpg');
insert into hobbies (classification, title, synopsis, genre) values ('Game', 'Final Fantasy XV', 'No se de que va porque no ha salido y nunca lo hara','actionrpg');
insert into hobbies (classification, title, synopsis, genre) values ('Game', 'Metal Gear 4', 'Tiros y sigilo, en ese orden, como los pros','sigilo');
insert into hobbies (classification, title, synopsis, genre) values ('Game', 'Kirby', 'Kirby se come lo que sea y consigue sus poderes','plataformas');
insert into hobbies (classification, title, synopsis, genre) values ('Game', 'Borderlands', 'Recorre Pandora completando misiones y disparando a todo lo que se mueva','shooterrpg');

insert into lists (hobbyid, username, tag, rank) values ('1', 'ruben', 'Visto', '9');
insert into lists (hobbyid, username, tag, rank) values ('3', 'ruben', 'Visto', '9');
insert into lists (hobbyid, username, tag, rank) values ('9', 'ruben', 'Visto', '9');
insert into lists (hobbyid, username, tag, rank) values ('14', 'ruben', 'Pendiente', '0');
insert into lists (hobbyid, username, tag, rank) values ('15', 'ruben', 'Visto', '9');
insert into lists (hobbyid, username, tag, rank) values ('17', 'ruben', 'Visto', '9');
insert into lists (hobbyid, username, tag, rank) values ('1', 'marc', 'Visto', '8');
insert into lists (hobbyid, username, tag, rank) values ('4', 'marc', 'Pendiente', '6');
insert into lists (hobbyid, username, tag, rank) values ('8', 'marc', 'Visto', '5');
insert into lists (hobbyid, username, tag, rank) values ('13', 'ruben', 'Pendiente', '0');
insert into lists (hobbyid, username, tag, rank) values ('16', 'ruben', 'Pendiente', '0');

insert into messages (sender, receiver, subject, content) values ('ruben', 'marc', 'hola', 'que tal');
insert into messages (sender, receiver, subject, content) values ('ruben', 'marc', 'blabla', 'me comi a mi gemelo en el utero');
insert into messages (sender, receiver, subject, content) values ('ruben', 'marc', 'hola', 'caracola');
insert into messages (sender, receiver, subject, content) values ('ruben', 'marc', 'lerele', 'yippee ki yay');
insert into messages (sender, receiver, subject, content) values ('marc', 'ruben', 'hola', 'la vaca hace muuuuu');