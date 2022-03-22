SELECT B.BID, B.BName, R.Stars, Temp.Avg_Stars, Temp.Review_Count, B.Street_Address, B.County, B.State, B.Zip_Code
FROM Business B, Reviews R, (SELECT R.Business_ID AS BID, COUNT(R.RID) AS Review_Count, AVG(R.Stars) AS Avg_Stars
				             FROM Reviews R
				             WHERE R.Business_ID IN (SELECT DISTINCT R.Business_ID
										             FROM Reviews R
                                                     WHERE R.Vote_Cool >= 1 OR R.Vote_Funny >= 1 OR R.Vote_Useful >=1
                                                     MINUS
                                                     SELECT R.Business_ID
									                 FROM Reviews R
										             WHERE R.Stars <= 3 AND R.Publish_Date >= to_date('2007-01-01', 'yyyy-mm-dd'))
				            GROUP BY Business_ID
				            HAVING AVG(R.Stars) >= 2 AND COUNT(R.RID) >= 2) Temp
WHERE B.BID = Temp.BID AND B.BID = R.Business_ID AND R.Business_ID = Temp.BID
ORDER BY B.BName;