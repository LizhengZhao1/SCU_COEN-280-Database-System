SELECT B.BName, B.BID
FROM Business B, Yelp_User U, Reviews R, (SELECT R.Business_ID, COUNT(R.Author)
                                          FROM Reviews R
                                          GROUP BY R.Business_ID
                                          HAVING COUNT(R.Author) = 1) temp
WHERE B.BID = temp.Business_ID AND R.Business_ID = temp.Business_ID AND U.user_id = R.Author AND U.first_name = 'John' AND U.last_name = 'Smith';