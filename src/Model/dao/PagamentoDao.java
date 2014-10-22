package Model.dao;

import Model.PagamentoTO;

public interface PagamentoDao {
	public void cadastrarPagamento(PagamentoTO pagamento);
	
	public void consultarPagamento(PagamentoTO pagamento);
}
