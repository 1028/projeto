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
import javax.swing.JOptionPane;

import Model.Aeronave;
import Model.AeronaveTO;
import Model.Localidade;
import Model.LocalidadeTO;
import Model.Situacao;
import Model.SituacaoTO;
import Model.Voo;
import Model.VooTO;


/**
 * Servlet implementation class GerenciaVoo
 */
@WebServlet("/GerenciaVoo")
public class GerenciaVoo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerenciaVoo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String carrega = (request.getAttribute("carrega") == null ? "" : request.getAttribute("carrega").toString());
		
		if(carrega.equals("true")) {
			try {
				carregaComboBox(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				verificaOp(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Boolean.parseBoolean(request.getAttribute("carrega").toString())) {
			try {
				carregaComboBox(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				verificaOp(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		
		request.setAttribute("carrega", false);
		
		//Adiciona as listas para preencher os ComboBox
		request.setAttribute("locais", locais);
		request.setAttribute("aeronaves", aeronaves);
		request.setAttribute("situacoes", situacoes);
		
		//Retorna para a VIEW
		request.getRequestDispatcher("gerenciaVoo.jsp").forward(request, response);
		
	}
	
	public void verificaOp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String teste = request.getParameter("op").toString();
		teste = (teste != null ? teste : "");
		
		
		switch (teste) {
		case "listar" :
			String codigo = (request.getParameter("fcodigo").equals("") ? "" : request.getParameter("fcodigo"));
			if(codigo.equals("")) {
				consultar(request, response);
			}
			else {
				//Consulta de voo específico, filtrando pelo código do voo atribuido através do parametro fcodigo
				consultarVoo(request, response);
			}
			break;
		case "cadastro" :
			cadastrar(request, response);
			break;
		case "alterar" :
			alterar(request,response);
			break;
		}
	}
	
	public void alterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		VooTO vooTO = new VooTO();
		vooTO.setValor(Double.parseDouble(request.getParameter("fvalor")));
		vooTO.setSituacao(Integer.parseInt(request.getParameter("fsituacao")));
		vooTO.setAeronave(Integer.parseInt(request.getParameter("faeronave")));
		//Transforma uma data no formato Brasileiro e retorna em formato SQL
		Formatador forma = new Formatador();
		vooTO.setDateHora(forma.dataNacional(request.getParameter("fdata")));
		
		//Atribuindo a Origem
		String[] localidade;
		LocalidadeTO loca = new LocalidadeTO();
		localidade = request.getParameter("forigem").toString().split(" - ");
		loca.setCodigo(Integer.parseInt(localidade[0]));
		loca.setUf(localidade[1]);
		loca.setNome(localidade[2]);
		loca.setTipo("O");
		vooTO.setOrigem(loca);
		
		//Atribuindo o Destino
		loca = new LocalidadeTO();
		localidade = request.getParameter("fdestino").toString().split(" - ");
		loca.setCodigo(Integer.parseInt(localidade[0]));
		loca.setUf(localidade[1]);
		loca.setNome(localidade[2]);
		loca.setTipo("D");
		vooTO.setDestino(loca);
		
		//Atribuindo a escala
		loca = new LocalidadeTO();
		localidade = request.getParameter("fescala").toString().split(" - ");
		loca.setCodigo(Integer.parseInt(localidade[0]));
		loca.setUf(localidade[1]);
		loca.setNome(localidade[2]);
		loca.setTipo("E");
		vooTO.setEscala(loca);
		
		Voo voo = new Voo(vooTO);
		
		try {
			int confirmacao = voo.alterarVoo();
			vooTO.setCodigoVoo(confirmacao);

			request.setAttribute("msg", "Voo " + confirmacao + " cadastrado com sucesso!");
			request.setAttribute("vooCadastrado", vooTO);
			request.getRequestDispatcher("gerenciaVoo.jsp").forward(request, response);
		}
		catch (Exception e) {
			request.setAttribute("msg", "Voo não foi cadastrado!");
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("gerenciaVoo.jsp").forward(request, response);
		}
		

	}
	
	public void executa(HttpServletRequest request, HttpServletResponse response){
		VooTO voo = new VooTO();
		
		voo.setValor(Double.parseDouble(request.getParameter("fvalor")));
		voo.setDateHora(request.getParameter("fdata"));
		//voo.setOrigem(request.getParameter("forigem"));
		//voo.setDestino(request.getParameter("fdestino"));
		//voo.setEscala(request.getParameter("fescala"));
		
		voo.setSituacao(Integer.parseInt(request.getParameter("fsituacao")));
	}
	
	public void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		VooTO vooTO = new VooTO();
		vooTO.setValor(Double.parseDouble(request.getParameter("fvalor")));
		vooTO.setSituacao(Integer.parseInt(request.getParameter("fsituacao")));
		vooTO.setAeronave(Integer.parseInt(request.getParameter("faeronave")));
		//Transforma uma data no formato Brasileiro e retorna em formato SQL
		Formatador forma = new Formatador();
		vooTO.setDateHora(forma.dataNacional(request.getParameter("fdata")));
		
		//Atribuindo a Origem
		String[] localidade;
		LocalidadeTO loca = new LocalidadeTO();
		localidade = request.getParameter("forigem").toString().split(" - ");
		loca.setCodigo(Integer.parseInt(localidade[0]));
		loca.setUf(localidade[1]);
		loca.setNome(localidade[2]);
		loca.setTipo("O");
		vooTO.setOrigem(loca);
		
		//Atribuindo o Destino
		loca = new LocalidadeTO();
		localidade = request.getParameter("fdestino").toString().split(" - ");
		loca.setCodigo(Integer.parseInt(localidade[0]));
		loca.setUf(localidade[1]);
		loca.setNome(localidade[2]);
		loca.setTipo("D");
		vooTO.setDestino(loca);
		
		//Atribuindo a escala
		loca = new LocalidadeTO();
		localidade = request.getParameter("fescala").toString().split(" - ");
		loca.setCodigo(Integer.parseInt(localidade[0]));
		loca.setUf(localidade[1]);
		loca.setNome(localidade[2]);
		loca.setTipo("E");
		vooTO.setEscala(loca);
		
		Voo voo = new Voo(vooTO);
		
		try {
			int confirmacao = voo.cadastrarVoo();
			vooTO.setCodigoVoo(confirmacao);

			request.setAttribute("msg", "Voo " + confirmacao + " cadastrado com sucesso!");
			request.setAttribute("vooCadastrado", vooTO);
			request.getRequestDispatcher("gerenciaVoo.jsp").forward(request, response);
		}
		catch (Exception e) {
			request.setAttribute("msg", "Voo não foi cadastrado!");
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("gerenciaVoo.jsp").forward(request, response);
		}
		
	}
	
	public void consultarVoo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int codigo = Integer.parseInt(request.getParameter("fcodigo"));
		Voo voo = new Voo();
		
		VooTO vooTo;
		List<VooTO> lista = new ArrayList<VooTO>();
		
		try {
			vooTo = voo.consultarVoo(codigo);
			lista.add(vooTo);
			request.setAttribute("lst", lista);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String msg = e.getMessage();
			System.out.println("Entrou no catch");
			request.setAttribute("msg", msg);
		}
		
		request.setAttribute("ret", "listar");
		
		System.out.println("Saiu do catch");
		
		request.getRequestDispatcher("gerenciaVoo.jsp").forward(request, response);
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
		int qtdReg = 10;
		int pageAt = 0;
		
		//pageAt = (request.getParameter("pageAt").equals("1") ? 0 : Integer.parseInt(request.getParameter("pageAt")));
		pageAt = Integer.parseInt(request.getParameter("pageAt"));
		
		int registroInicial = Math.abs(((pageAt - 1) * qtdReg));
		
		try {
			lista = voo.consultar(registroInicial,qtdReg);
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
		request.setAttribute("pageAt", "" + pageAt);

		request.getRequestDispatcher("gerenciaVoo.jsp").forward(request, response);
	}

}
