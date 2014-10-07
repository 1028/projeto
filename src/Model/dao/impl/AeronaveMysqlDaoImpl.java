package Model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.AeronaveTO;
import Model.LocalidadeTO;
import Model.dao.AeronaveDao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class AeronaveMysqlDaoImpl implements AeronaveDao {
	private Connection conexao;

	// ---------------------------------------------------------------------
	// Médotos referente ao Caso de Uso Manter Aeronave
	private Connection obtemConexao() throws SQLException {
		BancoDeDados bd = new BancoDeDados();
		return bd.obtemConexao();
	}

	// Métodos de insert
	public void inserirAeronave(AeronaveTO aeronave) {

		// String do comando a ser realizado
		String insercao = "INSERT INTO AERONAVE VALUES (NULL,?,?,?)";
		// pega conexão com o servidor MYSql
		conexao = null;
		// Prepara o comando para ser realizado
		PreparedStatement stm = null;

		try {
			conexao = obtemConexao();
			stm = prepararComando(insercao);
			//stm.setInt(1, aeronave.getCodigoAeronave());
			stm.setString(1, aeronave.getTipoAeronave());
			stm.setString(2, aeronave.getNome());
			stm.setInt(3, aeronave.getQtdAssentos());
			stm.execute();
			//conexao.commit();
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

	// método de update
	public void alterarAeronave(AeronaveTO aeronave) {
		String update = String
				.format("UPDATE AERONAVE SET TIPO_AERO = %d, NOME_AERO = '%s', QTD_ASSENTOS_AERO = %d WHERE COD_AERO = %d",
						aeronave.getTipoAeronave(), aeronave.getNome(), aeronave.getQtdAssentos(), aeronave.getCodigoAeronave());

		PreparedStatement stm = null;
		conexao = null;
		try {
			conexao = obtemConexao();
			stm = prepararComando(update);
			stm.execute();
			//conexao.commit();
		} catch (Exception e) {
			// tenta dar rollback na instrução realizada
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

	// Método de delete
	public void excluirAeronave(AeronaveTO aeronave) {
		//String delete = String.format(
		//		"DELETE FROM dbprojeto1.aeronave WHERE COD_AERO = %d", aeronave.getCodigoAeronave());

		String delete = "DELETE FROM AERONAVE WHERE COD_AERO =?";
		PreparedStatement stm = null;
		conexao = null;
		try {
			conexao = obtemConexao();
			stm = prepararComando(delete);
			stm.setInt(1, aeronave.getCodigoAeronave());
			stm.execute();
			//conexao.commit();
		} catch (Exception e) {
			// tenta dar rollback na instrução realizada
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

	public PreparedStatement prepararComando(String comando)
			throws SQLException {
		return conexao.prepareStatement(comando);
	}

	public List<AeronaveTO> consultarAeronave(AeronaveTO aeronave) {
		String consulta = String.format(
				"SELECT * FROM AERONAVE WHERE COD_AERO = %d", aeronave.getCodigoAeronave());
		conexao = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<AeronaveTO> resultado = new ArrayList<AeronaveTO>();
		try {
			conexao = obtemConexao();
			stm = prepararComando(consulta);
			rs = stm.executeQuery();
			// retornoQuery = (ArrayList) rs.getArray(0);
			// System.out.println(rs.getArray(0));
			if (rs.next()) {
				//VERIFICARR!!!
				AeronaveTO aero = new AeronaveTO();
				aero.setCodigoAeronave((rs.getInt(1)));
				aero.setTipoAeronave((rs.getString(2)));
				aero.setNome((rs.getString(3)));
				aero.setQtdAssentos((rs.getInt(4)));
				resultado.add(aero);
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
	
	public ArrayList<AeronaveTO> consultar() {
		String consulta = "SELECT * FROM AERONAVE";
		
		conexao = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<AeronaveTO> resultado = new ArrayList<AeronaveTO>();
		
		try {
			conexao = obtemConexao();
			stm = prepararComando(consulta);
			rs = stm.executeQuery();
			System.out.println("Executou a query.");
			while (rs.next()) {
				AeronaveTO aeronave = new AeronaveTO();
				aeronave.setCodigoAeronave((rs.getInt(1)));
				aeronave.setTipoAeronave((rs.getString(2)));
				aeronave.setNome((rs.getString(3)));
				aeronave.setQtdAssentos((rs.getInt(4)));
				resultado.add(aeronave);
			}
			
		} catch (Exception e) {
			// tenta dar rollback na instrução realizada
			try {
				conexao.rollback();
				e.printStackTrace();
			} catch (SQLException sqlEx) {
				sqlEx.getStackTrace();
			}
		} finally {
			if (stm != null) {
				try {
					conexao.close();
				} catch (SQLException sqlEx) {
					sqlEx.getStackTrace();
				}
			}

		}
		
		return resultado;

	}
	
}
