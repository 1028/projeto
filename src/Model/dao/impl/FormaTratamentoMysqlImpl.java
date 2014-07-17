package Model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import Model.FormaTratamentoTO;
import Model.dao.FormaTratamentoDao;

public class FormaTratamentoMysqlImpl implements FormaTratamentoDao {
	private Connection conexao;
	
	private Connection obtemConexao() throws SQLException {
		BancoDeDados bd = new BancoDeDados();
		return bd.obtemConexao();
	}
	
	public PreparedStatement prepararComando(String comando)
			throws SQLException {
		return conexao.prepareStatement(comando);
	}

	public List<FormaTratamentoTO> consultarFormaTratamento() {
		String consulta = "SELECT * FROM TRATAMENTO";

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
			// tenta dar rollback na instru��o realizada
			try {
				conexao.rollback();
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"Rollback realizado com sucesso");
			} catch (SQLException sqlEx) {
				JOptionPane.showMessageDialog(
						null,
						"N�o foi poss�vel realizar o rollback \n\n"
								+ sqlEx.getStackTrace());
			}
		} finally {
			if (stm != null) {
				try {
					conexao.close();
				} catch (SQLException sqlEx) {
					JOptionPane.showMessageDialog(null,
							"N�o foi poss�vel fechar a conex�o com o banco \n"
									+ sqlEx.getStackTrace());
				}
			}

		}
	}
}
