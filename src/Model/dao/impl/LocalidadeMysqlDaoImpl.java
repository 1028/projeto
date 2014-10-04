package Model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.LocalidadeTO;
import Model.dao.LocalidadeDao;

public class LocalidadeMysqlDaoImpl implements LocalidadeDao {
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
	// Médotos referente ao Caso de Uso Manter Localidade
	
	public int total() {
		String consulta = "SELECT COUNT(COD_Localidade) FROM Localidade_GERAL";
		System.out.println("MOdificado");
		conexao = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		int total = 0;
		
		try {
			conexao = obtemConexao();
			stm = prepararComando(consulta);
			rs = stm.executeQuery();
			
			rs.next();
			total = rs.getInt(1);
		}
		catch (Exception e) {
			// tenta dar rollback na instrução realizada
			e.printStackTrace();
			try {
				conexao.rollback();
				e.printStackTrace();
			} catch (SQLException sqlEx) {
				sqlEx.getStackTrace();
			}
		}
		finally {
			if (stm != null) {
				try {
					conexao.close();
				} catch (SQLException sqlEx) {
					sqlEx.getStackTrace();
				}
			}

		}
		
		return total;
	}
	
	public List<LocalidadeTO> consultar() {
		
		String consulta = "SELECT * FROM Localidade";
		
		conexao = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<LocalidadeTO> resultado = new ArrayList<LocalidadeTO>();
		
		try {
			conexao = obtemConexao();
			stm = prepararComando(consulta);
			rs = stm.executeQuery();
			
			while (rs.next()) {
				LocalidadeTO localidade = new LocalidadeTO();
				localidade.setCodigo((rs.getInt(1)));
				localidade.setUf((rs.getString(2)));
				localidade.setNome((rs.getString(3)));
				
				resultado.add(localidade);
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
	
	public void inserir(LocalidadeTO Localidade) {
		String inserir = "INSERT INTO Localidade VALUES(NULL,?,?)";
		PreparedStatement stm = null;

		try {
			stm = prepararComando(inserir);
			// coloca a Origem
			stm.setString(1, Localidade.getUf());
			stm.setString(2, Localidade.getNome());
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

	

	

	

	

	public void alterar(int codigo, String uf, String nome) {

		String update = "UPDATE Localidade SET NOME_LOC = ?, CID_LOCA = ? WHERE COD_LOC = ?";

		PreparedStatement stm = null;

		try {
			stm = prepararComando(update);

			stm.setString(1, uf);
			stm.setString(2, nome);
			stm.setInt(3, codigo);

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

	
	public void excluir(LocalidadeTO Localidade) {
		String delete = "DELETE FROM Localidade WHERE COD_Loc = ?";

		PreparedStatement stm = null;

		try {
			stm = prepararComando(delete);
			stm.setInt(1, Localidade.getCodigo());
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
	public void consultarLocalidade(LocalidadeTO Localidade) {
		// TODO Auto-generated method stub
		
	}

}
