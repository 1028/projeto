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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		executa(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		executa(request, response);
	}

	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoginTO login = new LoginTO();

		login.setLogin(request.getParameter("flogin"));
		login.setSenha(request.getParameter("fsenha"));

		Validacao oValida = new Validacao();
		if (!(oValida.camposEmBranco(login.getLogin(), login.getSenha()))) {
			CryptoDummy oCriptografa = new CryptoDummy();
			String senhaCriptografada = "";

			try {
				senhaCriptografada = oCriptografa.cifraSenha(login.getSenha());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			login.setSenha(senhaCriptografada);
			
			Login l = new Login(login);
			LoginTO resultado = l.efetuarLogin();
			
			System.out.println(senhaCriptografada + "  senha dig. " + "senha objeto TO" + login.getSenha());
			System.out.println("senha --" + resultado.getSenha() + "\nlogin --" + resultado.getLogin() + "\ntipo usu. --" + resultado.getTipoUsuario());

			if (resultado != null) {

				request.setAttribute("msg", "FrmLogin.mensagem.validado");
				request.getRequestDispatcher("principal.jsp").forward(request,
						response);
			} else {
				request.setAttribute("msg", "FrmLogin.mensagem.invalidos");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("msg", "mensage.campos.branco");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		}
	}
}
