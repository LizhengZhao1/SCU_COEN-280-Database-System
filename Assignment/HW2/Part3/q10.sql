SELECT B.BID, cat.BCName
FROM Business_Cat cat, Business B, Belong be
WHERE B.BID = be.BID AND be.BCID = cat.BCID AND cat.BCName != 'Restaurants' AND be.BID IN (SELECT B.BID
																						   FROM Business_Cat cat, Business B, Belong be
																						   WHERE cat.BCID = be.BCID AND B.BID = be.BID AND B.State = 'CA' AND cat.BCName = 'Restaurants'
																						   UNION
																						   SELECT B.BID
																						   FROM Business_Cat cat, Business B, Belong be
																					       WHERE cat.BCID = be.BCID AND B.BID = be.BID AND B.State = 'CT' AND cat.BCName = 'Restaurants')
ORDER BY cat.BCName;