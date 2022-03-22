SELECT U.last_name, U.first_name, U.user_id
FROM Yelp_User U 
WHERE U.user_id IN ((SELECT R.Author
                FROM Reviews R, Business B
                WHERE R.Business_ID = B.BID AND B.State = 'CA'
                GROUP BY R.Author
                HAVING Count(R.RID) >= 1)
                INTERSECT
                (SELECT R.Author
                FROM Reviews R, Business B
                WHERE R.Business_ID = B.BID AND B.State = 'AZ'
                GROUP BY R.Author
                HAVING Count(R.RID) >= 2));