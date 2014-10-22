package Model;

import java.sql.SQLException;

import Model.dao.DaoFactory;
import Model.dao.PagamentoDao;

public class Pagamento {
	
	PagamentoTO dadosPagamento = null;

	public Pagamento(PagamentoTO dadosPagamento) {
		this.dadosPagamento = dadosPagamento;
	}

	public void pagar() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		PagamentoDao dao = (PagamentoDao) factory.getPagamentoDao();
		dao.cadastrarPagamento(dadosPagamento);
	}

	public void consultarPagamneto() {
		DaoFactory factory = DaoFactory.getInstance();
		PagamentoDao dao = (PagamentoDao) factory.getPagamentoDao();
		dao.consultarPagamento(dadosPagamento);
	}
}
