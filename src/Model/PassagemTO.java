package Model;

import java.sql.Date;

public class PassagemTO {
	private int iNumeroPassagem;
	private Date data;
	private String numeroAssento;
	
	public int getNumeroPassagem() {
		return iNumeroPassagem;
	}

	public void setNumeroPassagem(int iNumeroPassagem) {
		this.iNumeroPassagem = iNumeroPassagem;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNumeroAssento() {
		return numeroAssento;
	}

	public void setNumeroAssento(String numeroAssento) {
		this.numeroAssento = numeroAssento;
	}
}
