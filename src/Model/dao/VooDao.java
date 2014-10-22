package Model.dao;

import java.sql.SQLException;
import java.util.List;

import Model.VooTO;

public interface VooDao {

	public void inserirVoo(VooTO voo);
	
	public VooTO consultarVoo(int codigo);
	
	public int alterarVoo(VooTO novo, VooTO antigo) throws SQLException;
	
	public void excluirVoo(VooTO voo) throws SQLException;
	
	public int cadastrarVoo(VooTO voo) throws SQLException;
	
	public List<VooTO> consultar() throws Exception;
	
	public List<VooTO> consultar(int paginaAtual, int qtdRegistros);
	
	public int total();
}
