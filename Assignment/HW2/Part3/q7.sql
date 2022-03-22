SELECT C.INFO, B.BID
FROM Business B, Check_In C, (SELECT Business_ID
                              FROM Reviews R
                              GROUP BY Business_ID
                              HAVING COUNT(RID) > 2) R
WHERE B.BID = C.BID AND C.BID = R.Business_ID;