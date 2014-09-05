package Model.dao;

import java.sql.SQLException;
import java.util.List;

import Model.PassageiroTO;

public interface PassageiroDao {
	public void cadastraPassageiro(PassageiroTO passageiro) throws SQLException;
	
	public void alterarPassageiro(PassageiroTO passageiro);
	
	public void excluirPassageiro(PassageiroTO passageiro);
	
	public List<PassageiroTO> consultarPassageiro(PassageiroTO passageiro);
}
