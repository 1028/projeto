package Model;

import java.sql.Date;
import java.sql.SQLException;

public class Passagem {

	private BancoDeDados bd;

	public Passagem() {
		bd = null;
	}

	public Passagem(int iNumeroPassagem) {
		setNumeroPassagem(iNumeroPassagem);
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
