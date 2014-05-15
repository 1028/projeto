package Model;

import java.sql.SQLException;
import java.util.ArrayList;

public class Aeronave {
	private BancoDeDados bd;

	public Aeronave(int codigoAeronave, int tipoAeronave, int qtdAssentos,	String nome) throws SQLException {
		setCodigoAeronave(codigoAeronave);
		setTipoAeronave(tipoAeronave);
		setQtdAssentos(qtdAssentos);
		setNome(nome);
		bd = null;
	}
	
	public Aeronave() {
		
	}
	
	public String toString() {
		return String.format("%d - %s ", getCodigoAeronave(), getNome());
	}

	public Aeronave(int codigo) {
		setCodigoAeronave(codigo);
		bd = null;
	}

	

	// Métodos do diagrama
	public void incluirAeronave() throws SQLException {
		bd = new BancoDeDados();

		bd.inserirAeronave(getTipoAeronave(), getQtdAssentos(), getNome());
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
		bd = new BancoDeDados();

		bd.alterarAeronave(getCodigoAeronave(), getTipoAeronave(), getNome(),
				getQtdAssentos());
	}

	public void excluirAeronave() throws SQLException {
		bd = new BancoDeDados();

		bd.excluirAeronave(getCodigoAeronave());
	}
}
