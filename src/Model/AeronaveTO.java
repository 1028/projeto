package Model;
public class AeronaveTO {

	private int codigoAeronave, tipoAeronave, qtdAssentos;
	private String nome;
	
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

	public int getTipoAeronave() {
		return tipoAeronave;
	}

	public void setTipoAeronave(int tipoAeronave) {
		this.tipoAeronave = tipoAeronave;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
