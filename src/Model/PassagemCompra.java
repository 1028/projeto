package Model;

import java.sql.SQLException;

public class PassagemCompra extends Passagem {
	private int valor, percentualTaxaEmbarque;
	private String dataCompra;
	private BancoDeDados bd;

	public PassagemCompra(int numeroPassagem, String numeroAssento, int valor,
			int percentualTaxaEmbarque, String dataCompra) {
		bd = null;
		setNumeroPassagem(numeroPassagem);
		setNumeroAssento(numeroAssento);
		setValor(valor);
		setPercentualTaxaEmbarque(percentualTaxaEmbarque);
		setDataCompra(dataCompra);
	}

	public PassagemCompra(int iNumPassagem) {
		setNumeroPassagem(iNumPassagem);
	}

	public PassagemCompra() {
		bd = null;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getPercentualTaxaEmbarque() {
		return percentualTaxaEmbarque;
	}

	public void setPercentualTaxaEmbarque(int percentualTaxaEmbarque) {
		this.percentualTaxaEmbarque = percentualTaxaEmbarque;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public void cancelarPassagem() throws SQLException {
		bd = new BancoDeDados();

		bd.cancelarPassagem(getNumeroPassagem());
	}

	public void comprarPassagem() {

	}

	public void calcularPassagem() {

	}

	public void emitirPassagem() {

	}
}
