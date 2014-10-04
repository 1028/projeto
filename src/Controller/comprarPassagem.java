package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class comprarPassagem
 */
@WebServlet(description = "Servlet da rotina de Compra de Passagem", urlPatterns = { "/comprarPassagem" })
public class comprarPassagem extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int estado;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public comprarPassagem() {
        super();
        estado = 0;
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		comprarPassagem(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		comprarPassagem(request,response);
	}
	
	//Chama a consulta de Voo
	protected void comprarPassagem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(estado == 0) {
			response.sendRedirect("consultarVoo.jsp");
		}
	}
	
}
