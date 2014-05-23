package Model;

import java.sql.SQLException;
import java.util.ArrayList;


public class FormaTratamento {
	private BancoDeDados bd;

	public FormaTratamento() {
		
	}
	
	public String toString() {
		return String.format(" %d - %s", getCodigo(), getNome());
	}
	

	
	public void consultar() throws SQLException {
		bd = new BancoDeDados();
		
		bd.consultarSituacao(getCodigo());
	}
	
	public ArrayList consultarTodos() throws SQLException {
		bd = new BancoDeDados();
		bd.consultarTratamento();
		
		return bd.getRetornoQuery();
	}
}
