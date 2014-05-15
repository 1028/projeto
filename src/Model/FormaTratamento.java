package Model;

import java.sql.SQLException;
import java.util.ArrayList;


public class FormaTratamento {
	private BancoDeDados bd;
	private String nome;
	private int codigo;
	
	public FormaTratamento(String nome, int codigo) {
		setNome(nome);
		setCodigo(codigo);
	}
	
	public FormaTratamento() {
		
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
		bd.consultarTratamento();
		
		return bd.getRetornoQuery();
	}
}
