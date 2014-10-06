package Model.dao;

import Model.LoginTO;

public interface LoginDao {
	public void cadastrarUsuario(LoginTO login);
	
	public LoginTO efetuarLogin(LoginTO login);
}
