Create TABLE User(
  UserID int, 
  phoneNumber int, 
  Email varchar(50), 
  creditCard int, 
  firstName varchar(20), 
  lastName varchar(20),
  PRIMARY KEY (UserID)
);

Create TABLE Bill(
  billID int, 
  Note varchar(30), 
  SmithNote varchar(30),
  PRIMARY KEY (billID)
);

Create TABLE Quote(
  QuoteID int, 
  ClientNote varchar(30), 
  SmithNote varchar(30), 
  Time time, 
  Price int,
  PRIMARY KEY (QuoteID)
);

Create TABLE Request(
  RequestID int, 
  treeID int, 
  SmithNote varchar(30), 
  ClientNote varchar(30), 
  Price int, 
  Time time,
  PRIMARY KEY (RequestID),
  FOREIGN KEY (treeID) REFERENCES Tree(treeID)
);

Create TABLE Tree(
  treeID int, 
  distancefromhome int, 
  size int, height int, 
  location varchar(30), 
  image1 varchar(30), 
  image2 varchar(30), 
  image3 varchar(30),
  PRIMARY KEY (treeID)
);

INSERT INTO User (UserID, phoneNumber, Email, creditCard, firstName, lastName)
VALUES (...);

INSERT INTO Bill(billID, Note, SmithNote)
VALUES (...);

INSERT INTO Quote(QuoteID, ClientNote , SmithNote, Time, Price)
VALUES (...);

INSERT INTO Request(RequestID, treeID, SmithNote, ClientNote, Price, Time)
VALUES (...);

INSERT INTO Tree(treeID, distancefromhome, size, height, location, image1, image2, image3)
VALUES (...);



