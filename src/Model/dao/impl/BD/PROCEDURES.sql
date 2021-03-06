DELIMITER $$

CREATE PROCEDURE SELECIONA_VOO (IN parCodigo INT)
BEGIN
	SELECT * FROM VOO WHERE COD_VOO = parCodigo;
	
	SELECT A.COD_VOO, 
			A.VALOR_VOO, 
			(CONCAT(COD_AERO , ' - ' , NOME_AERO)) AS AERO_DESC,
			NOME_SIT,
			CONCAT(NOME_LOC, ' - ', CID_LOCA) AS ORIGEM
		FROM VOO A
		INNER JOIN AERONAVE ON COD_AERO = COD_AERO_VOO
		INNER JOIN SITUACAO ON COD_SIT = COD_SIT_VOO
		INNER JOIN LOCALIDADE_VOO B ON A.COD_VOO = B.COD_VOO
		INNER JOIN LOCALIDADE C ON B.COD_LOC = C.COD_LOC
		GROUP BY 1,2,3

	SELECT * FROM VOO;
	SELECT * FROM LOCALIDADE_VOO
	SELECT * FROM AERONAVE
	SELECT * FROM SITUACAO
	SELECT * FROM LOCALIDADE
	
	SELECT A.COD_VOO, A.VALOR_VOO, A.COD_AERO_VOO, A.COD_SIT_VOO, B.TIPO, B.DATA, GROUP_CONCAT(C.NOME_LOC, ' - ', C.CID_LOCA) FROM VOO A
		INNER JOIN LOCALIDADE_VOO B ON A.COD_VOO = B.COD_VOO
		INNER JOIN LOCALIDADE C ON B.COD_LOC = C.COD_LOC
		WHERE A.COD_VOO = '1'
		GROUP BY 1,2,3
	
	
	SELECT A.COD_VOO, A.VALOR_VOO, B.TIPO, B.data
			(SELECT CONCAT(C.CID_LOCA, ' - ', C.NOME_LOC))
			FROM VOO A
		INNER JOIN LOCALIDADE_VOO B ON A.COD_VOO = B.COD_VOO
		INNER JOIN LOCALIDADE C ON C.COD_LOC = B.COD_LOC
		

END $$

DELIMITER ;