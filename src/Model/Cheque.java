package Model;

import java.sql.SQLException;

import Model.dao.ChequeDao;
import Model.dao.DaoFactory;

public class Cheque extends Pagamento {

	ChequeTO dadosCheque = null;

	public Cheque(PagamentoTO dadosPagamento, ChequeTO dadosCheque) {
		super(dadosPagamento);
		this.dadosCheque = dadosCheque;
	}

	public void cadastrarCheque() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		ChequeDao dao = factory.getChequeDao();
		dao.cadastrarCheque(dadosCheque);
	}

	public void consultarCheque() {
		DaoFactory factory = DaoFactory.getInstance();
		ChequeDao dao = factory.getChequeDao();
		dao.consultarCheque(dadosCheque);
	}
}
