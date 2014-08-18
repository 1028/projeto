package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Voo;
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
		//executa(request, response);
		verificaOp(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//executa(request, response);
		verificaOp(request,response);
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
	
	public void verificaOp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String teste = request.getParameter("op").toString();
		teste = (teste != null ? teste : "");
		
		if(teste.equals("listar")) {
			consultar(request, response) ;
		}
	}
	
	public void consultar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VooTO vooTO = new VooTO();
		Voo voo = new Voo(vooTO);
		List<VooTO> lista = new ArrayList<VooTO>();
		
		//variável pegará o total de voos registros na base
		int total = 0;
		//calculará a quantidade de páginas geral.
		int totPag; 
		//Quantidade de registros por página
		int qtdReg = 2;
		try {
			lista = voo.consultar();
			total = voo.total();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Cálculo, arredondado para cima, de páginas necessárias para a quantidade de registros retornados.
		totPag = (total + (qtdReg - 1))/ qtdReg;
		
		
		request.setAttribute("total", totPag);
		request.setAttribute("ret", "listar");
		request.setAttribute("lst", lista);
		request.getRequestDispatcher("consultarVoo.jsp").forward(request, response);
	}
	
}
