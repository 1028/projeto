package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.dao.DaoFactory;
import Model.dao.AeronaveDao;

public class Aeronave {
	AeronaveTO dadosAeronave = null;

	public Aeronave(AeronaveTO dadosAeronave) {
		this.dadosAeronave = dadosAeronave;
	}

	// Métodos do diagrama
	public void incluirAeronave(AeronaveTO aeronave) {
		DaoFactory factory = DaoFactory.getInstance();
		AeronaveDao dao = factory.getAeronaveDao();
		dao.inserirAeronave(dadosAeronave);
	}

	public List<AeronaveTO> consultarAeronave() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		AeronaveDao dao = factory.getAeronaveDao();
		return dao.consultarAeronave(dadosAeronave);
	}
	
	public ArrayList<AeronaveTO> consultar() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		AeronaveDao dao = factory.getAeronaveDao();
		return dao.consultar();
	}

	public void alterarAeronave() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		AeronaveDao dao = factory.getAeronaveDao();
		dao.alterarAeronave(dadosAeronave);
	}

	public void excluirAeronave() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		AeronaveDao dao = factory.getAeronaveDao();
		dao.excluirAeronave(dadosAeronave);
	}
	
	
}
