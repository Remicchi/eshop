sql formatting

games
id title developer genre qty

orders
id gameid customerid quantity

customers
id name email address username password 

DROP TABLE IF EXISTS games;
CREATE TABLE games (
id int NOT NULL AUTO_INCREMENT,
title varchar(50),
developer varchar(50),
genre varchar(50),
price int DEFAULT 10,
qty int DEFAULT 0,
PRIMARY KEY (id)
);

insert into games values (1 , 'Fun game lol' , 'Bean Company' , 'Action' , 20 , 11);
insert into games values (2 , 'Hehe game 2' , 'Bean Company' , 'Shooting' , 20 , 22);
insert into games values (3 , 'Not a game' , 'Bean Company' , 'Action' , 19 , 33);
insert into games values (4 , 'Another game' , 'Game Company 2' , 'Shooting' , 26.50 , 44);
insert into games values (5 , 'Game 5' , 'EA Sports' , 'Action' , 99 , 55);
insert into games values (6 , 'Sport Game 6' , 'EA Sports' , 'Action' , 19 , 55);
insert into games values (7 , 'Fast Game' , 'Toyoto' , 'Racing' , 19 , 55);
insert into games values (8 , 'Speed Guy' , 'Game Company 2' , 'Racing' , 99 , 55);
insert into games values (9 , 'Fast Game 1' , 'Toyoto' , 'Racing' , 49 , 55);


DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
id int NOT NULL AUTO_INCREMENT,
gameid int,
customerid int,
qtyordered int,
PRIMARY KEY (id)
);

DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
id int NOT NULL AUTO_INCREMENT,
name varchar(50),
email varchar(50),
address varchar(50),
username varchar(50),
password varchar(50),
PRIMARY KEY (id)
);

INSERT INTO customers values(1 , 'Bob' , 'bob@gmail.com' , "Bob's House" , 'bob123' , '123456');
INSERT INTO customers values(2 , 'Cat' , 'cat@gmail.com' , 'Cat Country' , 'cat' , 'ilikecats');
INSERT INTO customers values(3 , 'Dog' , 'dog@gmail.com' , 'Dog Land' , 'dog' , 'ilikedogs');
INSERT INTO customers values(4 , 'Emily' , 'Emily@gmail.com' , 'Emily Road' , 'Emily' , 'password');
INSERT INTO customers values(5 , 'A Person' , 'Person@gmail.com' , 'Address Road' , 'aperso' , 'apassword');
INSERT INTO customers values(6 , 'Test Boi' , 'test@gmail.com' , 'Test Lane' , 'test' , '123');

CREATE VIEW ViewOrders AS 
SELECT orders.id , customers.name as customer_name , games.title as game_title, orders.qtyordered 
FROM orders 
JOIN customers ON orders.customerid = customers.id
JOIN games ON orders.gameid = games.id;

SELECT * FROM games;
SELECT * FROM orders;
SELECT * FROM customers;