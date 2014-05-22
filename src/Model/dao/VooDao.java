package Model.dao;

import Model.VooTO;

public interface VooDao {

	public void inserirVoo(VooTO voo);
	
	public void consultarVoo(VooTO voo);
	
	public void alterarVoo(VooTO voo);
	
	public void excluirVoo(VooTO voo);
}
