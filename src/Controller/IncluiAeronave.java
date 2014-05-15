package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.AeronaveTO;

/**
 * Servlet implementation class IncluiAeronave
 */
@WebServlet("/IncluiAeronave")
public class IncluiAeronave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncluiAeronave() {
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

	protected void executa(HttpServletRequest request, HttpServletResponse response){
		AeronaveTO aeronave = new AeronaveTO();
		//aeronave.setCodigoAeronave(request.getParameter(""));
		aeronave.setNome(request.getParameter("fnome"));
		aeronave.setQtdAssentos(Integer.parseInt(request.getParameter("fqntassento")));
		aeronave.setTipoAeronave(Integer.parseInt(request.getParameter("ftipoaeronave")));
	}
	
}
