package Model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.LocalidadeTO;
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
	
	public CallableStatement prepararCall(String call)
			throws SQLException {
		return conexao.prepareCall(call);
	}
	
	// ---------------------------------------------------------------------
	// M�dotos referente ao Caso de Uso Manter Voo
	
	public int total() {
		String consulta = "SELECT COUNT(COD_VOO) FROM VOO_GERAL";
		
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
			// tenta dar rollback na instru��o realizada
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
	
	public int cadastrarVoo(VooTO voo) throws SQLException {
		int confirmacao = -1;
		
		String proc = "CALL insereVoo(?,?,?)"; //Parametros da PROC: valorVoo,codigoAeronave, codigoSituacao - Retorna a PK inserida
		String insercao = "INSERT INTO LOCALIDADE_VOO(COD_VOO,COD_LOC,TIPO,DATA) VALUES (?,?,?,?)";
		
		CallableStatement cStm = null;
		PreparedStatement stm = null;
		conexao = null;
		ResultSet rs = null;
		
		try {
			conexao = obtemConexao();
			conexao.setAutoCommit(false);
			
			cStm = prepararCall(proc);
			
			cStm.setDouble(1,voo.getValor());
			cStm.setInt(2,voo.getAeronave());
			cStm.setInt(3, voo.getSituacao());
			
			rs = cStm.executeQuery();
			int codigo;
			if(rs.next()) {
				codigo = rs.getInt(1);
			}
			else {
				codigo = -1;
			}
			
			stm = prepararComando(insercao);
			
			stm.setInt(1,codigo);
			stm.setInt(2,voo.getOrigemObj().getCodigo());
			stm.setString(3,voo.getOrigemObj().getTipo());
			stm.setString(4,voo.getDateHora());
			
			stm.addBatch();
			
			stm.setInt(1,codigo);
			stm.setInt(2,voo.getDestinoObj().getCodigo());
			stm.setString(3,voo.getDestinoObj().getTipo());
			stm.setString(4,voo.getDateHora());
			
			stm.addBatch();
			
			stm.setInt(1,codigo);
			stm.setInt(2,voo.getEscalaObj().getCodigo());
			stm.setString(3,voo.getEscalaObj().getTipo());
			stm.setString(4,voo.getDateHora());
			
			stm.addBatch();
			
			stm.executeBatch();
			
			cStm.close();
			stm.close();
			
			conexao.commit();
			confirmacao = codigo;
		}
		catch(Exception e) {
			conexao.rollback();
			throw e;
		}
		finally {
			if (cStm != null && stm != null) {
				try {
					conexao.close();
					
				} catch (SQLException sqlEX) {
					throw sqlEX;
				}
			}
		}
		
		return confirmacao;
	}
	
	public List<VooTO> consultar(int paginaAtual, int qtdRegistros) {
		
		String consulta = "SELECT * FROM VOO_GERAL LIMIT ?,?";
		
		conexao = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<VooTO> resultado = new ArrayList<VooTO>();
		
		try {
			conexao = obtemConexao();
			stm = prepararComando(consulta);

			stm.setInt(1, paginaAtual);
			stm.setInt(2, qtdRegistros);
			
			rs = stm.executeQuery();
			
			
			while (rs.next()) {
				VooTO voo = new VooTO();
				voo.setCodigoVoo((rs.getInt(1)));
				voo.setValor((rs.getDouble(2)));
				//3 - CODIGO DA AERONAVE
				//4 - COD DA SITUACAO
				String auxiliar = rs.getString(5);
				
				String[] strLocalidade;
				if(auxiliar != null)
				{
					strLocalidade = rs.getString(5).split("-");//Pega a origem, �ndice 5 do retorno da query
					LocalidadeTO origem = new LocalidadeTO(Integer.parseInt(strLocalidade[0]), strLocalidade[2], strLocalidade[1], "O");
					voo.setOrigem(origem);
					//6 - DATA DA ORIGEM	
				}
				else {
					LocalidadeTO localidade = new LocalidadeTO();
					voo.setOrigem(localidade);
				}
				
				auxiliar = rs.getString(7);
				
				if(auxiliar != null)
				{
					strLocalidade = rs.getString(7).split("-");//Pega o Destino, �ndice 7 do retorno da query
					LocalidadeTO destino = new LocalidadeTO(Integer.parseInt(strLocalidade[0]), strLocalidade[2], strLocalidade[1], "D");
					voo.setDestino(destino);
					//8 - DATA DESTINO
				}
				else {
					LocalidadeTO localidade = new LocalidadeTO();
					voo.setDestino(localidade);
				}
				
				auxiliar = rs.getString(9);
				
				if(auxiliar != null)
				{
					strLocalidade = rs.getString(9).split("-");//Pega escala, �ndice 9 do retorno da query
					LocalidadeTO escala = new LocalidadeTO(Integer.parseInt(strLocalidade[0]), strLocalidade[2], strLocalidade[1], "D");
					voo.setEscala(escala);
					voo.setDateHora(rs.getString(6));
				}
				else {
					LocalidadeTO localidade = new LocalidadeTO();
					voo.setEscala(localidade);
				}
				
				resultado.add(voo);
			}
			
		} catch (Exception e) {
			// tenta dar rollback na instru��o realizada
			e.printStackTrace();
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
	
	public List<VooTO> consultar() throws Exception {
		
		String consulta = "SELECT * FROM VOO_GERAL";
		
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
				voo.setValor((rs.getDouble(2)));
				//3 - CODIGO DA AERONAVE
				//4 - COD DA SITUACAO
				String[] strLocalidade = rs.getString(5).split("-");//Pega a origem, �ndice 5 do retorno da query
				LocalidadeTO origem = new LocalidadeTO(Integer.parseInt(strLocalidade[0]), strLocalidade[2], strLocalidade[1], "O");
				voo.setOrigem(origem);
				//6 - DATA DA ORIGEM
				
				strLocalidade = rs.getString(7).split("-");//Pega o Destino, �ndice 7 do retorno da query
				LocalidadeTO destino = new LocalidadeTO(Integer.parseInt(strLocalidade[0]), strLocalidade[2], strLocalidade[1], "D");
				voo.setDestino(destino);
				//8 - DATA DESTINO
				
				strLocalidade = rs.getString(9).split("-");//Pega escala, �ndice 9 do retorno da query
				LocalidadeTO escala = new LocalidadeTO(Integer.parseInt(strLocalidade[0]), strLocalidade[2], strLocalidade[1], "D");
				voo.setEscala(escala);
				voo.setDateHora(rs.getString(6));
				resultado.add(voo);
			}
			
		} catch (Exception e) {
			// tenta dar rollback na instru��o realizada
			try {
				conexao.rollback();
				throw e;
			} catch (SQLException sqlEx) {
				sqlEx.getStackTrace();
			}
			throw e;
		} finally {
			if (stm != null) {
				try {
					conexao.close();
				} catch (SQLException sqlEx) {
					throw sqlEx;
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
			// tenta dar rollback na instru��o realizada
			try {
				conexao.rollback();
			} catch (SQLException sqlEx) {
				JOptionPane.showMessageDialog(
						null,
						"N�o foi poss�vel realizar o rollback \n\n"
								+ sqlEx.getStackTrace());
			}

			e.printStackTrace();
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

	// Classes de dom�nios

	

	

	

	

	public int alterarVoo(VooTO novo, VooTO antigo) throws SQLException {
		int retorno = -1;
		String update = "UPDATE LOCALIDADE_VOO "
						+ " SET COD_LOC = ?, DATA = ? "
						+ " WHERE COD_VOO = ? "
						+ " AND COD_LOC = ? "
						+ " AND TIPO = ?" ;
		
		String update2 = "UPDATE VOO "
						+ " SET VALOR_VOO = ?, COD_AERO_VOO = ?, COD_SIT_VOO = ? "
						+ " WHERE COD_VOO = ? ";
		
		PreparedStatement stm = null;
		conexao = null;

		try {
			
			conexao = obtemConexao();
			conexao.setAutoCommit(false);
			
			
			
			stm = prepararComando(update);
			//Prepara os dados da Origem
			stm.setInt(1, novo.getOrigemObj().getCodigo());
			stm.setString(2, novo.getDateHora());
			stm.setInt(3, novo.getCodigoVoo());
			stm.setInt(4, antigo.getOrigemObj().getCodigo());
			stm.setString(5,antigo.getOrigemObj().getTipo());
			
			stm.addBatch();
			
			//Prepara os dados do Destino
			stm.setInt(1, novo.getDestinoObj().getCodigo());
			stm.setString(2, novo.getDateHora());
			stm.setInt(3, novo.getCodigoVoo());
			stm.setInt(4, antigo.getDestinoObj().getCodigo());
			stm.setString(5,antigo.getDestinoObj().getTipo());
			
			stm.addBatch();
			
			//Prepara os dados do Escala
			stm.setInt(1, novo.getEscalaObj().getCodigo());
			stm.setString(2, novo.getDateHora());
			stm.setInt(3, novo.getCodigoVoo());
			stm.setInt(4, antigo.getEscalaObj().getCodigo());
			stm.setString(5,antigo.getEscalaObj().getTipo());
			
			stm.addBatch();
			
			System.out.println(stm);
			stm.executeBatch();
			
			stm = prepararComando(update2);
			
			stm.setDouble(1,novo.getValor());
			stm.setInt(2,novo.getAeronave());
			stm.setInt(3, novo.getSituacao());
			stm.setInt(4, novo.getCodigoVoo());
			
			stm.execute();
			
			conexao.commit();
			System.out.println("Comitou e executou");
			retorno = novo.getCodigoVoo();
			
		} catch (Exception e) {
			
			try {
				conexao.rollback();
			} catch (SQLException sqlEx) {
				throw sqlEx;
			}
			
			throw e;
		} finally {
			if (stm != null) {
				try {
					conexao.close();
				} catch (SQLException sqlEx) {
					throw sqlEx;
				}
			}

		}
		
		return retorno;
	}

	
	public void excluirVoo(VooTO voo) throws SQLException {
		String deleteLocalidaeVoo = "DELETE FROM LOCALIDADE_VOO WHERE COD_VOO = ?";
		String deleteVoo = "DELETE FROM VOO WHERE COD_VOO = ?";

		PreparedStatement stm = null;
		conexao = null;
		
		try {
			conexao = obtemConexao();
			conexao.setAutoCommit(false);
			
			stm = prepararComando(deleteLocalidaeVoo);
			stm.setInt(1, voo.getCodigoVoo());
			stm.execute();
			
			stm = prepararComando(deleteVoo);
			stm.setInt(1, voo.getCodigoVoo());
			stm.execute();
			
			conexao.commit();
		} catch (Exception e) {
			try {
				conexao.rollback();
				throw e;
			} catch (SQLException sqlEx) {
				throw sqlEx;
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
	public VooTO consultarVoo(int codigo) {
		String consulta = "SELECT * FROM VOO_GERAL WHERE COD_VOO = ?";
		
		conexao = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		VooTO resultado = new VooTO();
		
		try {
			conexao = obtemConexao();
			stm = prepararComando(consulta);

			stm.setInt(1, codigo);

			rs = stm.executeQuery();
			
			
			while (rs.next()) {
				VooTO voo = new VooTO();
				voo.setCodigoVoo((rs.getInt(1)));
				voo.setValor((rs.getDouble(2)));
				//3 - CODIGO DA AERONAVE
				//4 - COD DA SITUACAO
				String auxiliar = rs.getString(5);
				
				String[] strLocalidade;
				if(auxiliar != null)
				{
					strLocalidade = rs.getString(5).split("-");//Pega a origem, �ndice 5 do retorno da query
					LocalidadeTO origem = new LocalidadeTO(Integer.parseInt(strLocalidade[0]), strLocalidade[2], strLocalidade[1], "O");
					voo.setOrigem(origem);
					//6 - DATA DA ORIGEM	
				}
				else {
					LocalidadeTO localidade = new LocalidadeTO();
					voo.setOrigem(localidade);
				}
				
				voo.getOrigem().toString();
				
				auxiliar = rs.getString(7);
				
				if(auxiliar != null)
				{
					strLocalidade = rs.getString(7).split("-");//Pega o Destino, �ndice 7 do retorno da query
					LocalidadeTO destino = new LocalidadeTO(Integer.parseInt(strLocalidade[0]), strLocalidade[2], strLocalidade[1], "D");
					voo.setDestino(destino);
					//8 - DATA DESTINO
				}
				else {
					LocalidadeTO localidade = new LocalidadeTO();
					voo.setDestino(localidade);
				}
				
				auxiliar = rs.getString(9);
				
				if(auxiliar != null)
				{
					strLocalidade = rs.getString(9).split("-");//Pega escala, �ndice 9 do retorno da query
					LocalidadeTO escala = new LocalidadeTO(Integer.parseInt(strLocalidade[0]), strLocalidade[2], strLocalidade[1], "D");
					voo.setEscala(escala);
					voo.setDateHora(rs.getString(6));
				}
				else {
					LocalidadeTO localidade = new LocalidadeTO();
					voo.setEscala(localidade);
				}
				
				resultado = voo;
				
			}
			
		} catch (Exception e) {
			// tenta dar rollback na instru��o realizada
			e.printStackTrace();
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
