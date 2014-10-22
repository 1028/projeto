package Model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.PassagemTO;
import Model.dao.PassagemDao;

public class PassagemMysqlDaoImpl implements PassagemDao {
	private Connection conexao;

	private Connection obtemConexao() throws SQLException {
		BancoDeDados bd = new BancoDeDados();
		return bd.obtemConexao();
	}

	public PreparedStatement prepararComando(String comando)
			throws SQLException {
		return (PreparedStatement) conexao.prepareStatement(comando);
	}

	public void consultarPassagem(PassagemTO passagem) {
		String consulta = String.format(
				"SELECT * FROM PASSAGEM WHERE NUM_PASS = %d", passagem.getNumeroPassagem());

		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = prepararComando(consulta);
			rs = stm.executeQuery();

			while (rs.next()) {
			//	retornoQuery.add(rs.getInt(1));
		//		retornoQuery.add(rs.getDate(2));
			}
		} catch (Exception e) {
			try {
				conexao.rollback();

			} catch (SQLException sqlEx) {

			} finally {
				if (stm != null) {
					try {
						conexao.close();
					} catch (SQLException sqlEx) {

					}
				}
			}
		}
	}

	public void tranferirPassagem(PassagemTO passagem) {
		String update = String.format("", passagem.getNumeroPassagem());
		conexao = null;
		PreparedStatement stm = null;

		try {
			conexao = obtemConexao();
			stm = prepararComando(update);
			stm.execute();
			conexao.commit();
		} catch (Exception e) {
			try {
				conexao.rollback();
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
