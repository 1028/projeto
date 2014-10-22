package Model;

public class Cartao extends Pagamento{
	
	CartaoTO dadosCartao = null;
	public Cartao(PagamentoTO dadosPagamento, CartaoTO dadosCartao) {
		super(dadosPagamento);
		this.dadosCartao = dadosCartao;
	}

	public void validarCartao() {

	}
}
