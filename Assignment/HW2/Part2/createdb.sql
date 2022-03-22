CREATE TABLE Yelp_User(
	user_id INTEGER PRIMARY KEY,
	first_Name VARCHAR2(20) NOT NULL, 
	last_Name VARCHAR2(20) NOT NULL,
	date_of_birth DATE NOT NULL,
	birth_place VARCHAR2(2) NOT NULL,
	gender CHAR(1) NOT NULL,
	email VARCHAR2(50) NOT NULL
);

INSERT ALL  INTO Yelp_User VALUES (1, 'John', 'Smith', to_date('1992-12-12','yyyy-mm-dd'), 'FL', 'M', 'john@yahoo.com')
            INTO Yelp_User VALUES (2, 'Juan', 'Carlos', to_date('1988-02-07','yyyy-mm-dd'), 'AK', 'M', 'juan@gmail.com')
            INTO Yelp_User VALUES (3, 'Jane', 'Chapel', to_date('1980-06-01','yyyy-mm-dd'), 'IL', 'F', 'Jane@gmail.com')
            INTO Yelp_User VALUES (4, 'Aditya', 'Awasthi', to_date('1994-04-12','yyyy-mm-dd'), 'CA', 'M', 'adi@yahoo.com')
            INTO Yelp_User VALUES (5, 'James', 'Williams', to_date('1991-05-05','yyyy-mm-dd'), 'NY', 'M', 'james@hotmail.com')
            INTO Yelp_User VALUES (6, 'Mike', 'Brown', to_date('1988-03-01','yyyy-mm-dd'), 'NC', 'M', 'mike@yahoo.com')
            INTO Yelp_User VALUES (7, 'Bob', 'Jones', to_date('1970-02-19','yyyy-mm-dd'), 'NY', 'M', 'bob@yahoo.com')
            INTO Yelp_User VALUES (8, 'Wei', 'Zhang', to_date('1975-03-18','yyyy-mm-dd'), 'NV', 'F', 'wei@gmail.com')
            INTO Yelp_User VALUES (9, 'Mark', 'Davis', to_date('1993-11-02','yyyy-mm-dd'), 'CA', 'M', 'mark@gmail.com')
            INTO Yelp_User VALUES (10, 'Daniel', 'Garcia', to_date('1984-05-10','yyyy-mm-dd'), 'NJ', 'M', 'daniel@yahoo.com')
            INTO Yelp_User VALUES (11, 'Maria', 'Rodriguez', to_date('1985-08-12','yyyy-mm-dd'), 'CA', 'F', 'maria@hotmail.com')
            INTO Yelp_User VALUES (12, 'Freya', 'Wilson', to_date('1995-10-05','yyyy-mm-dd'), 'NJ', 'F', 'freya@yahoo.com')
            INTO Yelp_User VALUES (13, 'John', 'Zhang', to_date('1995-12-12','yyyy-mm-dd'), 'NJ', 'M', 'jzhang@gmail.com')
SELECT * FROM DUAL;

CREATE TABLE beFriend(
	user_id INTEGER NOT NULL,
	FriendID INTEGER,
	PRIMARY KEY(user_id, FriendID),
	FOREIGN KEY(user_id) REFERENCES Yelp_User(user_id) ON DELETE CASCADE
);


 INSERT ALL INTO beFriend VALUES (1, 7)
            INTO beFriend VALUES (1, 9)
            INTO beFriend VALUES (1, 2)
            INTO beFriend VALUES (3, 5)
            INTO beFriend VALUES (3, 6)
            INTO beFriend VALUES (3, 11)
            INTO beFriend VALUES (4, 7)
            INTO beFriend VALUES (4, 11)
            INTO beFriend VALUES (6, 2)
            INTO beFriend VALUES (6, 4)
            INTO beFriend VALUES (7, 1)
            INTO beFriend VALUES (7, 3)
            INTO beFriend VALUES (8, 1)
            INTO beFriend VALUES (8, 6)
            INTO beFriend VALUES (9, 1)
            INTO beFriend VALUES (9, 2)
            INTO beFriend VALUES (11, 3)
            INTO beFriend VALUES (11, 5)
            INTO beFriend VALUES (12, 11)
            INTO beFriend VALUES (13, 12)
 SELECT * FROM DUAL;

