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
  ClientNote varchar(30),  
  SmithNote varchar(30), 
  Time time,
  Price int, 
  PRIMARY KEY (RequestID)
);

Create TABLE Tree(
  treeID int, 
  RequestID int,
  distancefromhome int, 
  size int, height int, 
  location varchar(30), 
  image1 varchar(30), 
  image2 varchar(30), 
  image3 varchar(30),
  PRIMARY KEY (treeID),
  FOREIGN KEY (RequestID) REFERENCES Request(RequestID)
);

INSERT INTO User (UserID, phoneNumber, Email, creditCard, firstName, lastName)
VALUES 
(001, 1234567890, "johnsmith@gmail.com", 11111111, "John", "Smith"),
(002, 4353536377, "joerey@gmail.com", 22222222, "Joe", "Rey"),
(003, 6456478874, "jameswhite@gmail.com", 33333333, "James", "White"),
(004, 1124435677, "aaronrodgers@gmail.com", 44444444, "Aaron", "Rodgers"),
(005, 9898654463, "johndoe@gmail.com", 55555555, "John", "Doe"),
(006, 8478976444, "barbakew@gmail.com", 66666666, "Barb", "Akew"),
(007, 4645735282, "oliveyew@gmail.com", 77777777, "Olive", "Yew"),
(008, 0902848747, "noahlyles@gmail.com", 88888888, "Noah", "Lyles"),
(009, 1235134356, "victorabu@gmail.com", 99999999, "Victor", "Abu"),
(010, 6546534262, "Lukmanace@gmail.com", 00000000, "Lukman", "Ace");

INSERT INTO Bill(billID, Note, SmithNote)
VALUES 
(0001, "Sold!", ""),
(0002, "Sold!", ""),
(0003, "Sold!", ""),
(0004, "Sold!", ""),
(0005, "Sold!", ""),
(0006, "Sold!", ""),
(0007, "Sold!", ""),
(0008, "Sold!", ""),
(0009, "Sold!", ""),
(0010, "Sold!", "");

INSERT INTO Quote(QuoteID, ClientNote , SmithNote, Time, Price)
VALUES 
(00001, "Sold!", "", "1:00:00", 4500),
(00002, "Sold!", "", "2:00:00", 5500),
(00003, "Sold!", "", "3:00:00", 6500),
(00004, "Sold!", "", "4:00:00", 7500),
(00005, "Sold!", "", "5:00:00", 8500),
(00006, "Sold!", "", "6:00:00", 9500),
(00007, "Sold!", "", "7:00:00", 10500),
(00008, "Sold!", "", "8:00:00", 11500),
(00009, "Sold!", "", "9:00:00", 12500),
(00010, "Sold!", "", "10:00:00", 13500);

INSERT INTO Request(RequestID, treeID, ClientNote, SmithNote, Time, Price)
VALUES 
(1, 111, "Sold!", "", "1:00:00", 4500),
(2, 222, "Sold!", "", "2:00:00", 5500),
(3, 333, "Sold!", "", "3:00:00", 6500),
(4, 444, "Sold!", "", "4:00:00", 7500),
(5, 555, "Sold!", "", "5:00:00", 8500),
(6, 666, "Sold!", "", "6:00:00", 9500),
(7, 777, "Sold!", "", "7:00:00", 10500),
(8, 888, "Sold!", "", "8:00:00", 11500),
(9, 999, "Sold!", "", "9:00:00", 12500),
(0, 000, "Sold!", "", "10:00:00", 13500);

INSERT INTO Tree(treeID, distancefromhome, size, height, location, image1, image2, image3)
VALUES 
(111, 5, 123, "Detroit", "a", "b", "c"),
(222, 10, 232, "Detroit", "a", "d", "e"),
(333, 15, 121, "Detroit", "h", "g", "f"),
(444, 20, 180, "Detroit", "i", "j", "k"),
(555, 25, 280, "Detroit"", "n", "m", "l"),
(666, 30, 321, "Detroit", "o", "p", "q"),
(777, 35, 213, "Detroit", "t", "s", "r"),
(888, 40, 91, "Detroit", "u", "b", "c"),
(999, 45, 145, "Detroit", "v", "y", "s"),
(000, 50, 156, "Detroit", "w", "x", "z");
