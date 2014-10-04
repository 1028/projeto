package Model;

import java.sql.SQLException;

public class Pagamento {
	
	private BancoDeDados bd;

	public Pagamento() {
		
	}

	public void pagar() throws SQLException {
		bd = new BancoDeDados();
		System.out.println(getNomeTitular());
		bd.cadastrarPagamento(getNomeTitular(), getCpf(), getDataPagamento(),
				getFormaPagamento());
	}

	public void consultarPagamneto() {

	}
}
