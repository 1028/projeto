package Model;

import java.sql.SQLException;
import java.util.List;

import Model.dao.DaoFactory;
import Model.dao.VooDao;

public class Voo {

	VooTO dadosVoo = null;

	public Voo(VooTO dadosVoo) {
		this.dadosVoo = dadosVoo;
	}

	public void inserirVoo() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		VooDao dao = factory.getVooDao();
		dao.inserirVoo(dadosVoo);
	}

	public void consultarVoo() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		VooDao dao = factory.getVooDao();
		dao.consultarVoo(dadosVoo);
	}
	
	public List<VooTO> consultar(int pag) throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		VooDao dao = factory.getVooDao();
		return dao.consultar(pag);
	}
	
	public int total() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		VooDao dao = factory.getVooDao();
		return dao.total();
	}

	public void alterarVoo() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		VooDao dao = factory.getVooDao();
		dao.alterarVoo(dadosVoo);
	}

	public void excluirVoo() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		VooDao dao = factory.getVooDao();
		dao.excluirVoo(dadosVoo);
	}
}