package Model;

import java.sql.SQLException;
import java.util.ArrayList;


public class Localidade {
	
	private int codigo;
	private String nome;
	private String uf;
	private BancoDeDados bd;
	
	public Localidade() {
		
	}
	
	public Localidade(int codigo, String nome, String uf) {
		setCodigo(codigo);
		setNome(nome);
		setUf(uf);
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
	
	public ArrayList consultarTodos() throws SQLException {
		ArrayList consulta;
		
		bd = new BancoDeDados();
		bd.consultarLocalidade();		
		consulta = bd.getRetornoQuery();
		
		return consulta;
	}

}