CREATE TABLE beComplimented(
	user_id INTEGER NOT NULL,
	CFriendID INTEGER,
	PRIMARY KEY(user_id, CFriendID),
	FOREIGN KEY(user_id) REFERENCES Yelp_User(user_id) ON DELETE CASCADE
);

 INSERT ALL INTO beComplimented VALUES (1,7)
            INTO beComplimented VALUES (1,9)
            INTO beComplimented VALUES (3,6)
            INTO beComplimented VALUES (4,11)
            INTO beComplimented VALUES (6,4)
            INTO beComplimented VALUES (7,3)
            INTO beComplimented VALUES (8,6)
            INTO beComplimented VALUES (9,2)
            INTO beComplimented VALUES (11,5)
            INTO beComplimented VALUES (12,11)
            INTO beComplimented VALUES (13,12)
 SELECT * FROM DUAL;

CREATE TABLE Business(
	BID INTEGER PRIMARY KEY,
	BName VARCHAR2(50) NOT NULL,
	Ambient_Type VARCHAR2(10),
	Street_Address VARCHAR2(50) NOT NULL,
	County VARCHAR2(20),
	State CHAR(2) NOT NULL,
	Zip_Code VARCHAR2(5) NOT NULL
);


INSERT ALL  INTO Business VALUES (1, 'Big Surf', 'Touristy', '1500 N McClintock Dr', 'Tempe', 'AZ', '85281')
            INTO Business VALUES (2, 'SMITH THOMSON ', 'Touristy', '1595 Spring Hill Road Suite 110', 'Vienna', 'VA', '22182')
            INTO Business VALUES (3, 'Bay Area Coffee Shop', 'Touristy', '1522 W. Sam Rayburn Dr.', 'Bonham', 'CA', '95051')
            INTO Business VALUES (4, 'China  Coffee Toffee', 'Touristy', '2570 El Camino Real', 'Santa Clara', 'CA', '95051')
            INTO Business VALUES (5, 'Hastings Water Works', NULL, '10331 Brecksville Rd.', 'Brecksville', 'OH', '44141')
            INTO Business VALUES (6, 'Catch Your Big Break', NULL, '2341 Roosevelt Ct', 'Santa Clara', 'CA', '95051')
            INTO Business VALUES (7, 'The Cinebar', NULL, '20285 South Western Ave., Suite # 200', 'Torrance', 'CA', '95051')
            INTO Business VALUES (8, 'Coffee Bar & Bistro', NULL, '2585 El Camino Real', 'Santa Clara', 'CA', '95051')
            INTO Business VALUES (9, 'Hobee''s', NULL, '90 Skyport Dr', 'San Jose', 'CA', '95110')
            INTO Business VALUES (10, 'Cafe Gourmet', NULL, '80 N Market St', 'San Jose', 'CA', '95110')
            INTO Business VALUES (11, 'Yellow Stone National Park', NULL, 'Yellowstone National Park', NULL, 'WY', '82190')
            INTO Business VALUES (12, 'Petrified Forest National Park', NULL, 'P.O. Box 221', NUll, 'AZ', '86028')
            INTO Business VALUES (13, 'Grand Canyon National park', NULL, 'Highway 64', NULL, 'AZ', '86023')
            INTO Business VALUES (14, 'Connecticut Bar & Restaurent', NULL, '1133 Terry Road', 'Hartford', 'CT', '06105')
            INTO Business VALUES (35, 'Connecticut Bar', NULL, '5847 San Felipe, Suite 2400', 'Houston', 'TX', '77057')
            INTO Business VALUES (36, 'Sherley''s Bar & Restaurent', NULL, '1132 Terry Road', 'Hartford', 'CT', '06105')
SELECT * FROM DUAL;

