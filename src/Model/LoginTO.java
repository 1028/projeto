package Model;

import Controller.BCrypt;

public class LoginTO {
	private String login, senha;
	private int tipoUsuario;
	
	public LoginTO(){
		
	}
	
	public LoginTO(String login, String senha, int tipoUsuario){
		setLogin(login);
		setSenhaCifrada(senha);
		setTipoUsuario(tipoUsuario);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setSenhaCifrada(String senha){
		this.senha = BCrypt.hashpw(senha, BCrypt.gensalt(12));
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
