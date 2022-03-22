CREATE TABLE Yelp_Users(
user_id VARCHAR2(100),
name VARCHAR2(100),
yelping_since DATE,
review_count NUMBER,
average_stars FLOAT,
friends_count NUMBER,
fans NUMBER,
type VARCHAR(50),
votes NUMBER,
PRIMARY KEY(user_id)
);

CREATE TABLE votes(
user_id VARCHAR2(100) NOT NULL,
funny NUMBER,
useful NUMBER,
cool NUMBER,
PRIMARY KEY(user_id),
FOREIGN KEY (user_id) REFERENCES Yelp_Users (user_id) ON DELETE CASCADE
);

CREATE TABLE compliments(
user_id VARCHAR2(100),
type VARCHAR2(50),
count_of_votes NUMBER,
PRIMARY KEY(user_id, type),
FOREIGN KEY (user_id) REFERENCES Yelp_Users (user_id) ON DELETE CASCADE
);

CREATE TABLE friends(
user_id VARCHAR2(100),
friend_id VARCHAR2(100),
PRIMARY KEY (user_id, friend_id),
FOREIGN KEY (user_id) REFERENCES Yelp_Users (user_id) ON DELETE CASCADE,
FOREIGN KEY (friend_id) REFERENCES Yelp_Users (user_id) ON DELETE CASCADE
);

CREATE TABLE Yelp_Businesses(
business_id VARCHAR2(100),
business_name VARCHAR2(100),
city VARCHAR2(50),
state VARCHAR2(50),
review_count NUMBER,
stars NUMBER,
full_address VARCHAR2(300),
open VARCHAR2(10),
latitude FLOAT,
longitude FLOAT,
type VARCHAR2(50),
PRIMARY KEY(business_id)
);

CREATE TABLE Main_Categories(
business_id VARCHAR2(100),
main_category VARCHAR2(50),
PRIMARY KEY(business_id, main_category),
FOREIGN KEY(business_id) REFERENCES Yelp_Businesses(business_id) ON DELETE CASCADE
);

CREATE TABLE Sub_Categories(
business_id VARCHAR2(100),
sub_category VARCHAR2(50),
PRIMARY KEY(business_id, sub_category),
FOREIGN KEY(business_id) REFERENCES Yelp_Businesses(business_id) ON DELETE CASCADE
);

CREATE TABLE hours(
business_id VARCHAR2(100),
day VARCHAR2(10),
close VARCHAR2(10),
open  VARCHAR2(10),
PRIMARY KEY(business_id, day),
FOREIGN KEY(business_id) REFERENCES Yelp_Businesses(business_id) ON DELETE CASCADE
);

CREATE TABLE neighborhoods(
business_id VARCHAR2(100),
neighbor_name VARCHAR2(100),
PRIMARY KEY(business_id, neighbor_name),
FOREIGN KEY(business_id) REFERENCES Yelp_Businesses(business_id) ON DELETE CASCADE
);

CREATE TABLE Business_Attributes(
business_id VARCHAR2(100),
attribute_key VARCHAR2(50),
attribute_value VARCHAR2(50),
PRIMARY KEY(business_id, attribute_key),
FOREIGN KEY(business_id) REFERENCES Yelp_Businesses(business_id) ON DELETE CASCADE
);

CREATE TABLE Yelp_Reviews(
user_id     VARCHAR2(100),
review_id   VARCHAR2(100),
stars       NUMBER,
review_date DATE,
votes       NUMBER,
type        VARCHAR2(50),
business_id VARCHAR2(50),
review_text LONG,
PRIMARY KEY (review_id),
FOREIGN KEY (user_id) REFERENCES Yelp_Users(user_id) ON DELETE CASCADE,
FOREIGN KEY (business_id) REFERENCES Yelp_Businesses(business_id) ON DELETE CASCADE
);

CREATE TABLE Review_Votes(
review_id VARCHAR2(100),
funny NUMBER,
useful NUMBER,
cool NUMBER,
PRIMARY KEY(review_id),
FOREIGN KEY (review_id) REFERENCES Yelp_Reviews (review_id) ON DELETE CASCADE
);

CREATE TABLE Yelp_CheckIn(
business_id VARCHAR2(100),
check_info LONG,
type VARCHAR2(50),
FOREIGN KEY (business_id) REFERENCES Yelp_Businesses(business_id) ON DELETE CASCADE
);

CREATE INDEX mc_index ON Main_Categories (main_category);
CREATE INDEX sc_index ON Sub_Categories (sub_category);
CREATE INDEX ba_index ON Business_Attributes (attribute_key, attribute_value);
CREATE INDEX yu_index ON Yelp_Users (yelping_since, review_count, votes, average_stars, friends_count);
CREATE INDEX yr_index ON Yelp_Reviews(user_id, business_id, review_date);
CREATE INDEX yb_index ON Yelp_Businesses(business_id, business_name, city, stars, review_count);