CREATE TABLE Reviews(
	RID INTEGER,
	Stars INTEGER, 
	Author INTEGER NOT NULL,
	Publish_Date DATE NOT NULL,
	Business_ID INTEGER NOT NULL,
	Vote_Funny INTEGER,
	Vote_Cool INTEGER,
	Vote_Useful INTEGER,
    PRIMARY KEY (RID),
	FOREIGN KEY(Author) REFERENCES Yelp_User(user_id) ON DELETE CASCADE,
	FOREIGN KEY(Business_ID) REFERENCES Business(BID) ON DELETE CASCADE
);


 INSERT ALL INTO Reviews VALUES (1, 3, 11, to_date('2007-10-02 09:10:54','yyyy-mm-dd hh24:mi:ss'),  3, 2, 1, 3)
            INTO Reviews VALUES (2, 2, 10, to_date('2007-09-29 13:45:00','yyyy-mm-dd hh24:mi:ss'),  1, 0, 1, 2)
            INTO Reviews VALUES (3, 4, 2, to_date('2007-09-29 10:38:25','yyyy-mm-dd hh24:mi:ss'),  10, 3, 2, 0)
            INTO Reviews VALUES (4, 5, 11, to_date('2007-10-02 13:05:56','yyyy-mm-dd hh24:mi:ss'),  4, 2, 2, 1)
            INTO Reviews VALUES (5, 5, 1, to_date('2007-10-25 17:15:00','yyyy-mm-dd hh24:mi:ss'),  3, 3, 1, 2)
            INTO Reviews VALUES (6, 5, 4, to_date('2007-09-26 17:15:00','yyyy-mm-dd hh24:mi:ss'),  4, 5, 1, 3)
            INTO Reviews VALUES (7, 5, 9, to_date('2007-09-26 17:15:00','yyyy-mm-dd hh24:mi:ss'),  5, 7, 3, 6)
            INTO Reviews VALUES (8, 5, 5, to_date('2007-09-27 17:15:00','yyyy-mm-dd hh24:mi:ss'),  6, 3, 2, 1)
            INTO Reviews VALUES (9, 5, 12, to_date('2007-09-28 17:15:00','yyyy-mm-dd hh24:mi:ss'),  7, 2, 4, 5)
            INTO Reviews VALUES (10, 5, 5, to_date('2007-10-29 17:15:00','yyyy-mm-dd hh24:mi:ss'), 8, 6, 1, 2)
            INTO Reviews VALUES (11, 5, 1, to_date('2007-09-30 17:15:00','yyyy-mm-dd hh24:mi:ss'), 9, 2, 3, 8)
            INTO Reviews VALUES (12, 5, 12, to_date('2007-10-25 17:15:00','yyyy-mm-dd hh24:mi:ss'), 10, 2, 1, 4)
            INTO Reviews VALUES (13, 5, 4, to_date('2007-09-25 17:15:00','yyyy-mm-dd hh24:mi:ss'), 11, 5, 6, 2)
            INTO Reviews VALUES (14, 5, 5, to_date('2007-09-25 17:15:00','yyyy-mm-dd hh24:mi:ss'), 12, 2, 3, 5)
            INTO Reviews VALUES (15, 5, 1, to_date('2007-09-29 17:15:00','yyyy-mm-dd hh24:mi:ss'), 13, 0, 0, 0)
            INTO Reviews VALUES (16, 2, 11, to_date('2015-06-07 17:15:00','yyyy-mm-dd hh24:mi:ss'), 2, 1, 1, 2)
            INTO Reviews VALUES (17, 1, 4, to_date('2015-05-05 17:15:00','yyyy-mm-dd hh24:mi:ss'), 2, 2, 3, 2)
            INTO Reviews VALUES (18, 1, 1, to_date('2015-05-05 17:15:00','yyyy-mm-dd hh24:mi:ss'), 2, 1, 4, 3)
            INTO Reviews VALUES (19, 1, 10, to_date('2007-10-25 17:15:00','yyyy-mm-dd hh24:mi:ss'), 3, 4, 2, 3)
            INTO Reviews VALUES (20, 1, 1, to_date('2007-09-28 17:15:00','yyyy-mm-dd hh24:mi:ss'), 4, 5, 1, 2)
            INTO Reviews VALUES (21, 4, 11, to_date('2015-06-07 17:15:00','yyyy-mm-dd hh24:mi:ss'), 14, 5, 6, 9)
            INTO Reviews VALUES (22, 1, 4, to_date('2015-05-05 17:15:00','yyyy-mm-dd hh24:mi:ss'), 14, 7, 8, 4)
            INTO Reviews VALUES (23, 1, 1, to_date('2015-05-05 17:15:00','yyyy-mm-dd hh24:mi:ss'), 14, 8, 6, 3)
            INTO Reviews VALUES (24, 4, 13, to_date('2018-06-10 18:15:00','yyyy-mm-dd hh24:mi:ss'), 1, 2, 4, 5)
 SELECT * FROM DUAL;

