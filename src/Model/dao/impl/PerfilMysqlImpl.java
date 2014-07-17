package Model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import Model.PerfilTO;
import Model.dao.PerfilDao;

public class PerfilMysqlImpl implements PerfilDao {
	private Connection conexao;
	
	private Connection obtemConexao() throws SQLException {
		BancoDeDados bd = new BancoDeDados();
		return bd.obtemConexao();
	}
	
	public PreparedStatement prepararComando(String comando)
			throws SQLException {
		return conexao.prepareStatement(comando);
	}
	
	public List<PerfilTO> consultarPerfil() {
		String consulta = "SELECT * FROM PERFIL";

		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = prepararComando(consulta);
			rs = stm.executeQuery();

			while (rs.next()) {
				retornoQuery.add(rs.getInt(1));
				retornoQuery.add(rs.getString(2));
			}

		} catch (Exception e) {
			// tenta dar rollback na instrução realizada
			try {
				conexao.rollback();
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"Rollback realizado com sucesso");
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
}
