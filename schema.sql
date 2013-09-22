-- Comp9321 Assignment 2, Group 2
-- Database schema:

-- possible issues, should auction and item be separate entities?
-- if so should owner be in item table or auction table?

CREATE TABLE member (
        username varChar(20) Primary Key,
        nickname varChar(20) NOT NULL,
        firstname varChar(50),
        lastname varChar(50),
        email varChar(50) NOT NULL,
        birthyear integer,
        address varChar(100),
        creditcard varChar(20),
        role varChar(14) NOT NULL
);

CREATE TABLE item (
        itemid serial Primary Key,
        title varChar(30) NOT NULL,
        category varChar(15) NOT NULL, -- valid categories to be included in code
        picture varChar(20) NOT NULL, -- filename
        description varChar(100) NOT NULL,
        postagedetails varChar(50) NOT NULL,
        reserveprice integer NOT NULL,
        bidincrement integer NOT NULL,
        closingtime integer -- (optional) number hours after auction start
);

CREATE TABLE auction (
        item integer NOT NULL,
        username varChar(20) NOT NULL,
        starttime time NOT NULL,
        PRIMARY KEY (item, username),
        FOREIGN KEY (item) REFERENCES item(itemid),
        FOREIGN KEY (username) REFERENCES member(username)
);

CREATE TABLE bid (
        biddate date NOT NULL,
        bidtime time NOT NULL, -- use time instead of date given short auction time
        bidder varchar(20) NOT NULL,
        item integer NOT NULL,
        amount integer NOT NULL, -- assume big in whole dollars
        PRIMARY KEY (biddate, bidtime, bidder, item),
        FOREIGN KEY (bidder) REFERENCES member(username),
        FOREIGN KEY (item) REFERENCES item(itemid)
);

-- start of data
INSERT INTO member (username, nickname, email, role)
        VALUES ('Administrator', 'Admin', 'admin@domain.com', 'administrator');

INSERT INTO member (username, nickname, firstname, lastname, email, birthyear, address, creditcard, role)
        VALUES ('user1', 'JohnD', 'John', 'Doe', 'johndoe@missingpersons.com', 1994, '1 Anzac Pde Kensington', 'XXXX-XXXX-XXXX-XXXX', 'member');

INSERT INTO member (username, nickname, firstname, lastname, email, birthyear, address, creditcard, role)
        VALUES ('user2', 'Jim', 'James', 'Jones', 'jjones@someuni.edu', 1996, '4 George Street Sydney', '1234-5678-0000-1111', 'member');

INSERT INTO item (title, category, picture, description, postagedetails, reserveprice, bidincrement)
        VALUES ('Dell Laptop', 'Computers', 'dell.jpg', 'Scond hand laptop', 'Courier Delivery only', 150, 50);

INSERT INTO item (title, category, picture, description, postagedetails, reserveprice, bidincrement)
        VALUES ('Samsung Galaxy', 'Phone', 'mobile', 'Android mobile phone', 'AustraliaPost', 300, 20);

INSERT INTO item (title, category, picture, description, postagedetails, reserveprice, bidincrement)
        VALUES ('Textbook: Computer Networks', 'Book', 'image.jpg', 'used textbook as new', 'via post', 50, 10);

INSERT INTO auction (item, username, starttime)
        VALUES (1, 'user1', '16:00');

INSERT INTO auction(item, username, starttime)
        VALUES (2, 'user2', '14:00');

INSERT INTO bid (biddate, bidtime, bidder, item, amount)
        VALUES (to_date('2013-09-16', 'YYYY-MM-DD'), '14:00', 'user2', 1, 200);

INSERT INTO bid (biddate, bidtime, bidder, item, amount)
        VALUES (to_date('2013-10-14', 'YYYY-MM-DD'), '12:00', 'user1', 2, 320);