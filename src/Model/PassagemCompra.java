package Model;

import java.sql.SQLException;

import Model.dao.DaoFactory;
import Model.dao.PassagemCompraDao;

public class PassagemCompra extends Passagem {

	PassagemCompraTO dadosPassagemCompra = null;

	public PassagemCompra(PassagemTO dadosPassagem, PassagemCompraTO dadosPassagemCompra) {
		super(dadosPassagem);
		this.dadosPassagemCompra = dadosPassagemCompra;
	}

	public void cancelarPassagem() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		PassagemCompraDao dao = factory.getPassagemCompraDao();
		dao.cancelarPassagem(dadosPassagemCompra);
	}

	public void comprarPassagem() {
		DaoFactory factory = DaoFactory.getInstance();
		PassagemCompraDao dao = factory.getPassagemCompraDao();
		dao.cadastrarPassagem(dadosPassagemCompra);
	}

	public void calcularPassagem() {

	}

	public void emitirPassagem() {

	}
}
