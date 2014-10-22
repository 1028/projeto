package Model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.ChequeTO;
import Model.dao.ChequeDao;

public class ChequeMysqlDaoImpl extends PagamentoMysqlDaoImpl implements ChequeDao{
	private Connection conexao;

	private Connection obtemConexao() throws SQLException {
		BancoDeDados bd = new BancoDeDados();
		return bd.obtemConexao();
	}

	public PreparedStatement prepararComando(String comando)
			throws SQLException {
		return (PreparedStatement) conexao.prepareStatement(comando);
	}
	
	public void cadastrarCheque(ChequeTO cheque) {
		String insercao = "INSERT INTO CHEQUE VALUES (NULL, ?, ?, ?, ?)";
		conexao = null;
		PreparedStatement stm = null;

		try {
			conexao = obtemConexao();
			stm = prepararComando(insercao);
			stm.setString(1, cheque.getBanco());
			stm.setString(2, cheque.getAgencia());
			stm.setString(3, cheque.getConta());
			stm.setInt(4, cheque.getCodigoPagamento());
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

	public void consultarCheque(ChequeTO cheque) {
		String consulta = "";
		conexao = null;
		PreparedStatement stm = null;

		try {
			conexao = obtemConexao();
			stm = prepararComando(consulta);
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
