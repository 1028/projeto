package Model.dao;

import Model.ChequeTO;

public interface ChequeDao extends PagamentoDao{
	public void cadastrarCheque(ChequeTO cheque);
	
	public void consultarCheque(ChequeTO cheque);
}
