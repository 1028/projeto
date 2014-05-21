package Model;

public class VooTO {
	private int codigoVoo, situacao;
	private String origem, destino, escala;
	private String dateHora;
	private double valor;
		
	public int getCodigoVoo() {
		return codigoVoo;
	}
	
	public void setCodigoVoo(int codigoVoo) {
		this.codigoVoo = codigoVoo;
	}
	
	public String getOrigem() {
		return origem;
	}
	
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public String getEscala() {
		return escala;
	}
	
	public void setEscala(String escala) {
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
}
