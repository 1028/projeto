package Controller;

import java.sql.SQLException;
import Model.Aeronave;

public class GerenciaAeronave {
	private Aeronave aero;

	public void incluirAeronave(int cod, int tipo, int qntAssentos, String nome)
			throws SQLException {
		aero = new Aeronave(cod, tipo, qntAssentos, nome);

		aero.incluirAeronave();

	}

	public Aeronave consultarAeronave(int cod) throws SQLException {
		aero = new Aeronave(cod);
		
		int qtdItens = aero.consultarAeronave();
		if (qtdItens > 0) {
		}
		
		return aero;
	}

	public void alterarAeronave(int cod, int tipo, int qntAssentos, String nome) throws SQLException {
		aero = new Aeronave(cod, tipo, qntAssentos, nome);
		
		aero.alterarAeronave();
	}

	public void excluirAeronave(int cod, int tipo, int qntAssentos, String nome)
			throws SQLException {
		aero = new Aeronave(cod, tipo, qntAssentos, nome);

		aero.excluirAeronave();
	}
}
