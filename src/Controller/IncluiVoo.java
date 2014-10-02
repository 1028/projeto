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
import javax.websocket.Session;

import Model.Aeronave;
import Model.AeronaveTO;
import Model.Localidade;
import Model.LocalidadeTO;
import Model.Situacao;
import Model.SituacaoTO;
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
		if(Boolean.parseBoolean(request.getAttribute("carrega").toString())) {
			try {
				carregaComboBox(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			verificaOp(request,response);
		}
	}

	private void carregaComboBox(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
		//Primeiro comboBox a ser preenchido é o de Localidade
		LocalidadeTO locaTO = new LocalidadeTO();
		Localidade local = new Localidade(locaTO);
		
		List<LocalidadeTO> locais = local.consultar();
		
		//Carrega ComboBOX da Aeronave
		
		AeronaveTO aeronaveTO = new AeronaveTO();
		Aeronave aeronave = new Aeronave(aeronaveTO);
		ArrayList<AeronaveTO> aeronaves = aeronave.consultar();
		
		//Carrega ComboBox de Situação
		
		SituacaoTO situacaoTO = new SituacaoTO();
		Situacao situacao = new Situacao(situacaoTO);
		ArrayList<SituacaoTO> situacoes = situacao.consultarSituacao();
		
		Boolean carrega = false;
		request.setAttribute("carrega", carrega);
		
		//Adiciona as listas para preencher os ComboBox
		request.setAttribute("locais", locais);
		request.setAttribute("aeronaves", aeronaves);
		request.setAttribute("situacoes", situacoes);
		
		//Retorna para a VIEW
		request.getRequestDispatcher("cadastroVoo.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(Boolean.parseBoolean(request.getAttribute("carrega").toString())) {
			
		}
		else {
			verificaOp(request,response);
		}
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
		
		
		switch (teste) {
		case "listar" : 
			consultar(request, response);
			break;
		case "cadastro" :
			cadastrar(request, response);
			break;
		}
		/*
		if(teste.equals("listar")) {
			consultar(request, response) ;
		}
		*/
	}
	
	public void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VooTO vooTO = new VooTO();
		Voo voo = new Voo(vooTO);
		
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
		int pag;
		try {
			pag = Integer.parseInt(request.getParameter("pagAt"));
			System.out.println("o valor de pag é " + pag);
		}
		catch(Exception e) {
			System.out.println("Pag não chegou aqui rapa!");
		}
		
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
