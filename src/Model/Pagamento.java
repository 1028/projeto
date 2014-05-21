package Model;

import java.sql.SQLException;

public class Pagamento {
	private String nomeTitular, cpf, dataPagamento, formaPagamento;
	private BancoDeDados bd;

	public Pagamento() {
		bd = null;
	}

	public Pagamento(String sNomeTitular, String sCpf, String sData,
			String sFormaPagamento) {
		setNomeTitular(sNomeTitular);
		setCpf(sCpf);
		setDataPagamento(sData);
		setFormaPagamento(sFormaPagamento);
	}

	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public void pagar() throws SQLException {
		bd = new BancoDeDados();
		System.out.println(getNomeTitular());
		bd.cadastrarPagamento(getNomeTitular(), getCpf(), getDataPagamento(),
				getFormaPagamento());
	}

	public void consultarPagamneto() {

	}
}
