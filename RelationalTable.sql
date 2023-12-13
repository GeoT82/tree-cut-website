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
  cutDate datetime default null, 
  PRIMARY KEY (treeID),
  FOREIGN KEY (requestID) REFERENCES Request(requestID)
);
alter table Tree auto_increment = 500;

INSERT INTO Tree(distance, width, height, address, image1, image2, image3, requestID, cutDate, cutStatus)
VALUES 
( 5, 123, 334, 'Detroit', 'a', 'b', 'c',204, default, default),
( 10, 232, 522, 'Detroit', 'a', 'd', 'e',206, default, default),
( 15, 121, 402, 'Detroit', 'h', 'g', 'f',202, '2022-09-19 7:24:40', true),
( 20, 180, 903, 'Detroit', 'i', 'j', 'k',208, default, default),
( 25, 280, 346, 'Detroit', 'n', 'm', 'l',210, default, default),
( 30, 321, 108, 'Detroit', 'o', 'p', 'q',207, '2022-08-19 7:24:40', true),
( 35, 213, 14, 'Detroit', 't', 's', 'r',205,  '2022-10-22 8:15:30', true),
( 40, 91, 55, 'Detroit', 'u', 'b', 't',206, default, default),
( 50, 32, 877, 'Detroit', 'f', 'r', 'i',210, default, default),
( 45, 145, 557, 'Detroit', 'v','y', 's',202, '2022-05-23 10:14:00', true);
SET FOREIGN_KEY_CHECKS = 1;

SET FOREIGN_KEY_CHECKS = 0;
Create TABLE if not exists Request(
  requestID int not null auto_increment,
  quoteID int not null default 0,
  clientID int not null default 0,
  treeCount int not null default 0,
  clientNote varchar(30) default 'pending',  
  smithNote varchar(30) default 'pending',
  issueDate datetime not null default '1990-01-31 10:24:40', 
  PRIMARY KEY (requestID),
  foreign key (quoteID) references Quote(quoteID),
  foreign key (clientID) references User(clientID)
);
alter table Request auto_increment = 200;
INSERT INTO Request(clientNote, smithNote, clientID, quoteID, issueDate, treeCount)
VALUES 
( 'Added Tree', 'Ok', 100, 20, '2020-04-16 06:53:40', 0),
( 'Wait for One more tree', 'Alright', 102, 21, '2022-07-16 07:23:40', 0),
( 'Looks Good!', 'Thanks', 102, 23, '2022-05-16 09:52:40', 2),
( 'What does distance mean!', 'From home', 103, 23, '2022-02-16 07:34:40', 0),
( 'Who is this!', 'David', 108, 22, '2022-08-16 08:51:40', 1),
( '3 big trees!', 'Wow', 101, 24, '2021-10-16 10:14:40', 1),
( 'Tonight!', 'I cant', 104, 25, '2021-12-16 12:21:40', 1),
( 'Cut only to the stump!', 'Sure thing', 105, 26, '2022-01-16 15:12:40', 1),
( 'I think Im missing a tree!', 'Better find it!', 106, 27, '2022-04-16 13:51:40', 0),
( 'How do I get a quote!', 'I will send one out', 106, 28, '2022-08-16 06:46:40', 2),
( 'Tomorrow!', 'Yes', 108, 29, '2021-04-16 09:21:40', 0),
( 'Seems ok!', 'Good', 101, 21, '2020-05-16 10:12:40', 0);
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
  billID int not null default '0',
  issueDate datetime not null default '1990-01-31 10:24:40', 
  PRIMARY KEY (QuoteID),
  Foreign key (requestID) references Request(requestID),
  Foreign key (billID) references Bill(billID),
  foreign key (clientID) references User(clientID)
);
alter table Quote auto_increment = 20;
INSERT INTO Quote( clientNote , smithNote, times, price, requestID, clientID, issueDate, billID)
VALUES 
( 'Looks Good!', 'Awsome', '1:00:00', 1300, 200, 100, '2022-05-16 06:46:40', default),
( 'Please!', 'Sure', '2:00:00', 6600, 201, 102, '2022-08-16 06:46:40', 10),
( 'Sold!', 'thank you', '3:00:00', 9000, 202, 102, '2022-06-16 06:46:40', default),
( 'Why so much!', 'Its a lot of trees', '4:00:00', 2000, 203, 103, '2022-03-16 06:46:40', 10),
( 'Roger!', 'Yay', '5:00:00', 5668, 204, 103, '2022-11-16 06:46:40', 11),
( 'NeverMind!', 'OK', '6:00:00', 7880, 205, 108, '2023-01-16 06:46:40', 12),
( default, 'Here you go', '7:00:00', 2239, 205, 101, '2022-05-16 06:46:40', 13),
( default, 'Just look over this', '8:00:00', 9449, 206, 104, '2022-09-16 06:46:40', 14),
( default, 'I got you', '9:00:00', 4855, 207, 105, '2022-06-16 06:46:40', 15),
( 'Nice!', 'Thanks', '10:00:00', 3455, 208, 106, '2022-12-16 06:46:40', default);
SET FOREIGN_KEY_CHECKS = 1;

