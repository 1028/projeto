DELIMITER $$
CREATE PROCEDURE insereVoo(IN valorVoo float, IN codigoAeronave int, IN codigoSituacao int)
begin
	INSERT INTO VOO (COD_VOO, VALOR_VOO, COD_AERO_VOO, COD_SIT_VOO) VALUES (NULL,valorVoo,codigoAeronave,codigoSituacao);
	
	SELECT @@IDENTITY;

END $$
DELIMITER ;

