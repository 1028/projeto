package Model;

import java.sql.SQLException;

public class Cheque extends Pagamento {
	private int codigo, codigoPagamento;
	private String banco, agencia, conta;
	private BancoDeDados bd;

	public Cheque() {

	}

	public Cheque(String sNomeTitular, String sCpf, String sData,
			String sFormaPagamento, String sBanco, String sAgencia,
			String sConta) {
		setNomeTitular(sNomeTitular);
		setCpf(sCpf);
		setDataPagamento(sData);
		setFormaPagamento(sFormaPagamento);
		setBanco(sBanco);
		setAgencia(sAgencia);
		setConta(sConta);
		setCodigoPagamento(1);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigoPagamento() {
		return codigoPagamento;
	}

	public void setCodigoPagamento(int codigoPagamento) {
		this.codigoPagamento = codigoPagamento;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public void cadastrarCheque() throws SQLException {
		bd = new BancoDeDados();

		// bd.cadastrarPagamento(getNomeTitular(), getCpf(), getDataPagamento(),
		// getFormaPagamento());

		System.out.println(getNomeTitular());
		bd.cadastrarCheque(getBanco(), getAgencia(), getConta(), 1);// getCodigoPagamento());
	}

	public void consultarCheque() {

	}
}
