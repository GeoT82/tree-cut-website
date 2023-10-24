drop database if exists testdb;
create database testdb;
use testdb;
Create TABLE User(
  UserID int, 
  phoneNumber int, 
  email varchar(50), 
  creditCard int, 
  firstName varchar(20), 
  lastName varchar(20),
  PRIMARY KEY (UserID)
);

Create TABLE Bill(
  billID int, 
  note varchar(30), 
  SmithNote varchar(30),
  PRIMARY KEY (billID)
);

Create TABLE Quote(
  quoteID int, 
  clientNote varchar(30), 
  smithNote varchar(30), 
  times time, 
  price int,
  PRIMARY KEY (QuoteID)
);

Create TABLE Request(
  requestID int, 
  treeID int, 
  SmithNote varchar(30), 
  ClientNote varchar(30), 
  Price int, 
  Time time,
  PRIMARY KEY (requestID)
);

Create TABLE Tree(
  treeID int, 
  distance int, 
  size int, height int, 
  location varchar(30), 
  image1 varchar(30), 
  image2 varchar(30), 
  image3 varchar(30),
  PRIMARY KEY (treeID),
  FOREIGN KEY (requestID) REFERENCES Request(requestID)
);

INSERT INTO User (UserID, phoneNumber, Email, creditCard, firstName, lastName)
VALUES (...);

INSERT INTO Bill(billID, Note, SmithNote)
VALUES (...);

INSERT INTO Quote(QuoteID, ClientNote , SmithNote, Times, Price)
VALUES (...);

INSERT INTO Request(RequestID, treeID, SmithNote, ClientNote, Price, Times)
VALUES (...);

INSERT INTO Tree(treeID, distance, size, height, location, image1, image2, image3)
VALUES (...);



