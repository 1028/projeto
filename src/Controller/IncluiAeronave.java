package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Aeronave;
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

	protected void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String operacao = (String) request.getParameter("btn");
		
		if(operacao.equals("Cadastrar")){
		AeronaveTO aeronave = new AeronaveTO();
		//aeronave.setCodigoAeronave(request.getParameter(""));
		aeronave.setNome(request.getParameter("fnome"));
		aeronave.setQtdAssentos(Integer.parseInt(request.getParameter("fqntassento")));
		aeronave.setTipoAeronave(Integer.parseInt(request.getParameter("ftipoaeronave")));
		
		Aeronave a = new Aeronave(aeronave);
		a.incluirAeronave(aeronave);
		}
		else if (operacao.equals("Consultar")){
			AeronaveTO aeronave = new AeronaveTO();
			
			aeronave.setCodigoAeronave(Integer.parseInt(request.getParameter("fcodigo")));
			
			Aeronave a = new Aeronave(aeronave);
			List<AeronaveTO> consulta = new ArrayList<AeronaveTO>();
			try {
				consulta = a.consultarAeronave();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("consultou", "ok");
			request.setAttribute("con", consulta);
			request.getRequestDispatcher("cadastroAeronave.jsp").forward(request, response);
		}
		else if(operacao.equals("Alterar")){
			AeronaveTO aeronave = new  AeronaveTO();
			aeronave.setCodigoAeronave(Integer.parseInt(request.getParameter("fcodigo")));
			aeronave.setNome(request.getParameter("fnome"));
			aeronave.setQtdAssentos(Integer.parseInt(request.getParameter("fqntassento")));
			aeronave.setTipoAeronave(Integer.parseInt(request.getParameter("ftipoaeronave")));
			
			Aeronave a = new Aeronave(aeronave);
			try {
				a.alterarAeronave();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			AeronaveTO aeronave = new  AeronaveTO();
			System.out.print(Integer.parseInt(request.getParameter("fcodigo")) + "");
			aeronave.setCodigoAeronave(Integer.parseInt(request.getParameter("fcodigo")));
			

			Aeronave a = new Aeronave(aeronave);
			try {
				a.excluirAeronave();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
