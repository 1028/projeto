package Model;

import java.sql.SQLException;

import Model.dao.DaoFactory;
import Model.dao.PassageiroDao;

public class Passageiro {

	PassageiroTO dadosPassageiro = null;

	public Passageiro(PassageiroTO dadosPassageiro){
		this.dadosPassageiro = dadosPassageiro;
	}

	public void cadastrarPassageiro() throws SQLException {
		DaoFactory factory = DaoFactory.getInstance();
		PassageiroDao dao = factory.getPassageiroDao();
		dao.cadastraPassageiro(dadosPassageiro);

		System.out.println("Ok");
	}

	public void consultarPassageiro() {

	}
}
