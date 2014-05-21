package Model;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.dao.DaoFactory;
import Model.dao.AeronaveDao;

public class Aeronave {
	AeronaveTO dadosAeronave = null;
	
	public Aeronave(AeronaveTO aeronave){
		this.dadosAeronave = aeronave;
	}
	
	public Aeronave(int codigo) {
		setCodigoAeronave(codigo);
	}

	public String toString() {
		return String.format("%d - %s ", getCodigoAeronave(), getNome());
	}

	

	

	// Métodos do diagrama
	public void incluirAeronave(AeronaveTO aeronave) {
		DaoFactory factory = DaoFactory.getInstance();
		AeronaveDao dao = factory.getAeronaveDao();
		dao.inserirAeronave(dadosAeronave);
	}
	
	public ArrayList consultarTodas() throws SQLException {
		bd = new BancoDeDados();
		bd.consultarAeronave();
		
		return bd.getRetornoQuery();
	}

	public int consultarAeronave() throws SQLException {
		bd = new BancoDeDados();

		bd.consultarAeronave(getCodigoAeronave());

		ArrayList ret = bd.getRetornoQuery();

		if (ret.size() > 0) {
			setCodigoAeronave((int) ret.get(0));
			setTipoAeronave((int) ret.get(1));
			setNome((String) ret.get(2));
			setQtdAssentos((int) ret.get(3));
		}

		String s = "";
		System.out.println("Passou dentro da classe aeronave");

		for (int i = 0; i < ret.size(); i++) {
			s += "" + ret.get(i) + "\n";
		}

		System.out.println(s);
		return ret.size();
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
