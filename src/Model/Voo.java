package Model;

import java.sql.SQLException;
import java.util.List;

import Model.dao.DaoFactory;
import Model.dao.VooDao;
import Model.VooTO;

public class Voo {

	VooTO dadosVoo = null;

	public Voo(VooTO dadosVoo) {
		this.dadosVoo = dadosVoo;
	}
	
	public Voo() {
		
	}

	public void inserirVoo() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		VooDao dao = factory.getVooDao();
		dao.inserirVoo(dadosVoo);
	}

	public VooTO consultarVoo(int codigo) throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		VooDao dao = factory.getVooDao();
		return dao.consultarVoo(codigo);
	}
	
	public List<VooTO> consultar() throws Exception {
		DaoFactory factory = DaoFactory.getInstance();
		VooDao dao = factory.getVooDao();
		return dao.consultar();
	}
	
	public List<VooTO> consultar(int paginaAtual, int qtdRegistros) throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		VooDao dao = factory.getVooDao();
		return dao.consultar(paginaAtual,qtdRegistros);
	}
	
	public int total() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		VooDao dao = factory.getVooDao();
		return dao.total();
	}
	
	public int cadastrarVoo() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		VooDao dao = factory.getVooDao();
		return dao.cadastrarVoo(dadosVoo);
	}

	public int alterarVoo(VooTO antigo) throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		VooDao dao = factory.getVooDao();
		return dao.alterarVoo(dadosVoo, antigo);
	}

	public void excluirVoo() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		VooDao dao = factory.getVooDao();
		dao.excluirVoo(dadosVoo);
	}
}