SELECT First_Name, Last_Name
FROM Yelp_User U, Business B, Reviews R
WHERE U.user_id = R.Author AND R.Business_ID = B.BID AND B.State = 'OH';