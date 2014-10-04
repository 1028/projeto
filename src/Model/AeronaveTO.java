package Model;
public class AeronaveTO {

	private int codigoAeronave, qtdAssentos;
	private String nome, tipoAeronave;
	
	public int getCodigoAeronave() {
		return codigoAeronave;
	}

	public void setCodigoAeronave(int codigoAeronave) {
		this.codigoAeronave = codigoAeronave;
	}

	public int getQtdAssentos() {
		return qtdAssentos;
	}

	public void setQtdAssentos(int qtdAssentos) {
		this.qtdAssentos = qtdAssentos;
	}

	public String getTipoAeronave() {
		return tipoAeronave;
	}

	public void setTipoAeronave(String tipoAeronave) {
		this.tipoAeronave = tipoAeronave;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return "" + getCodigoAeronave() + " - " + getTipoAeronave() + " - " + getNome() + " - " + getQtdAssentos();
	}
}