SET FOREIGN_KEY_CHECKS = 0;
Create TABLE if not exists Bill(
  billID int not null auto_increment, 
  clientNote varchar(150) default 'pending', 
  smithNote varchar(150) default 'pending',
  price double(10,2) not null default 5000,
  quoteID int not null default 0,
  clientID int not null default 0, 
  issueDate datetime not null default '2023-01-31 10:24:40', 
  dueDate datetime not null default '2023-02-24 10:24:40',
  payDate datetime  default null,
  payStatus boolean not null default false,
  PRIMARY KEY (billID),
  Foreign key (quoteID) references Quote(quoteID),
  Foreign key (clientID) references User(clientID)
);
alter table Bill auto_increment = 10;
INSERT INTO Bill(clientNote, smithNote, quoteID, clientID, issueDate, dueDate, payDate, payStatus, price)
VALUES 
( default, 'Thank You', 20, 100, '2022-07-24 10:24:40', '2022-08-24 10:24:40' ,  default, false, 1300),
( 'Paid', 'Thank You', 21, 102, '2022-09-24 10:24:40', '2022-10-24 10:24:40' , '2022-09-25 06:24:40', true, 6600),
( 'Too expensive', 'Why', 22, 102, '2022-07-24 10:24:40', '2022-08-24 10:24:40' , default, false, 9000),
( 'Why this date', 'Its the fastest', 23, 103, '2022-04-24 10:24:40', '2022-05-24 10:24:40' , '2022-08-11 10:24:40', true, 2000),
( 'Can you show me what your charging', 'Sure', 24, 103, '2022-12-24 10:24:40', '2023-01-24 10:24:40' , default, false, 5668),
( 'Alright', 'Sweet', 25, 108, '2022-07-24 10:24:40', '2022-08-24 10:24:40' , '2022-08-01 10:24:40', true, 7880),
( default, 'Thank You', 26, 101, '2022-02-24 10:24:40', '2022-03-24 10:24:40' , default, false, 2239),
( default, 'Thank You', 27, 104, '2022-06-24 10:24:40', '2022-07-24 10:24:40' , '2022-08-11 10:24:40', true, 9449),
( default, 'Thank You', 28, 105, '2022-10-24 10:24:40', '2022-11-24 10:24:40' , '2022-12-11 10:24:40', true, 4855),
( default, 'Thank You', 29, 106, '2022-07-24 10:24:40', '2023-01-24 10:24:40' , default, false, 3455);
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
select * from Tree;
select * from Request;
select * from Quote;
select * from Bill;


select * from RequestResponse;
select * from QuoteResponse;
select * from BillResponse;

#Big Client
create view TreeCount AS 
select clientID, sum(treeCount) as sumCount
from Request
group by clientID;

select clientID, sumCount
from TreeCount 
where sumCount = (select max(sumCount) from TreeCount);

# EASY CLIENT TABLE
select distinct clientID as EasyClient
from Quote
where billID != 0 and clientNote = "pending";


# One Tree Quotes
select quoteID as OneTreeQuotes, requestID
from Quote
where billID != 0 and quoteID in (
	select quoteID
	from Request
	where treeCount = 1
);


# Select prospective clients
select distinct clientID as ProspectiveClient
from quote
where billID = 0;


# Highest Tree
select treeID as HighestTree, height
from Tree
where height = (
select max(height)
from Tree
);

# Select overdue bills
select billID as OverDueBills
from Bill
where TIMESTAMPDIFF(hour, issueDate, current_timestamp) > 168 and payStatus = false;

#Bad Clients
select distinct clientID as BadClients
from Bill
where TIMESTAMPDIFF(hour, issueDate, current_timestamp) > TIMESTAMPDIFF(hour, issueDate, dueDate) and payStatus = false
group by clientID;

#Good Clients
select distinct clientID as GoodClients
from Bill
where TIMESTAMPDIFF(hour, issueDate, payDate) < 24 and payStatus = true
group by clientID;

#clientStats
select clientID, sum(treeCount)
from Request
group by clientID;

select clientID, sum(price)
from Bill 
group by clientID;

select clientID, sum(price)
from Bill
where payStatus = true
group by clientID;

select treeID, T.requestID, cutDate, R.clientID
from Tree T, Request R
where cutStatus = true and T.requestID = R.requestID;
