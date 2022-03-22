SELECT R.Business_ID, R.Author, U.first_name, U.last_name
FROM Yelp_User U, Reviews R, (SELECT Business_ID, MAX(Stars) as maxStar
							  FROM Reviews R
                              GROUP BY R.Business_ID) g
WHERE R.Author = U.user_id AND R.Business_ID = g.Business_ID AND R.Stars = g.maxStar
ORDER BY R.Business_ID;