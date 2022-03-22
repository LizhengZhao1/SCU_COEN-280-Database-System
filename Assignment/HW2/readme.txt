Lizheng Zhao, SCU ID:W1608646

Name Of Database: db11g

Table1: Yelp_User
Table2: beFriend
Table3: beComplimented
Table4: Business
Table5: Reviews
Table6: Comments
Table7: Operation
Table8: Business_Cat
Table9: Belong
Table10: Check_In
Table11: Check_Into

The Result of q1:

BNAME
Bay Area Coffee Shop
China  Coffee Toffee
SMITH THOMSON 
Catch Your Big Break
Coffee Bar & Bistro
The Cinebar
Petrified Forest National Park
Hastings Water Works
Cafe Gourmet
Grand Canyon National park
Big Surf
Hobee's
Yellow Stone National Park
Connecticut Bar & Restaurent

The Result of q2:

FIRST_NAME	LAST_NAME
Mark	Davis

The Result of q3:

BNAME	BID
Hobee's	9
Grand Canyon National park	13

The Result of q4:

BUSINESS_ID	AUTHOR	FIRST_NAME	LAST_NAME
1	13	John	Zhang
2	11	Maria	Rodriguez
3	1	John	Smith
4	4	Aditya	Awasthi
4	11	Maria	Rodriguez
5	9	Mark	Davis
6	5	James	Williams
7	12	Freya	Wilson
8	5	James	Williams
9	1	John	Smith
10	12	Freya	Wilson
11	4	Aditya	Awasthi
12	5	James	Williams
13	1	John	Smith
14	11	Maria	Rodriguez

The Result of q5:

RID	BNAME	FIRST_NAME	LAST_NAME	PUBLISH_DATE
6	China  Coffee Toffee	Aditya	Awasthi	26-SEP-07
4	China  Coffee Toffee	Maria	Rodriguez	02-OCT-07

The Result of q6:

BUSINESS_ID	MAX(R.VOTE_COOL)	MAX(R.VOTE_FUNNY)	MAX(R.VOTE_USEFUL)
6	2	3	1
7	4	2	5
8	1	6	2
10	2	3	4
4	2	5	3
3	2	4	3
9	3	2	8

The Result of q7:

INFO	BID
Checkin Info 3	3
Checkin Info 4	4
Checkin Info 2	2

The Result of q8:


BID	BNAME	STARS	AVG_STARS	REVIEW_COUNT	STREET_ADDRESS	COUNTY	STATE	ZIP_CODE
10	Cafe Gourmet	4	4.5	2	80 N Market St	San Jose	CA	95110
10	Cafe Gourmet	5	4.5	2	80 N Market St	San Jose	CA	95110
The Result of q9:

no data found

The Result of q10:

BID	BCNAME
36	Bars
3	Coffee Shops
10	Food and More
4	Food and More

