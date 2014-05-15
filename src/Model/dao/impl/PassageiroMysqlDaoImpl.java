package Model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.dao.impl.BancoDeDados;
import Model.PassageiroTO;
import Model.dao.PassageiroDao;

public class PassageiroMysqlDaoImpl implements PassageiroDao{
	private Connection conexao;
	
	private Connection obtemConexao() throws SQLException{
		BancoDeDados bd = new BancoDeDados();
		return bd.obtemConexao();
	}
	
	public PreparedStatement prepararComando(String comando)
			throws SQLException {
		return conexao.prepareStatement(comando);
	}
	
	
	// Métodos utilizados para cadastrar passageiro.
	public void cadastraPassageiro(PassageiroTO passageiro) throws SQLException {
		String insercao = "INSERT INTO PASSAGEIRO VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
		conexao = null;

		PreparedStatement stm = null;

		try {
			conexao = obtemConexao();
			stm = prepararComando(insercao);
			stm.setString(1, passageiro.getNome());
			stm.setString(2, passageiro.getSobrenome());
			stm.setString(3, passageiro.getCelular());
			stm.setString(4, passageiro.getDataNascimento());
			stm.setString(5, passageiro.getEmail());
			stm.setInt(6, passageiro.getFormaTrata());
			stm.setInt(7, passageiro.getTipoPassageiro());
			stm.execute();
			//conexao.commit(); //ERRO AKI
		} catch (Exception e) {
			try {
				conexao.rollback();
				System.out.println(e.getStackTrace());
				throw e;
				
			} catch (SQLException sqlEx) {
				throw sqlEx;
			}
		} finally {
			if (stm != null) {
				try {
					conexao.close();
				} catch (SQLException sqlEX) {
					throw sqlEX;
				}
			}
		}
	}

	public void consultarTratamento() {
		String consulta = "SELECT * FROM TRATAMENTO";

		PreparedStatement stm = null;

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

	public void consultarPerfil() {
		String consulta = "SELECT * FROM PERFIL";

		PreparedStatement stm = null;

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
