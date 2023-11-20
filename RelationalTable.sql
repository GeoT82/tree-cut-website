drop database if exists testdb;
create database testdb;
use testdb;
Create TABLE if not exists User(
  clientID int(4) auto_increment, 
  phoneNumber varchar(13) not null default '111-222-3333', 
  email varchar(50) not null, 
  creditCard int(16) not null default '0000000000000000', 
  firstName varchar(20) not null, 
  lastName varchar(20) not null,
  password varchar(20) not null,
  PRIMARY KEY (clientID)
);
alter table User auto_increment = 100;
INSERT INTO User (phoneNumber, email, creditCard, firstName, lastName, password)
VALUES 
( 1234567890, 'johnsmith@gmail.com', 11111111, 'John', 'Smith', '1234'),
( 4353536377, 'joerey@gmail.com', 22222222, 'Joe', 'Rey', '1234'),
( 6456478874, 'jameswhite@gmail.com', 33333333, 'James', 'White' , '1234'),
( 1124435677, 'aaronrodgers@gmail.com', 44444444, 'Aaron', 'Rodgers' , '1234'),
( 9898654463, 'johndoe@gmail.com', 55555555, 'John', 'Doe' , '1234'),
( 8478976444, 'barbakew@gmail.com', 66666666, 'Barb', 'Akew' , '1234'),
( 4645735282, 'oliveyew@gmail.com', 77777777, 'Olive', 'Yew' , '1234'),
( 0902848747, 'noahlyles@gmail.com', 88888888, 'Noah', 'Lyles' , '1234'),
( 1235134356, 'victorabu@gmail.com', 99999999, 'Victor', 'Abu', '1234'),
( 6546534262, 'Lukmanace@gmail.com', 00000000, 'Lukman', 'Ace', '1234'),
( 2345234234, 'davidSmith@gmail.com', 2142552, 'David', 'Smith', 'ds1234'),
( 1234566757, 'susie@gmail.com', '12341234', 'Susie ', 'Guzman', 'susie1234'),
( 7777888899, 'root', 00000000, 'default', 'default','pass1234');


SET FOREIGN_KEY_CHECKS = 0;
Create TABLE if not exists Bill(
  billID int not null auto_increment, 
  clientNote varchar(150) default 'pending', 
  smithNote varchar(150) default 'pending',
  price double(10,2) not null default 0,
  quoteID int not null default 0,
  clientID int not null default 0, 
  issueDate datetime not null default '1990-01-31 10:24:40', 
  dueDate datetime not null default '1990-02-24 10:24:40',
  payStatus boolean not null default false,
  PRIMARY KEY (billID),
  Foreign key (quoteID) references Quote(quoteID),
  Foreign key (clientID) references User(clientID)
);
alter table Bill auto_increment = 10;
INSERT INTO Bill(clientNote, SmithNote, quoteID)
VALUES 
( 'Sold!', '', 2),
( 'Sold!', '', 4),
( 'Sold!', '',3),
( 'Sold!', '',4),
( 'Sold!', '',1),
( 'Sold!', '',2),
( 'Sold!', '',5),
( 'Sold!', '',6),
( 'Sold!', '',6),
( 'Sold!', '',1);
SET FOREIGN_KEY_CHECKS = 1;

SET FOREIGN_KEY_CHECKS = 0;
Create TABLE if not exists Quote(
  quoteID int not null auto_increment, 
  clientNote varchar(150) default 'pending', 
  smithNote varchar(150) default 'pending', 
  times time not null default '1:00:00', 
  price double not null default '0.00',
  requestID int not null default '0',
  clientID int not null default '0', 
  issueDate datetime not null default '1990-01-31 10:24:40', 
  PRIMARY KEY (QuoteID),
  Foreign key (requestID) references Request(requestID),
  foreign key (clientID) references User(clientID)
);
alter table Quote auto_increment = 20;
INSERT INTO Quote( clientNote , smithNote, times, price, requestID, clientID)
VALUES 
( 'Sold!', '', '1:00:00', 4500, 200, 111),
( 'Sold!', '', '2:00:00', 5500, 201, 111),
( 'Sold!', '', '3:00:00', 6500, 204, 111),
( 'Sold!', '', '4:00:00', 7500, 4, 109),
( 'Sold!', '', '5:00:00', 8500, 5, 110),
( 'Sold!', '', '6:00:00', 9500, 6, 100),
( 'Sold!', '', '7:00:00', 10500, 7, 103),
( 'Sold!', '', '8:00:00', 11500, 8, 105),
( 'Sold!', '', '9:00:00', 12500, 9, 104),
( 'Sold!', '', '10:00:00', 13500, 210, 111);
SET FOREIGN_KEY_CHECKS = 1;

SET FOREIGN_KEY_CHECKS = 0;
Create TABLE if not exists Request(
  requestID int not null auto_increment,
  quoteID int not null default 0,
  clientID int not null default 0, 
  clientNote varchar(30) default 'pending',  
  smithNote varchar(30) default 'pending',
  issueDate datetime not null default '1990-01-31 10:24:40', 
  PRIMARY KEY (requestID),
  foreign key (quoteID) references Quote(quoteID),
  foreign key (clientID) references User(clientID)
);
alter table Request auto_increment = 200;
INSERT INTO Request(clientNote, smithNote, clientID, quoteID, issueDate)
VALUES 
( 'Sold!', '', 111, 20, '2020-04-16 06:53:40'),
( 'Sold!', '', 111, 21, '2022-07-16 06:53:40'),
( 'Sold!', '', 108, default, default),
( 'Sold!', '', 109, default, default),
( 'Sold!', '', 111, 22, default),
( 'Sold!', '', 100, default, default),
( 'Sold!', '', 111, default, default),
( 'Sold!', '', 112, default, default),
( 'Sold!', '', 107, default, default),
( 'Sold!', '', 106, default, default),
( 'Sold!', '', 111, 29, '2021-03-16 06:53:40'),
( 'Sold!', '', 111, 21, '2020-09-16 06:53:40');
SET FOREIGN_KEY_CHECKS = 1;

