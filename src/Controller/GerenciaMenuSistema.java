package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GerenciaMenuSistema
 */
@WebServlet("/GerenciaMenuSistema")
public class GerenciaMenuSistema extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerenciaMenuSistema() {
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

	protected void executa(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String parametro = request.getParameter("op");  
		if(parametro.equals("1")){
			request.setAttribute("msg", "mensagem.sobre");
			request.getRequestDispatcher("principal.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("idioma.jsp").forward(request, response);
		}
	}
}