CREATE TABLE Comments(
	RID INTEGER NOT NULL,
	user_id INTEGER NOT NULL,
	Comment_Date DATE,
	Content VARCHAR2(500),
	PRIMARY KEY (RID, user_id),
	FOREIGN KEY (RID) REFERENCES Reviews(RID) ON DELETE CASCADE,
	FOREIGN KEY (user_id) REFERENCES Yelp_User(user_id) ON DELETE CASCADE
);

 INSERT ALL INTO Comments(RID, user_id) VALUES (2, 2)
            INTO Comments(RID, user_id) VALUES (2, 3)
            INTO Comments(RID, user_id) VALUES (3, 3)
            INTO Comments(RID, user_id) VALUES (7, 8)
            INTO Comments(RID, user_id) VALUES (7, 11)
            INTO Comments(RID, user_id) VALUES (15, 8)
            INTO Comments(RID, user_id) VALUES (16, 8)
            INTO Comments(RID, user_id) VALUES (20, 7)
            INTO Comments(RID, user_id) VALUES (20, 10)
SELECT * FROM DUAL;


CREATE TABLE Operation(
	BID INTEGER NOT NULL,
	Day VARCHAR2(3) NOT NULL,
	PRIMARY KEY(BID, Day),
	FOREIGN KEY (BID) REFERENCES Business(BID) ON DELETE CASCADE
);

INSERT ALL 
INTO Operation VALUES (1, 'Mon') INTO Operation VALUES (1, 'Tue') INTO Operation VALUES (1, 'Wed')
INTO Operation VALUES (2, 'Mon') INTO Operation VALUES (2, 'Tue') INTO Operation VALUES (2, 'Wed') INTO Operation VALUES (2, 'Thu')
INTO Operation VALUES (3, 'Mon') INTO Operation VALUES (3, 'Tue') INTO Operation VALUES (3, 'Wed')
INTO Operation VALUES (4, 'Mon') INTO Operation VALUES (4, 'Tue') INTO Operation VALUES (4, 'Wed')
INTO Operation VALUES (5, 'Mon') INTO Operation VALUES (5, 'Tue') INTO Operation VALUES (5, 'Wed')
INTO Operation VALUES (6, 'Mon') INTO Operation VALUES (6, 'Tue') INTO Operation VALUES (6, 'Wed') 
INTO Operation VALUES (6, 'Thu') INTO Operation VALUES (6, 'Fri') INTO Operation VALUES (6, 'Sat') INTO Operation VALUES (6, 'Sun')
INTO Operation VALUES (7, 'Mon') INTO Operation VALUES (7, 'Tue') INTO Operation VALUES (7, 'Wed') INTO Operation VALUES (7, 'Thu')
INTO Operation VALUES (8, 'Mon') INTO Operation VALUES (8, 'Tue') INTO Operation VALUES (8, 'Wed')
INTO Operation VALUES (9, 'Mon') INTO Operation VALUES (9, 'Tue') INTO Operation VALUES (9, 'Wed') INTO Operation VALUES (9, 'Thu') INTO Operation VALUES (9, 'Fri')
INTO Operation VALUES (10, 'Mon') INTO Operation VALUES (10, 'Tue')
INTO Operation VALUES (11, 'Mon') INTO Operation VALUES (11, 'Fri') INTO Operation VALUES (11, 'Sat') INTO Operation VALUES (11, 'Sun')
INTO Operation VALUES (12, 'Mon') INTO Operation VALUES (12, 'Tue') INTO Operation VALUES (12, 'Sun')
INTO Operation VALUES (13, 'Mon') INTO Operation VALUES (13, 'Sat') INTO Operation VALUES (13, 'Sun')
INTO Operation VALUES (14, 'Mon')INTO Operation VALUES(14, 'Wed')INTO Operation VALUES(14, 'Sun')
INTO Operation VALUES (35, 'Mon')INTO Operation VALUES(35, 'Thu')INTO Operation VALUES(35, 'Sun')
INTO Operation VALUES (36, 'Mon')INTO Operation VALUES(36, 'Wed')INTO Operation VALUES(36, 'Sun')
SELECT * FROM DUAL;


CREATE TABLE Business_Cat(
	BCID INTEGER PRIMARY KEY,
	BCName VARCHAR2(50) NOT NULL,
	Sub_Cat VARCHAR2(100) NOT NULL
);

