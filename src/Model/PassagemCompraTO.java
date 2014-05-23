package Model;

public class PassagemCompraTO extends PassagemTO{
	private int valor, percentualTaxaEmbarque;
	private String dataCompra;
	
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

}
