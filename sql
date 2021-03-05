sql formatting

games
id title developer genre qty

orders
id gameid customerid quantity

customers
id name email

accounts
id username password hpnumber address

DROP TABLE IF EXISTS accounts;
CREATE TABLE (
id int NOT NULL AUTO_INCREMENT,
username varchar(50),
password varchar(50),
hpnumber int,
address varchar(50),
PRIMARY KEY (id)
);

insert into games values (1, 'admin', 'ballsdeep69', 99126929, 'UpYourAssTurnRight Ave');

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
insert into games values (3 , 'Not a game' , 'Bean Company' , 'Action' , 3 , 33);
insert into games values (4 , 'AAAAA' , 'Game Company 2' , 'Shooting' , 2 , 44);
insert into games values (5 , 'Game 5' , 'EA Sports' , 'Action' , 1 , 55);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
id int NOT NULL AUTO_INCREMENT,
gameid int,
customerid int,
qtyordered int,
PRIMARY KEY (id)
);

INSERT INTO orders values(1 , 2 , 2 , 1);
INSERT INTO orders values(2 , 1 , 2 , 2);
INSERT INTO orders values(3 , 1 , 2 , 3);
INSERT INTO orders values(4 , 3 , 3 , 1);
INSERT INTO orders values(5 , 2 , 1 , 22);

DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
id int NOT NULL AUTO_INCREMENT,
name varchar(50),
email varchar(50),
PRIMARY KEY (id)
);

INSERT INTO customers values(1 , 'Bob' , 'bob@gmail.com');
INSERT INTO customers values(2 , 'Cat' , 'cat@gmail.com');
INSERT INTO customers values(3 , 'Dog' , 'dog@gmail.com');
INSERT INTO customers values(4 , 'Emily' , 'Emily@gmail.com');
INSERT INTO customers values(5 , 'A Person' , 'Person@gmail.com');

SELECT * FROM games;
SELECT * FROM orders;
SELECT * FROM customers;
