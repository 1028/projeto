package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Login;
import Model.LoginTO;

/**
 * Servlet implementation class GerenciaLogin
 */
@WebServlet("/GerenciaLogin")
public class GerenciaLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerenciaLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		executa(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		executa(request, response);
	}
	
	public void executa(HttpServletRequest request, HttpServletResponse response){
		LoginTO login = new LoginTO();
		
		login.setLogin(request.getParameter("flogin"));
		login.setSenha(request.getParameter("fsenha"));
		
		Login l = new Login(login);
		l.efetuarLogin();
	}

}
