package Controller;

import java.sql.SQLException;

import Model.Passageiro;

public class GerenciaPassageiro {

	public void cadastrarPassageiro(String sNome, String sSobrenome,
			String sTelefone, String sEmail, String sDataNascimento,
			int iFormaTrata, int iTipoPassageiro) throws SQLException {

		Passageiro oPassageiro = new Passageiro(sNome, sSobrenome, sTelefone,
				sEmail, sDataNascimento, iFormaTrata, iTipoPassageiro);

		oPassageiro.cadastrarPassageiro();
	}
}
