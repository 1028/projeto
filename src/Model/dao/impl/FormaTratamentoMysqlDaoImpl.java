package Model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.FormaTratamentoTO;
import Model.dao.FormaTratamentoDao;

public class FormaTratamentoMysqlDaoImpl implements FormaTratamentoDao {
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
		ArrayList<FormaTratamentoTO> resultado = new ArrayList<FormaTratamentoTO>();
		PreparedStatement stm = null;
		ResultSet rs = null;
		conexao = null;
		try {
			conexao = obtemConexao();
			stm = prepararComando(consulta);
			rs = stm.executeQuery();

			while (rs.next()) {
				FormaTratamentoTO formaTrat = new FormaTratamentoTO();
				formaTrat.setCodigo(rs.getInt(1));
				formaTrat.setNome(rs.getString(2));
				resultado.add(formaTrat);
			}

			return resultado;
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
		return resultado;
	}
}
