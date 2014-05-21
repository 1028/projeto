<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "Model.Passageiro"
    import = "Controller.Formatador"
    import = "View.Validacoes"
 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		try {
			//Pegando os valores dos campos do formulário passado pela página html
			String nome = request.getParameter("nome");
			String sobrenome = request.getParameter("sobrenome");
			String celular = request.getParameter("celular");
			String email = request.getParameter("email");
			Formatador formatador = new Formatador();
			System.out.println(request.getParameter("nome"));
			System.out.println(request.getParameter("sobrenome"));
			System.out.println(request.getParameter("celular"));
			System.out.println(request.getParameter("email"));
			System.out.println(request.getParameter("nascimento"));
			System.out.println(request.getParameter("tratamento"));
			System.out.println(request.getParameter("perfil"));
			String dataNascimento = formatador.dataNacional(request.getParameter("nascimento"));
			
			int tratamento = Integer.parseInt(request.getParameter("tratamento"));
			int perfil = Integer.parseInt(request.getParameter("perfil"));
			
			//Verifica os campos enviados
			Validacoes validador = new Validacoes();
			
			if(!(validador.camposEmBranco(nome) || validador.camposEmBranco(email) || validador.camposEmBranco(dataNascimento))) {
				Passageiro p = new Passageiro(nome, sobrenome, celular, email, dataNascimento, tratamento, perfil);
				
				try {
					p.cadastrarPassageiro();
					request.setAttribute("msg", "Usuário cadastrado com sucesso!");
					System.out.println(request.getAttribute("msg"));
					request.getRequestDispatcher("/cadastroPassageiro.jsp").forward(request, response);
					response.getWriter().write("Usuário cadastrado com sucesso!");
				}
				catch (Exception e) {
					out.print("<p>Falha ao cadastrar o Passageiro!</p>");
					out.print("<p>" + e.getMessage() +"</p>");
					out.print("<p>" + e.getStackTrace() +"</p>");
				}
			}
			else {
				//response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Há campos não preenchidos.");
				request.setAttribute("msg", "Há campos não preenchidos.");
				request.getRequestDispatcher("/cadastroPassageiro.jsp").forward(request, response);
			}
		}
		catch (Exception e) {
			System.out.println(e);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Há campos não preenchidos.");
		}
	%>
</body>
</html>