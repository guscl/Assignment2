-- Comp9321 Assignment 2, Group 2
-- Database schema:

CREATE TABLE member (
        username varChar(20) Primary Key,
        nickname varChar(20) NOT NULL,
        firstname varChar(50),
        lastname varChar(50),
        password varChar(20),
        email varChar(50) NOT NULL,
        birthyear integer,
        address varChar(100),
        creditcard varChar(20),
        role varChar(14) NOT NULL,
        locked boolean NOT NULL
);

CREATE TABLE item (
        itemid INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
        title varChar(30) UNIQUE,
        category varChar(15) NOT NULL, -- valid categories to be included in code
        picture varChar(20) NOT NULL, -- filename
        description varChar(100) NOT NULL,
        postagedetails varChar(50) NOT NULL,
        reserveprice integer NOT NULL,
        bidincrement integer NOT NULL,
        PRIMARY KEY (itemid)
);

CREATE TABLE auction (
        item integer NOT NULL,
        username varChar(20) NOT NULL,
        starttime time,
        auctionlength integer,
        status varchar(7) NOT NULL,
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
INSERT INTO member (username, nickname, password, email, role, locked)
        VALUES ('Administrator', 'Admin', 'admin123', 'admin@domain.com', 'administrator', false);

INSERT INTO member (username, nickname, firstname, lastname, password, email, birthyear, address, creditcard, role, locked)
        VALUES ('user1', 'JohnD', 'John', 'Doe', 'abc123', 'johndoe@missingpersons.com', 1994, '1 Anzac Pde Kensington', 'XXXX-XXXX-XXXX-XXXX', 'member', false);

INSERT INTO member (username, nickname, firstname, lastname, password, email, birthyear, address, creditcard, role, locked)
        VALUES ('user2', 'Jim', 'James', 'Jones', 'xyz246', 'jjones@someuni.edu', 1996, '4 George Street Sydney', '1234-5678-0000-1111', 'member', false);

INSERT INTO item (title, category, picture, description, postagedetails, reserveprice, bidincrement)
        VALUES ('Dell Laptop', 'Computers', 'dell.jpg', 'Scond hand laptop', 'Courier Delivery only', 150, 50);

INSERT INTO item (title, category, picture, description, postagedetails, reserveprice, bidincrement)
        VALUES ('Samsung Galaxy', 'Phone', 'mobile', 'Android mobile phone', 'AustraliaPost', 300, 20);

INSERT INTO item (title, category, picture, description, postagedetails, reserveprice, bidincrement)
        VALUES ('Textbook: Computer Networks', 'Book', 'image.jpg', 'used textbook as new', 'via post', 50, 10);

INSERT INTO auction (item, username, starttime, status)
        VALUES (1, 'user1', '16:00', 'new');

INSERT INTO auction(item, username, starttime, status)
        VALUES (2, 'user2', '14:00', 'started');

INSERT INTO bid (biddate, bidtime, bidder, item, amount)
        VALUES (DATE('09/16/2013'), '14:00', 'user2', 1, 200);

INSERT INTO bid (biddate, bidtime, bidder, item, amount)
        VALUES (DATE('10/14/2013'), '12:00', 'user1', 2, 320);
