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

import Model.Passageiro;
import Model.PassageiroTO;
import Controller.Formatador;

/**
 * Servlet implementation class IncluiPassageiro
 */
@WebServlet("/IncluiPassageiro")
public class GerenciaPassageiro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GerenciaPassageiro() {
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

	protected void executa(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String operacao = (String) request.getParameter("btn");
		PassageiroTO passageiro = new PassageiroTO();

		passageiro.setCodigo(Integer.parseInt(request.getParameter("codigo")));
		passageiro.setNome(request.getParameter("nome"));
		passageiro.setSobrenome(request.getParameter("sobrenome"));
		passageiro.setCelular(request.getParameter("celular"));
		passageiro.setEmail(request.getParameter("email"));
		Formatador formatador = new Formatador();
		passageiro.setDataNascimento(formatador.dataNacional(request
				.getParameter("nascimento")));
		passageiro.setFormaTrata(Integer.parseInt(request
				.getParameter("tratamento")));
		passageiro.setTipoPassageiro(Integer.parseInt(request
				.getParameter("perfil")));

		// Verifica os campos enviados
		Validacao oValida = new Validacao();

		if (oValida.operacaoCadastro(operacao) && (!(oValida.camposEmBranco(passageiro.getNome(),
				passageiro.getEmail(), passageiro.getDataNascimento())))) {
			try {
				Passageiro p = new Passageiro(passageiro);
				p.cadastrarPassageiro();
				request.setAttribute("msg", "mensagem.cadastrar.exito");
				request.getRequestDispatcher("gerenciaPassageiro.jsp").forward(
						request, response);
				// response.getWriter().write("Usuário cadastrado com sucesso!");
			} catch (Exception e) {
				request.setAttribute("msg", "mensagem.cadastrar.erro");
				request.getRequestDispatcher("gerenciaPassageiro.jsp").forward(
						request, response);
			}
		} else if (oValida.operacaoConsultar(operacao) && (!(oValida.camposEmBranco(passageiro.getNome())))){
			try {
				Passageiro p = new Passageiro(passageiro);
				List<PassageiroTO> consulta = new ArrayList<PassageiroTO>();
				
				consulta = p.consultarPassageiro();
				if(consulta.size() > 0){
					request.setAttribute("msg", "mensagem.branco");
					request.setAttribute("consultou", "ok");
					request.setAttribute("con", consulta);
					request.getRequestDispatcher("gerenciaPassageiro.jsp").forward(request, response);
				} else {
					request.setAttribute("msg",
							"mensagem.consulta.dado.nao.encontrado");
					request.getRequestDispatcher("gerenciaPassageiro.jsp")
							.forward(request, response);
				}
			} catch (SQLException e) {
				request.setAttribute("msg",
						"mensagem.consulta.dado.nao.encontrado");
				request.getRequestDispatcher("gerenciaPassageiro.jsp")
						.forward(request, response);
			}
			
		} else if (oValida.operacaoAlterar(operacao) && (passageiro.getCodigo() > 0) && (!(oValida.camposEmBranco(passageiro.getNome(),
				passageiro.getEmail(), passageiro.getDataNascimento())))){
			// Alteração do dados do Passageiro
			try {
				Passageiro p = new Passageiro(passageiro);
				p.alterarPassageiro();
				request.setAttribute("msg", "mensagem.alterar.exito");
				request.getRequestDispatcher("gerenciaPassageiro.jsp").forward(
						request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				request.setAttribute("msg", "mensagem.alterar.erro");
				request.getRequestDispatcher("gerenciaPassageiro.jsp").forward(
						request, response);
			}
		} else if(oValida.operacaoExcluir(operacao) && (passageiro.getCodigo() > 0) && (!(oValida.camposEmBranco(passageiro.getNome())))){
			try {
				Passageiro p = new Passageiro(passageiro);
				p.excluirPassageiro();
				request.setAttribute("msg", "mensagem.excluir.exito");
				request.getRequestDispatcher("gerenciaPassageiro.jsp").forward(
						request, response);
			} catch (Exception e) {
				request.setAttribute("msg", "mensagem.excluir.erro");
				request.getRequestDispatcher("gerenciaPassageiro.jsp").forward(
						request, response);
			}

		} else {
			// response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Há campos não preenchidos.");
			request.setAttribute("msg", "mensage.campos.branco");
			request.getRequestDispatcher("/gerenciaPassageiro.jsp").forward(
					request, response);
		}
	}
}
