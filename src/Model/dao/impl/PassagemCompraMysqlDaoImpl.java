package Model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.PassagemCompraTO;
import Model.dao.PassagemCompraDao;

public class PassagemCompraMysqlDaoImpl extends PassagemMysqlDaoImpl implements PassagemCompraDao{
	private Connection conexao;

	private Connection obtemConexao() throws SQLException {
		BancoDeDados bd = new BancoDeDados();
		return bd.obtemConexao();
	}

	public PreparedStatement prepararComando(String comando)
			throws SQLException {
		return (PreparedStatement) conexao.prepareStatement(comando);
	}
	
	public void cadastrarPassagem(PassagemCompraTO passagemCompra) {
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

	public void cancelarPassagem(PassagemCompraTO passagemCompra) {
		String update = String.format("", passagemCompra.getNumeroPassagem());
		conexao = null;
		PreparedStatement stm = null;

		try {
			conexao = obtemConexao();
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
