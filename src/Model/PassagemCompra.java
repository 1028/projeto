package Model;

import java.sql.SQLException;

public class PassagemCompra extends Passagem {

	private BancoDeDados bd;

	public PassagemCompra() {
		
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