INSERT ALL
INTO Business_Cat VALUES (1, 'Amusement Parks', 'Water Parks')
INTO Business_Cat VALUES (2, 'National Parks', 'Wildlife National Parks')
INTO Business_Cat VALUES (3, 'Career Counseling', 'Career Counselling for kidss')
INTO Business_Cat VALUES (4, 'Food and More', 'Ice-cream and Yougurt')
INTO Business_Cat VALUES (5, 'Bars', 'Sports bar')
INTO Business_Cat VALUES (6, 'Restaurants', 'Ice-cream and Yougurt')
INTO Business_Cat VALUES (7, 'Pool Cleaners', 'Swimming pool cleaners')
INTO Business_Cat VALUES (8, 'Coffee Shops', 'Cold coffee shops')
SELECT * FROM DUAL;

CREATE TABLE Belong(
	BID INTEGER NOT NULL,
	BCID INTEGER NOT NULL,
	PRIMARY KEY (BID, BCID),
	FOREIGN KEY (BID) REFERENCES Business(BID) ON DELETE CASCADE,
	FOREIGN KEY (BCID) REFERENCES Business_Cat(BCID) ON DELETE CASCADE
);

INSERT ALL
INTO Belong VALUES  (1,1)
INTO Belong VALUES 	(2,4)INTO Belong VALUES (2,6)
INTO Belong VALUES 	(3,6)INTO Belong VALUES (3,8)
INTO Belong VALUES 	(4,4)INTO Belong VALUES (4,6)
INTO Belong VALUES 	(5,7)
INTO Belong VALUES 	(6,3)
INTO Belong VALUES 	(7,5)
INTO Belong VALUES 	(8,5)
INTO Belong VALUES 	(9,6)
INTO Belong VALUES 	(10,4)INTO Belong VALUES (10,6)
INTO Belong VALUES 	(11,2)
INTO Belong VALUES 	(12,2)
INTO Belong VALUES 	(13,2)
INTO Belong VALUES 	(14,5)
INTO Belong VALUES 	(35,5)
INTO Belong VALUES 	(36,5)INTO Belong VALUES (36,6)
SELECT * FROM DUAL;

CREATE TABLE Check_In(
	CID INTEGER PRIMARY KEY,
	INFO VARCHAR2(500) NOT NULL,
	BID INTEGER NOT NULL
);

INSERT ALL
INTO Check_In VALUES (1, 'Checkin Info 1', 1)
INTO Check_In VALUES (2, 'Checkin Info 2', 2)
INTO Check_In VALUES (3, 'Checkin Info 3', 3)
INTO Check_In VALUES (4, 'Checkin Info 4', 4)
INTO Check_In VALUES (5, 'Checkin Info 5', 5)
INTO Check_In VALUES (6, 'Checkin Info 6', 6)
INTO Check_In VALUES (7, 'Checkin Info 7', 7)
INTO Check_In VALUES (8, 'Checkin Info 8', 8)
INTO Check_In VALUES (9, 'Checkin Info 9', 9)
INTO Check_In VALUES (10, 'Checkin Info 10', 10)
INTO Check_In VALUES (11, 'Checkin Info 11', 11)
INTO Check_In VALUES (12, 'Checkin Info 12', 12)
INTO Check_In VALUES (13, 'Checkin Info 13', 13)
SELECT * FROM DUAL;



CREATE TABLE Check_Into(
	user_id INTEGER NOT NULL,
	BID INTEGER NOT NULL,
	Check_Time DATE, 
	PRIMARY KEY(user_id, BID),
	FOREIGN KEY (user_id) REFERENCES Yelp_User(user_id) ON DELETE CASCADE,
	FOREIGN KEY (BID) REFERENCES Business(BID) ON DELETE CASCADE
);

