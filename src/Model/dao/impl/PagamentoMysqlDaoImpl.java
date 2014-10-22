package Model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.PagamentoTO;

public class PagamentoMysqlDaoImpl {
	private Connection conexao;

	private Connection obtemConexao() throws SQLException {
		BancoDeDados bd = new BancoDeDados();
		return bd.obtemConexao();
	}

	public PreparedStatement prepararComando(String comando)
			throws SQLException {
		return (PreparedStatement) conexao.prepareStatement(comando);
	}
	
	public void cadastrarPagamento(PagamentoTO pagamento) {
		String insercao = "INSERT INTO PAGAMENTO VALUES (NULL, ?, ?, ?, ?)";
		conexao = null;
		PreparedStatement stm = null;
		
		try {
			conexao = obtemConexao();
			stm = prepararComando(insercao);
			stm.setString(1, pagamento.getNomeTitular());
			stm.setString(2, pagamento.getCpf());
			stm.setString(3, pagamento.getDataPagamento());
			stm.setString(4, pagamento.getFormaPagamento());
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
	
	public void consultarPagamento(PagamentoTO pagamento) {
		String insercao = "INSERT INTO PAGAMENTO VALUES (NULL, ?, ?, ?, ?)";
		conexao = null;
		PreparedStatement stm = null;
		
		try {
			conexao = obtemConexao();
			stm = prepararComando(insercao);
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
}