Create TABLE if not exists Tree(
  treeID int not null auto_increment, 
  requestID int not null default 0,
  distance double (12,2) not null default '0', 
  width double (12,2) not null default '0',
  height double (12,2) not null default '0', 
  address varchar(30) not null default 'unknown', 
  image1 varchar(30) not null default 'blank.png', 
  image2 varchar(30) not null default 'blank.png', 
  image3 varchar(30) not null default 'blank.png',
  cutStatus boolean not null default false,
  PRIMARY KEY (treeID),
  FOREIGN KEY (requestID) REFERENCES Request(requestID)
);
alter table Tree auto_increment = 500;
SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO Tree(distance, width, height, address, image1, image2, image3, requestID)
VALUES 
( 5, 123, 123, 'Detroit', 'a', 'b', 'c',200),
( 10, 232, 123, 'Detroit', 'a', 'd', 'e',200),
( 15, 121, 123, 'Detroit', 'h', 'g', 'f',201),
( 20, 180, 123, 'Detroit', 'i', 'j', 'k',201),
( 25, 280, 123, 'Detroit', 'n', 'm', 'l',204),
( 30, 321, 123, 'Detroit', 'o', 'p', 'q',204),
( 35, 213, 123, 'Detroit', 't', 's', 'r',210),
( 40, 91, 123, 'Detroit', 'u', 'b', 't',210),
( 50, 32, 15, 'Detroit', 'f', 'r', 'i',211),
( 45, 145, 123, 'Detroit', 'v','y', 's',210);
SET FOREIGN_KEY_CHECKS = 1;


SET FOREIGN_KEY_CHECKS = 0;
Create TABLE if not exists RequestResponse(
  replyRID int not null auto_increment,
  requestID int not null default 0,
  clientID int not null default 0,  
  reply varchar(50) not null default '',
  issueDate datetime not null default '1990-01-31 10:24:40', 
  PRIMARY KEY (replyRID),
  foreign key (requestID) references Request(requestID),
  foreign key (clientID) references User(clientID)
);
alter table RequestResponse auto_increment = 600;
insert into RequestResponse(requestID, clientID, reply)
values (21, 102, 'pass'),
(21, 102, 'Looks Good'),
(21, 102, 'Nevermind'),
(24, 105, 'OK'),
(24, 107, 'Where?'),
(25, 108, 'Too Expensive'),
(27, 109, 'Ill check'),
(28, 110, 'Thanks'),
(29, 111, 'Alright');
SET FOREIGN_KEY_CHECKS = 1;


SET FOREIGN_KEY_CHECKS = 0;
Create TABLE if not exists QuoteResponse(
  replyQID int not null auto_increment,
  quoteID int not null default 0,
  clientID int not null default 0,  
  reply varchar(50) not null default '',
  issueDate datetime not null default '1990-01-31 10:24:40', 
  PRIMARY KEY (replyQID),
  foreign key (quoteID) references Quote(quoteID),
  foreign key (clientID) references User(clientID)
);
alter table QuoteResponse auto_increment = 500;
insert into QuoteResponse(quoteID, clientID, reply)
values (21, 102, 'pass'),
(21, 102, 'Looks Good'),
(21, 102, 'Nevermind'),
(24, 105, 'OK'),
(24, 107, 'Where?'),
(25, 108, 'Too Expensive'),
(27, 109, 'Ill check'),
(28, 110, 'Thanks'),
(29, 111, 'Alright');
SET FOREIGN_KEY_CHECKS = 1;

SET FOREIGN_KEY_CHECKS = 0;
Create TABLE if not exists BillResponse(
  replyBID int not null auto_increment,
  billID int not null default 0,
  clientID int not null default 0,  
  reply varchar(50) not null default '',
  issueDate datetime not null default '1990-01-31 10:24:40', 
  PRIMARY KEY (replyBID),
  foreign key (billID) references Bill(billID),
  foreign key (clientID) references User(clientID)
);
alter table BillResponse auto_increment = 700;
insert into BillResponse(billID, clientID, reply)
values (21, 102, 'pass'),
(21, 102, 'Looks Good'),
(21, 102, 'Nevermind'),
(24, 105, 'OK'),
(24, 107, 'Where?'),
(25, 108, 'Too Expensive'),
(27, 109, 'Ill check'),
(28, 110, 'Thanks'),
(29, 111, 'Alright');
SET FOREIGN_KEY_CHECKS = 1;

SET FOREIGN_KEY_CHECKS = 0;
insert into Request(clientID, clientNote, issueDate) values (111, 'hi', '2023/11/16 13:44:13');
SET FOREIGN_KEY_CHECKS = 1;

select * from User;
select * from Bill;
select * from Request;
select * from Quote;
select * from Tree;
select * from RequestResponse;
select * from QuoteResponse;
select * from BillResponse;
