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
public class GerenciaAeronave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GerenciaAeronave() {
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
		String sCodigo, sQntAssento;
		sCodigo = request.getParameter("fcodigo");
		sQntAssento = request.getParameter("fqntassento");

		AeronaveTO aeronave = new AeronaveTO();
		aeronave.setNome(request.getParameter("fnome"));
		aeronave.setTipoAeronave((request.getParameter("ftipoaeronave")));

		Validacao oValida = new Validacao();

			
			if (oValida.operacaoCadastro(operacao) && (!(oValida.camposEmBranco(sCodigo, aeronave.getNome(), sQntAssento)))) {
				try {
					aeronave.setCodigoAeronave(Integer.parseInt(sCodigo));
					aeronave.setQtdAssentos(Integer.parseInt(sQntAssento));
					aeronave.setTipoAeronave((request.getParameter("ftipoaeronave")));
					Aeronave a = new Aeronave(aeronave);
					
					a.incluirAeronave(aeronave);
					request.setAttribute("msg", "mensagem.cadastrar.exito");
					request.getRequestDispatcher("gerenciaAeronave.jsp").forward(request, response);
				} catch (Exception e) {
					request.setAttribute("msg", "mensagem.cadastrar.erro");
					request.getRequestDispatcher("gerenciaAeronave.jsp")
							.forward(request, response);
				}

			} else if (oValida.operacaoConsultar(operacao) && (!(oValida.camposEmBranco(sCodigo)))) {
				try {
					aeronave.setCodigoAeronave(Integer.parseInt(sCodigo));
					Aeronave a = new Aeronave(aeronave);
					List<AeronaveTO> consulta = new ArrayList<AeronaveTO>();
					
					consulta = a.consultarAeronave();
					if (consulta.size() > 0) {
						request.setAttribute("msg", "mensagem.branco");
						request.setAttribute("consultou", "ok");
						request.setAttribute("con", consulta);
						request.getRequestDispatcher("gerenciaAeronave.jsp").forward(request, response);
					} else {
						request.setAttribute("msg",
								"mensagem.consulta.dado.nao.encontrado");
						request.getRequestDispatcher("gerenciaAeronave.jsp")
								.forward(request, response);
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					request.setAttribute("msg",
							"mensagem.consulta.dado.nao.encontrado");
					request.getRequestDispatcher("gerenciaAeronave.jsp")
							.forward(request, response);
				}

			} else if (oValida.operacaoAlterar(operacao) && (!(oValida.camposEmBranco(sCodigo, aeronave.getNome(), sQntAssento)))) {
				try {
					aeronave.setCodigoAeronave(Integer.parseInt(sCodigo));
					aeronave.setQtdAssentos(Integer.parseInt(sQntAssento));
					aeronave.setTipoAeronave((request.getParameter("ftipoaeronave")));
					Aeronave a = new Aeronave(aeronave);
					
					a.alterarAeronave();
					request.setAttribute("msg", "mensagem.alterar.exito");
					request.getRequestDispatcher("gerenciaAeronave.jsp")
							.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					request.setAttribute("msg", "mensagem.alterar.erro");
					request.getRequestDispatcher("gerenciaAeronave.jsp")
							.forward(request, response);
				}
			} else if(oValida.operacaoExcluir(operacao) && (!(oValida.camposEmBranco(sCodigo)))) {
				try {
					aeronave.setCodigoAeronave(Integer.parseInt(sCodigo));
					Aeronave a = new Aeronave(aeronave);
					
					a.excluirAeronave();
					request.setAttribute("msg", "mensagem.excluir.exito");
					request.getRequestDispatcher("gerenciaAeronave.jsp")
							.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					request.setAttribute("msg", "mensagem.excluir.erro");
					request.getRequestDispatcher("gerenciaAeronave.jsp")
							.forward(request, response);
				}
			
		} else {
			request.setAttribute("msg", "mensage.campos.branco");
			request.getRequestDispatcher("gerenciaAeronave.jsp").forward(
					request, response);
		}

	}
}
