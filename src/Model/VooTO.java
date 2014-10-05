package Model;

public class VooTO {
	private int codigoVoo, situacao;
	//private String origem, destino, escala;
	private String dateHora;
	private double valor;
	private int aeronave;
	private LocalidadeTO origem , destino, escala;
	
	public int getCodigoVoo() {
		return codigoVoo;
	}
	
	public void setCodigoVoo(int codigoVoo) {
		this.codigoVoo = codigoVoo;
	}
	
	public String getOrigem() {
		return origem.toString();
	}
	
	public LocalidadeTO getOrigemObj() {
		return origem;
	}
	
	public void setOrigem(LocalidadeTO origem) {
		this.origem = origem;
	}
	
	public String getDestino() {
		return destino.toString();
	}
	
	public LocalidadeTO getDestinoObj() {
		return destino;
	}
	
	public void setDestino(LocalidadeTO destino) {
		this.destino = destino;
	}
	
	public String getEscala() {
		return escala.toString();
	}
	
	public LocalidadeTO getEscalaObj() {
		return escala;
	}
	
	public void setEscala(LocalidadeTO escala) {
		this.escala = escala;
	}
	
	public String getDateHora() {
		return dateHora;
	}
	
	public void setDateHora(String dateHora) {
		this.dateHora = dateHora;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public int getSituacao() {
		return situacao;
	}
	
	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}
	
	public void setAeronave(int codigo) {
		this.aeronave = codigo;
	}
	
	public int getAeronave() {
		return this.aeronave;
	}
	
}
