package Model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import Model.LoginTO;
import Model.dao.LoginDao;

public class LoginMysqlImpl implements LoginDao {
	private Connection conexao;

	private Connection obtemConexao() throws SQLException {
		BancoDeDados bd = new BancoDeDados();
		return bd.obtemConexao();
	}

	public PreparedStatement prepararComando(String comando)
			throws SQLException {
		return (PreparedStatement) conexao.prepareStatement(comando);
	}

	public void efetuarLogin(LoginTO login) {
		// public List<LoginTO> efetuarLogin(){

		String consulta = "SELECT * FROM LOGIN";

		conexao = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			conexao = obtemConexao();
			stm = prepararComando(consulta);
			rs = stm.executeQuery();

			LoginTO loginAr = new LoginTO();
			// login.setTipoUsuario((rs.getString(1)));
		} catch (Exception e) {

		}

	}

}
