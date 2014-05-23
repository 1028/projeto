package Model;

import java.sql.SQLException;
import java.util.ArrayList;

// mudar para class TipoPassageiro?
public class Perfil {
	

	
	public Perfil(String nome, int codigo) {
		setNome(nome);
		setCodigo(codigo);
	}
	
	public Perfil() {
		
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
		bd.consultarPerfil();
		
		return bd.getRetornoQuery();
	}
}
