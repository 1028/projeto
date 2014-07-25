package Model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<VooTO> consultar() {
		
		String consulta = "SELECT * FROM VOO";
		
		conexao = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<VooTO> resultado = new ArrayList<VooTO>();
		
		try {
			conexao = obtemConexao();
			stm = prepararComando(consulta);
			rs = stm.executeQuery();
			
			while (rs.next()) {
				VooTO voo = new VooTO();
				voo.setCodigoVoo((rs.getInt(1)));
				voo.setOrigem((rs.getString(2)));
				voo.setDestino((rs.getString(3)));
				voo.setEscala((rs.getString(4)));
				voo.setDateHora(rs.getString(5));
				voo.setValor((rs.getDouble(6)));
				voo.setSituacao((rs.getInt(8)));
				resultado.add(voo);
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


	@Override
	public void inserirVoo(VooTO voo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void consultarVoo(VooTO voo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void alterarVoo(VooTO voo) {
		// TODO Auto-generated method stub
		
	}
}
