package Model;

public class LocalidadeTO {
	
	private int codigo;
	private String nome;
	private String uf;
	private String tipo;
	
	public LocalidadeTO() {
		
	}
	
	public LocalidadeTO(int codigo, String nome, String uf,String tipo) {
		setCodigo(codigo);
		setNome(nome);
		setUf(uf);
		setTipo(tipo);
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getUf() {
		return uf;
	}
	
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome= nome;
	}
	
	public String toString() {
		return "" + getCodigo() + " - " + getUf() + " - " + getNome();
	}
}
