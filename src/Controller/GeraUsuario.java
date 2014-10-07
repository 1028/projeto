package Controller;

import Model.Login;
import Model.LoginTO;

public class GeraUsuario {

	public GeraUsuario() {

	}

	public void GeradorUsuarios() {
		LoginTO[] usuario = new LoginTO[2];
		usuario[0] = new LoginTO("Allan", "123a", 0);
		usuario[1] = new LoginTO("Diego", "456d", 0);

		int i = 0;
		while(i < usuario.length) {
			Login login = new Login(usuario[i]);
			login.cadastrarUsuario();
			i++;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GeraUsuario geraUsuario = new GeraUsuario();
		geraUsuario.GeradorUsuarios();
	}

}
