package Model;

import java.sql.Date;
import java.sql.SQLException;

public class Passagem {
	private int iNumeroPassagem;
	private Date data;
	private String numeroAssento;
	private BancoDeDados bd;

	public Passagem() {
		bd = null;
	}

	public Passagem(int iNumeroPassagem) {
		setNumeroPassagem(iNumeroPassagem);
	}

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
	
	public void efetuarCheckin(){
		
	}
	
	public void transferir() throws SQLException {
		bd = new BancoDeDados();

		bd.tranferirPassagem(getNumeroPassagem());
	}
	
	public void consultarPassagem(){
		
	}
	
	public int emitirCartaoEmbarque(){
		
		return 1;
	}
}
