package Model;

import java.sql.SQLException;

public class PassagemCompra extends Passagem {

	PassagemCompraTO dadosPassagemCompra = null;

	public PassagemCompra(PassagemTO dadosPassagem, PassagemCompraTO dadosPassagemCompra) {
		super(dadosPassagem);
		this.dadosPassagemCompra = dadosPassagemCompra;
	}

	public void cancelarPassagem() throws SQLException {
		
	}

	public void comprarPassagem() {

	}

	public void calcularPassagem() {

	}

	public void emitirPassagem() {

	}
}
