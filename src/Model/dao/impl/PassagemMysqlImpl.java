package Model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class PassagemMysqlImpl {
	private Connection conexao;

	private Connection obtemConexao() throws SQLException {
		BancoDeDados bd = new BancoDeDados();
		return bd.obtemConexao();
	}

	public PreparedStatement prepararComando(String comando)
			throws SQLException {
		return (PreparedStatement) conexao.prepareStatement(comando);
	}
	// ---------------------------------------------------------------------
	// Médotos referente ao Caso de Uso Comprar Passagem


	// Método para armazenar os dados da passagem comprada.
	public void cadastrarPassagem() {
		String insercao = "INSERT INTO PASSAGEM VALUES (NULL, ?, ?, ?, ?, ?)";

		PreparedStatement stm = null;

		try {
			stm = prepararComando(insercao);
			/*
			 * stm.setString(1, sNome); stm.setString(2, sSobrenome);
			 * stm.setString(3, sCel); stm.setString(4, dData); stm.setString(5,
			 * sEmail); stm.setInt(6, iCodTrata); stm.setInt(7, iCodPer);
			 */
			stm.execute();
			conexao.commit();
		} catch (Exception e) {
			try {
				conexao.rollback();
				System.out.print("roll");
			} catch (SQLException sqlEx) {
				System.out.print("Ex");
			}
			e.printStackTrace();
		} finally {
			if (stm != null) {
				try {
					conexao.close();
				} catch (SQLException sqlEX) {
					System.out.print("Ex2");
				}
			}
		}
	}

	public void cadastrarPagamento(String sNomeTitular, String sCpf,
			String sData, String sFormaPagamento) {
		String insercao = "INSERT INTO PAGAMENTO VALUES (NULL, ?, ?, ?, ?)";
		PreparedStatement stm = null;

		try {
			stm = prepararComando(insercao);
			stm.setString(1, sNomeTitular);
			stm.setString(2, sCpf);
			stm.setString(3, sData);
			stm.setString(4, sFormaPagamento);
			stm.execute();
			conexao.commit();
		} catch (Exception e) {
			try {
				conexao.rollback();
			} catch (SQLException sqlEx) {

			}
			e.printStackTrace();
		} finally {
			if (stm != null) {
				try {
					conexao.close();
				} catch (SQLException sqlEx) {

				}
			}
		}
	}

	public void cadastrarCheque(String sBanco, String sAgencia, String sConta,
			int iCodPagamentoCheque) {
		String insercao = "INSERT INTO CHEQUE VALUES (NULL, ?, ?, ?, ?)";
		PreparedStatement stm = null;

		try {
			stm = prepararComando(insercao);
			stm.setString(1, sBanco);
			stm.setString(2, sAgencia);
			stm.setString(3, sConta);
			stm.setInt(4, iCodPagamentoCheque);
			stm.execute();
			conexao.commit();
		} catch (Exception e) {
			try {
				conexao.rollback();
			} catch (SQLException sqlEx) {

			}
			e.printStackTrace();
		} finally {
			if (stm != null) {
				try {
					conexao.close();
				} catch (SQLException sqlEx) {

				}
			}
		}
	}

	// ---------------------------------------------------------------------
	// Médotos referente ao Caso de Uso Cancelar Passagem

	public void consultarPassagem(int numPassagem) {
		String consulta = String.format(
				"SELECT * FROM PASSAGEM WHERE NUM_PASS = %d", numPassagem);

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

	public void tranferirPassagem(int numPassagem) {
		String update = String.format("", numPassagem);
		PreparedStatement stm = null;

		try {
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

	public void cancelarPassagem(int numPassagem) {
		String update = String.format("", numPassagem);
		PreparedStatement stm = null;

		try {
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
