package Model.dao;

import java.sql.SQLException;

import Model.PassageiroTO;

public interface PassageiroDao {
	public void cadastraPassageiro(PassageiroTO passageiro) throws SQLException;
}