INSERT ALL
INTO Check_Into(user_id, BID) VALUES      (1,2)INTO Check_Into(user_id, BID) VALUES(1,3)INTO Check_Into(user_id, BID) VALUES(1,4)INTO Check_Into(user_id, BID) VALUES(1,5)INTO Check_Into(user_id, BID) VALUES(1,9)INTO Check_Into(user_id, BID) VALUES(1,13)INTO Check_Into(user_id, BID) VALUES(1,35)INTO Check_Into(user_id, BID) VALUES(1,36)
INTO Check_Into(user_id, BID) VALUES	(2,2)INTO Check_Into(user_id, BID) VALUES(2,5)INTO Check_Into(user_id, BID) VALUES(2,10)INTO Check_Into(user_id, BID) VALUES(2,11)INTO Check_Into(user_id, BID) VALUES(2,35)INTO Check_Into(user_id, BID) VALUES(2,36)
INTO Check_Into(user_id, BID) VALUES	(4,2)INTO Check_Into(user_id, BID) VALUES(4,4)INTO Check_Into(user_id, BID) VALUES(4,5)INTO Check_Into(user_id, BID) VALUES(4,11)INTO Check_Into(user_id, BID) VALUES(4,12)INTO Check_Into(user_id, BID) VALUES(4,35)INTO Check_Into(user_id, BID) VALUES(4,36)
INTO Check_Into(user_id, BID) VALUES	(5,5)INTO Check_Into(user_id, BID) VALUES(5,6)INTO Check_Into(user_id, BID) VALUES(5,8)INTO Check_Into(user_id, BID) VALUES(5,11)INTO Check_Into(user_id, BID) VALUES(5,12)INTO Check_Into(user_id, BID) VALUES(5,35)INTO Check_Into(user_id, BID) VALUES(5,36)
INTO Check_Into(user_id, BID) VALUES	(6,2)INTO Check_Into(user_id, BID) VALUES(6,3)INTO Check_Into(user_id, BID) VALUES(6,11)INTO Check_Into(user_id, BID) VALUES(6,13)INTO Check_Into(user_id, BID) VALUES(6,36)
INTO Check_Into(user_id, BID) VALUES	(7,2)INTO Check_Into(user_id, BID) VALUES(7,3)INTO Check_Into(user_id, BID) VALUES(7,11)INTO Check_Into(user_id, BID) VALUES(7,13)INTO Check_Into(user_id, BID) VALUES(7,35)INTO Check_Into(user_id, BID) VALUES(7,36)
INTO Check_Into(user_id, BID) VALUES	(8,2)INTO Check_Into(user_id, BID) VALUES(8,4)INTO Check_Into(user_id, BID) VALUES(8,11)INTO Check_Into(user_id, BID) VALUES(8,13)INTO Check_Into(user_id, BID) VALUES(8,35)INTO Check_Into(user_id, BID) VALUES(8,36)
INTO Check_Into(user_id, BID) VALUES	(9,2)INTO Check_Into(user_id, BID) VALUES(9,5)INTO Check_Into(user_id, BID) VALUES(9,11)INTO Check_Into(user_id, BID) VALUES(9,13)INTO Check_Into(user_id, BID) VALUES(9,35)INTO Check_Into(user_id, BID) VALUES(9,36)
INTO Check_Into(user_id, BID) VALUES	(10,1)INTO Check_Into(user_id, BID) VALUES(10,2)INTO Check_Into(user_id, BID) VALUES(10,3)INTO Check_Into(user_id, BID) VALUES(10,5)INTO Check_Into(user_id, BID) VALUES(10,11)INTO Check_Into(user_id, BID) VALUES(10,35)INTO Check_Into(user_id, BID) VALUES(10,36)
INTO Check_Into(user_id, BID) VALUES	(11,1)INTO Check_Into(user_id, BID) VALUES(11,2)INTO Check_Into(user_id, BID) VALUES(11,3)INTO Check_Into(user_id, BID) VALUES(11,4)INTO Check_Into(user_id, BID) VALUES(11,5)INTO Check_Into(user_id, BID) VALUES(11,11)INTO Check_Into(user_id, BID) VALUES(11,35)INTO Check_Into(user_id, BID) VALUES(11,36)
INTO Check_Into(user_id, BID) VALUES	(12,2)INTO Check_Into(user_id, BID) VALUES(12,5)INTO Check_Into(user_id, BID) VALUES(12,7)INTO Check_Into(user_id, BID) VALUES(12,10)INTO Check_Into(user_id, BID) VALUES(12,11)INTO Check_Into(user_id, BID) VALUES(12,13)INTO Check_Into(user_id, BID) VALUES(12,35)INTO Check_Into(user_id, BID) VALUES(12,36)
INTO Check_Into(user_id, BID) VALUES	(13,1)INTO Check_Into(user_id, BID) VALUES(13,2)INTO Check_Into(user_id, BID) VALUES(13,3)INTO Check_Into(user_id, BID) VALUES(13,4)INTO Check_Into(user_id, BID) VALUES(13,5)
SELECT * FROM DUAL;


