package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.VooTO;

/**
 * Servlet implementation class IncluiVoo
 */
@WebServlet("/IncluiVoo")
public class IncluiVoo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncluiVoo() {
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
		VooTO voo = new VooTO();
		
		voo.setValor(Double.parseDouble(request.getParameter("fvalor")));
		voo.setDateHora(request.getParameter("fdata"));
		voo.setOrigem(request.getParameter("forigem"));
		voo.setDestino(request.getParameter("fdestino"));
		voo.setEscala(request.getParameter("fescala"));
		
		voo.setSituacao(Integer.parseInt(request.getParameter("fsituacao")));
	}
	
}
