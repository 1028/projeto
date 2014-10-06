package Model;

import Model.dao.DaoFactory;
import Model.dao.LoginDao;

public class Login {
	LoginTO dadosLogin = null;

	public Login(LoginTO dadosLogin) {
		this.dadosLogin = dadosLogin;
	}
	
	public void cadastrarUsuario(){
		DaoFactory factory = DaoFactory.getInstance();
		LoginDao dao = factory.getLoginDao();
		dao.cadastrarUsuario(dadosLogin);
	}
	
	public LoginTO efetuarLogin(){
		DaoFactory factory = DaoFactory.getInstance();
		LoginDao dao = factory.getLoginDao();
		return dao.efetuarLogin(dadosLogin);		
	}
}
