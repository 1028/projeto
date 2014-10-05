package Model.dao;

import java.sql.SQLException;
import java.util.List;

import Model.VooTO;

public interface VooDao {

	public void inserirVoo(VooTO voo);
	
	public void consultarVoo(VooTO voo);
	
	public void alterarVoo(VooTO voo);
	
	public void excluirVoo(VooTO voo);
	
	public int cadastrarVoo(VooTO voo) throws SQLException;
	
	public List<VooTO> consultar();
	
	public int total();
}
