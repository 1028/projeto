package Model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.LocalidadeTO;
import Model.SituacaoTO;
import Model.dao.SituacaoDao;

public class SituacaoMysqlDaoImpl implements SituacaoDao{

	private Connection conexao;
	
	
	public ArrayList<SituacaoTO> consultarSituacao() {
		
		String consulta = "SELECT * FROM SITUACAO";
		
		conexao = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<SituacaoTO> resultado = new ArrayList<SituacaoTO>();
		
		try {
			conexao = obtemConexao();
			stm = prepararComando(consulta);
			rs = stm.executeQuery();
			
			while (rs.next()) {
				SituacaoTO situacao = new SituacaoTO();
				situacao.setCodigo((rs.getInt(1)));
				situacao.setNome((rs.getString(2)));
				
				resultado.add(situacao);
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
	


	
	
}
