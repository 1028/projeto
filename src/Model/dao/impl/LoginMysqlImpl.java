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

	public LoginTO efetuarLogin(LoginTO login) {
		String consulta = "SELECT * FROM LOGIN WHERE login = ? AND SENHA = ?";

		conexao = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		LoginTO oLogin = new LoginTO();
		try {
			conexao = obtemConexao();
			stm = prepararComando(consulta);
			stm.setString(1, login.getLogin());
			stm.setString(2, login.getSenha());
			rs = stm.executeQuery();

			if (rs.next()) {
				oLogin.setLogin(rs.getString(1));
				oLogin.setSenha(rs.getString(2));
				oLogin.setTipoUsuario(Integer.parseInt((rs.getString(3))));

				return oLogin;
			}
		} catch (Exception e) {
			try {
				conexao.rollback();
				e.printStackTrace();
			} catch (SQLException sqlEx) {

			}
		} finally {
			if (stm != null) {
				try {
					conexao.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
		return oLogin;
	}
}
