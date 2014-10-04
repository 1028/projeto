package Model;

import java.sql.SQLException;

public class PassagemCompra extends Passagem {

	PassagemCompraTO dadosPassagemCompra = null;

	public PassagemCompra(PassagemCompraTO dadosPassagemCompra) {
		this.dadosPassagemCompra = dadosPassagemCompra;
	}

	public void cancelarPassagem() throws SQLException {
		bd = new BancoDeDados();

		bd.cancelarPassagem(getNumeroPassagem());
	}

	public void comprarPassagem() {

	}

	public void calcularPassagem() {

	}

	public void emitirPassagem() {

	}
}
