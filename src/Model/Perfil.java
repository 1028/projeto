package Model;

import java.sql.SQLException;
import java.util.ArrayList;


public class Perfil {
	private BancoDeDados bd;
	private String nome;
	private int codigo;
	
	public Perfil(String nome, int codigo) {
		setNome(nome);
		setCodigo(codigo);
	}
	
	public Perfil() {
		
	}
	
	public String toString() {
		return String.format(" %d - %s", getCodigo(), getNome());
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getCodigo() {
		return codigo;
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
