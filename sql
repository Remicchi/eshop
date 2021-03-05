sql formatting

list of tables

games
orders
customer


games
id title developer genre qty

DROP TABLE IF EXISTS games;
CREATE TABLE games (
id int,
title varchar(50),
developer varchar(50),
genre varchar(50),
price int DEFAULT 10,
qty int DEFAULT 0,
PRIMARY KEY (id)
);

insert into games values (1 , 'fun game lol' , 'bean company' , 'Action' , 20 , 11);
insert into games values (2 , 'hehe game 2' , 'bean company' , 'Shooting' , 20 , 22);
insert into games values (3 , 'not a game' , 'bean company' , 'Action' , 3 , 33);
insert into games values (4 , 'aa' , 'we sell game' , 'Shooting' , 2 , 44);
insert into games values (5 , 'game 5' , 'EA' , 'Action' , 1 , 55);

SELECT * FROM games;

orders
id game-id customer-id quantity

customers
id name email number

