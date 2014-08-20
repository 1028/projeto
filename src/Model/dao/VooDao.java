package Model.dao;

import java.util.List;

import Model.VooTO;

public interface VooDao {

	public void inserirVoo(VooTO voo);
	
	public void consultarVoo(VooTO voo);
	
	public void alterarVoo(VooTO voo);
	
	public void excluirVoo(VooTO voo);
	
	public List<VooTO> consultar();
	
	public int total();
}
