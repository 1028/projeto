package Model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

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
	
	public void cadastrarUsuario(LoginTO login){
		// String do comando a ser realizado
		String insercao = "INSERT INTO LOGIN VALUES (?,?,?)";
		// pega conexão com o servidor MYSql
		conexao = null;
		// Prepara o comando para ser realizado
		PreparedStatement stm = null;

		try {
			conexao = obtemConexao();
			stm = prepararComando(insercao);
			stm.setString(1, login.getLogin());
			stm.setString(2, login.getSenha());
			stm.setInt(3, login.getTipoUsuario());
			stm.execute();
			conexao.commit();
		} catch (Exception e) {
			// tenta dar rollback na instrução realizada
			try {
				conexao.rollback();
				e.printStackTrace();
			} catch (SQLException sqlEx) {
				JOptionPane.showMessageDialog(
						null,
						"Não foi possível realizar o rollback \n\n"
								+ sqlEx.getStackTrace());
			}
		} finally {
			if (stm != null) {
				try {
					conexao.close();
				} catch (SQLException sqlEx) {
					JOptionPane.showMessageDialog(null,
							"Não foi possível fechar a conexão com o banco \n"
									+ sqlEx.getStackTrace());
				}
			}

		}	
	}

	public LoginTO efetuarLogin(LoginTO login) {
		String consulta = "SELECT * FROM LOGIN WHERE login = ?";

		conexao = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		LoginTO oLogin = new LoginTO();
		try {
			conexao = obtemConexao();
			stm = prepararComando(consulta);
			stm.setString(1, login.getLogin());
			//stm.setString(2, login.getSenha());
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
