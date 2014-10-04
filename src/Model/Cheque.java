package Model;

import java.sql.SQLException;

public class Cheque extends Pagamento {

	private BancoDeDados bd;

	public Cheque() {

	}

	public void cadastrarCheque() throws SQLException {
		bd = new BancoDeDados();

		// bd.cadastrarPagamento(getNomeTitular(), getCpf(), getDataPagamento(),
		// getFormaPagamento());

		System.out.println(getNomeTitular());
		bd.cadastrarCheque(getBanco(), getAgencia(), getConta(), 1);// getCodigoPagamento());
	}

	public void consultarCheque() {

	}
}
