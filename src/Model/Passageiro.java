package Model;

import java.sql.SQLException;
import java.util.List;

import Model.dao.DaoFactory;
import Model.dao.PassageiroDao;

public class Passageiro {

	PassageiroTO dadosPassageiro = null;

	public Passageiro(PassageiroTO dadosPassageiro) {
		this.dadosPassageiro = dadosPassageiro;
	}

	public void cadastrarPassageiro() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		PassageiroDao dao = factory.getPassageiroDao();
		dao.cadastraPassageiro(dadosPassageiro);

		System.out.println("Ok");
	}

	public void alterarPassageiro() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		PassageiroDao dao = factory.getPassageiroDao();
		dao.alterarPassageiro(dadosPassageiro);
	}
	
	public void excluirPassageiro() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		PassageiroDao dao = factory.getPassageiroDao();
		dao.excluirPassageiro(dadosPassageiro);
	}

	public List<PassageiroTO> consultarPassageiro() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		PassageiroDao dao = factory.getPassageiroDao();
		return dao.consultarPassageiro(dadosPassageiro);
	}
}
