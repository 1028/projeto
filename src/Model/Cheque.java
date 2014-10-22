package Model;

import java.sql.SQLException;

public class Cheque extends Pagamento {

	ChequeTO dadosCheque = null;

	public Cheque(PagamentoTO dadosPagamento, ChequeTO dadosCheque) {
		super(dadosPagamento);
		this.dadosCheque = dadosCheque;
	}

	public void cadastrarCheque() throws SQLException {

	}

	public void consultarCheque() {

	}
}
