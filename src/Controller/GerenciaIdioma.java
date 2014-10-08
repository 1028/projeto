package Controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class GerenciaIdioma
 */
@WebServlet("/GerenciaIdioma")
public class GerenciaIdioma extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerenciaIdioma() {
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

	
	protected void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String escolha = request.getParameter("idioma");
		ResourceBundle bundle;
		if (escolha.equals("es")) {
			bundle = ResourceBundle.getBundle("Idiomas/idioma", new Locale("es", "ES"));
		} else if (escolha.equals("en")) {
			bundle = ResourceBundle.getBundle("Idiomas/idioma", Locale.US);
		} else {
			bundle = ResourceBundle.getBundle("Idiomas/idioma", new Locale("pt", "BR"));
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("idioma", bundle);
		session.setAttribute("msg", "mensagem.branco");
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
}
