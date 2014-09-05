INSERT INTO AERONAVE values(null,'COMERCIAL','RED CARPET', 130);
INSERT INTO AERONAVE values(null,'ECONOMICO','TAJ MAHAL', 140);

INSERT INTO VOO values('001',375.50,1,1);
INSERT INTO VOO values('002', 355.50,1,1);
INSERT INTO VOO values('003', 475.50,1,1);


INSERT INTO LOCALIDADE_VOO VALUES (1,1,'O', '2014-10-10');
INSERT INTO LOCALIDADE_VOO VALUES (1,2,'D', '2014-11-10');
INSERT INTO LOCALIDADE_VOO VALUES (1,2,'E', NULL);


SELECT * FROM VOO A
	INNER JOIN LOCALIDADE_VOO B ON A.COD_VOO = B.COD_VOO
	INNER JOIN LOCALIDADE C ON B.COD_LOC = C.COD_LOC


SELECT A.COD_VOO, A.VALOR_VOO, A.COD_AERO_VOO, A.COD_SIT_VOO, B.TIPO, B.data, C.COD_LOC, C.NOME_LOC, C.CID_LOCA FROM VOO A
	INNER JOIN LOCALIDADE_VOO B ON A.COD_VOO = B.COD_VOO
	INNER JOIN LOCALIDADE C ON B.COD_LOC = C.COD_LOC

