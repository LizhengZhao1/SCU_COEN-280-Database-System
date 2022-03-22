SELECT R.Business_ID, MAX(R.Vote_Cool), MAx(R.Vote_Funny), MAX(R.Vote_Useful)
FROM Reviews R, Business B
WHERE R.Business_ID = B.BID AND B.State = 'CA'
GROUP BY R.Business_ID;