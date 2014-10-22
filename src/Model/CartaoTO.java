package Model;

public class CartaoTO extends PagamentoTO {

	private int iNumCartao, iDataValidade, iCodigoSeguranca, iTipoCartao;

	public int getNumCartao() {
		return iNumCartao;
	}

	public void setNumCartao(int iNumCartao) {
		this.iNumCartao = iNumCartao;
	}

	public int getDataValidade() {
		return iDataValidade;
	}

	public void setDataValidade(int iDataValidade) {
		this.iDataValidade = iDataValidade;
	}

	public int getCodigoSeguranca() {
		return iCodigoSeguranca;
	}

	public void setCodigoSeguranca(int iCodigoSeguranca) {
		this.iCodigoSeguranca = iCodigoSeguranca;
	}

	public int getTipoCartao() {
		return iTipoCartao;
	}

	public void setTipocartao(int iTipoCartao) {
		this.iTipoCartao = iTipoCartao;
	}

}
