package Model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		String sInsercao = "INSERT INTO PASSAGEIRO VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
		conexao = null;

		PreparedStatement stm = null;

		try {
			conexao = obtemConexao();
			stm = prepararComando(sInsercao);
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
	
	public void alterarPassageiro(PassageiroTO passageiro){
		String sAlterar = "UPDATE PASSAGEIRO SET NOM_PAS = ?, SOBRENOME_PAS = ?, CEL_PAS = ?, DATA_NASC_PAS = ?, EMAIL_PAS = ?, COD_TRA_PAS = ?, COD_PER_PAS = ? WHERE COD_PASSAGEIRO = ?";
		
		conexao = null;
		PreparedStatement stm = null;
		
		try{
			conexao = obtemConexao();
			stm = prepararComando(sAlterar);
			stm.setString(1, passageiro.getNome());
			stm.setString(2, passageiro.getSobrenome());
			stm.setString(3, passageiro.getCelular());
			stm.setString(4, passageiro.getDataNascimento());
			stm.setString(5, passageiro.getEmail());
			stm.setInt(6, passageiro.getFormaTrata());
			stm.setInt(7, passageiro.getTipoPassageiro());
			stm.setInt(8, passageiro.getCodigo());
			stm.execute();
		} catch (Exception e) {
			try {
				conexao.rollback();
			} catch (SQLException sqlEX) {
				
			}
		} finally {
			if(stm != null) {
				try{
					conexao.close();
				} catch (SQLException sqlEx) {
					
				}
			}
		}
	}
	
	public void excluirPassageiro(PassageiroTO passageiro){
		String sExcluir = "DELETE FROM PASSAGEIRO WHERE COD_PASSAGEIRO = ?";
		
		conexao = null;
		PreparedStatement stm = null;
		
		try{
			conexao = obtemConexao();
			stm = prepararComando(sExcluir);
			stm.setInt(1, passageiro.getCodigo());
			stm.execute();
		} catch(Exception e){
			try{
				conexao.rollback();
			} catch(SQLException sqlEx){
				
			}
		} finally {
			try{
				conexao.close();
			} catch(SQLException sqlEx){
				
			}
		}
	}
	
	public List<PassageiroTO> consultarPassageiro(PassageiroTO passageiro){
		String sConsulta = "SELECT * FROM PASSAGEIRO WHERE NOM_PAS = ?";
		
		conexao = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<PassageiroTO> resultado = new ArrayList<PassageiroTO>();
		try{
			conexao = obtemConexao();
			stm = prepararComando(sConsulta);
			stm.setString(1, passageiro.getNome());
			rs = stm.executeQuery();
			
			if(rs.next()){
				PassageiroTO oPassageiro = new PassageiroTO();
				oPassageiro.setCodigo(rs.getInt(1));
				oPassageiro.setNome(rs.getString(2));
				oPassageiro.setSobrenome(rs.getString(3));
				oPassageiro.setCelular(rs.getString(4));
				oPassageiro.setDataNascimento(rs.getString(5));
				oPassageiro.setEmail(rs.getString(6));
				oPassageiro.setFormaTrata(rs.getInt(7));
				oPassageiro.setTipoPassageiro(rs.getInt(8));
				resultado.add(oPassageiro);
			}
		} catch(Exception e){
			try{
				conexao.rollback();
			} catch(SQLException sqlEx){
				
			}
		} finally {
			if(stm != null){
				try{
					conexao.close();
				} catch(SQLException sqlEx){
					
				}
			}
		}

		return resultado;		
	}
}
