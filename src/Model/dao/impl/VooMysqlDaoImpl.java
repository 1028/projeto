package Model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.VooTO;
import Model.dao.VooDao;

public class VooMysqlDaoImpl implements VooDao {
	private Connection conexao;
	
	private Connection obtemConexao() throws SQLException {
		BancoDeDados bd = new BancoDeDados();
		return bd.obtemConexao();
	}
	
	
	public PreparedStatement prepararComando(String comando)
			throws SQLException {
		return conexao.prepareStatement(comando);
	}
	// ---------------------------------------------------------------------
	// Médotos referente ao Caso de Uso Manter Voo
	public void inserirVoo(VooTO voo, int codigoLocalidade,	int codigoAeronave) {
		String inserir = "INSERT INTO VOO VALUES(NULL,?,?,?,?,?,?,?,?)";
		PreparedStatement stm = null;

		try {
			stm = prepararComando(inserir);
			// coloca a Origem
			stm.setString(1, voo.getOrigem());
			stm.setString(2, voo.getDestino());
			stm.setString(3, voo.getEscala());
			stm.setString(4, voo.getDateHora());
			stm.setDouble(5, voo.getValor());
			stm.setInt(6, codigoAeronave);
			stm.setInt(7, codigoLocalidade);
			stm.setInt(8, voo.getSituacao());
			stm.execute();
			conexao.commit();
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

			e.printStackTrace();
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

	// Classes de domínios

	public void consultarSituacao(VooTO voo) {
		String consulta = "SELECT * FROM SITUACAO WHERE COD_SIT = ?";

		PreparedStatement stm = null;

		try {
			stm = prepararComando(consulta);
			stm.setInt(1, voo.getCodigoVoo());
			rs = stm.executeQuery();

			if (rs.next()) {
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

	public void consultarAeronave() {
		String consulta = "SELECT * FROM AERONAVE";

		PreparedStatement stm = null;

		try {
			stm = prepararComando(consulta);
			rs = stm.executeQuery();

			while (rs.next()) {
				retornoQuery.add(rs.getInt(1));
				retornoQuery.add(rs.getInt(2));
				retornoQuery.add(rs.getString(3));
				retornoQuery.add(rs.getInt(4));
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

	public void consultarSituacao() {
		String consulta = "SELECT * FROM SITUACAO";

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

	public void consultarLocalidade() {
		String consulta = "SELECT * FROM LOCALIDADE";

		PreparedStatement stm = null;

		try {
			stm = prepararComando(consulta);
			rs = stm.executeQuery();

			while (rs.next()) {
				retornoQuery.add(rs.getInt(1));// codigo
				retornoQuery.add(rs.getString(2));// Nome
				retornoQuery.add(rs.getString(3));// UF
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

	public void alterarVoo(String origem, String destino, String escala,
			String dataHora, Double valor, int codigoAeronave,
			int codigoLocalidade, int situacao, int codigoVoo) {

		String update = "UPDATE VOO "
				+ "SET ORIGEM_VOO = ?, DESTINO_VOO = ?, ESCALA_VOO = ?, DATA_VOO = ?, "
				+ "VALOR_VOO = ?, COD_AERO_VOO = ?, COD_LOC_VOO = ?, COD_SIT_VOO = ? WHERE COD_VOO = ?";

		PreparedStatement stm = null;

		try {
			stm = prepararComando(update);

			stm.setString(1, origem);
			stm.setString(2, destino);
			stm.setString(3, escala);
			stm.setString(4, dataHora);
			stm.setDouble(5, valor);
			stm.setInt(6, codigoAeronave);
			stm.setInt(7, codigoLocalidade);
			stm.setInt(8, situacao);
			stm.setInt(9, codigoVoo);

			stm.execute();

			conexao.commit();

		} catch (Exception e) {
			e.printStackTrace();

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

	public ArrayList consultarVoo(int codigo) {
		String consulta = String
				.format("SELECT COD_VOO, ORIGEM_VOO, DESTINO_VOO, ESCALA_VOO, DATA_VOO, VALOR_VOO, COD_SIT, NOME_SIT, COD_AERO, TIPO_AERO, NOME_AERO, QTD_ASSENTOS_AERO FROM VOO V INNER JOIN SITUACAO ON COD_SIT = COD_SIT_VOO INNER JOIN AERONAVE ON COD_AERO = COD_AERO_VOO WHERE COD_VOO = %d",
						codigo);

		PreparedStatement stm = null;

		try {
			stm = prepararComando(consulta);
			rs = stm.executeQuery();

			while (rs.next()) {
				retornoQuery.add(rs.getInt(1)); // codigo do Voo
				retornoQuery.add(rs.getString(2)); // Origem do voo
				retornoQuery.add(rs.getString(3)); // Destino
				retornoQuery.add(rs.getString(4));// Escala
				retornoQuery.add(rs.getDate(5));// Data do voo
				retornoQuery.add(rs.getTime(5));// Hora do voo
				retornoQuery.add(rs.getDouble(6));// Valor
				retornoQuery.add(rs.getInt(7));// Codigo situação
				retornoQuery.add(rs.getString(8));// nome situação
				retornoQuery.add(rs.getInt(9));// Código aeronave
				retornoQuery.add(rs.getString(10));// Tipo aeronave
				retornoQuery.add(rs.getString(11));// nome Aeronave
				retornoQuery.add(rs.getInt(12));// qtd Assentos
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

		return retornoQuery;

	}

	public void excluirVoo(VooTO voo) {
		String delete = "DELETE FROM VOO WHERE COD_VOO = ?";

		PreparedStatement stm = null;

		try {
			stm = prepararComando(delete);
			stm.setInt(1, voo.getCodigoVoo());
			stm.execute();
			conexao.commit();

		} catch (Exception e) {
			try {
				conexao.rollback();
			} catch (SQLException sqlEx) {

			}
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
