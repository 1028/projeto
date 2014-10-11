package Controller;

import java.io.IOException;
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
		System.out.println("Passou no servlets");
		PassageiroTO passageiro = new PassageiroTO();

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

		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("sobrenome"));
		System.out.println(request.getParameter("celular"));
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("nascimento"));
		System.out
				.println(Integer.parseInt(request.getParameter("tratamento")));
		System.out.println(Integer.parseInt(request.getParameter("perfil")));

		// Verifica os campos enviados
		Validacao oValida = new Validacao();

		if (!(oValida.camposEmBranco(passageiro.getNome(),
				passageiro.getEmail(), passageiro.getDataNascimento()))) {
			Passageiro p = new Passageiro(passageiro);

			try {
				p.cadastrarPassageiro();
				request.setAttribute("msg", "mensagem.cadastrar.exito");
				System.out.println("Aqui");
				request.getRequestDispatcher("cadastroPassageiro.jsp").forward(
						request, response);
				// response.getWriter().write("Usuário cadastrado com sucesso!");
			} catch (Exception e) {
				// System.out.println("Exception");
				// System.out.println(e.getMessage());
				request.setAttribute("msg", "mensagem.cadastrar.erro");
				request.getRequestDispatcher("cadastroPassageiro.jsp").forward(
						request, response);
				// out.print("<p>Falha ao cadastrar o Passageiro!</p>");
				// out.print("<p>" + e.getMessage() +"</p>");
				// out.print("<p>" + e.getStackTrace() +"</p>");
			}

			// Alteração do dados do Passageiro
			try {
				p.alterarPassageiro();
				request.setAttribute("msg", "mensagem.alterar.exito");
				request.getRequestDispatcher("cadastroPassageiro.jsp").forward(
						request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				request.setAttribute("msg", "mensagem.alterar.erro");
				request.getRequestDispatcher("cadastroPassageiro.jsp").forward(
						request, response);
			}

			try {
				p.excluirPassageiro();
				request.setAttribute("msg", "mensagem.excluir.exito");
				request.getRequestDispatcher("cadastroPassageiro.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("msg", "mensagem.excluir.erro");
				request.getRequestDispatcher("cadastroPassageiro.jsp").forward(request, response);
			}

		} else {
			// response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Há campos não preenchidos.");
			request.setAttribute("msg", "mensage.campos.branco");
			request.getRequestDispatcher("/cadastroPassageiro.jsp").forward(
					request, response);
		}
	}
}
