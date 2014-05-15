<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.Locale"
	import="java.util.ResourceBundle"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
		Locale idioma;
		ResourceBundle bundle;
		
		String escolha=request.getParameter("idioma");
		String escolhaPais = "";
		if (escolha.equals("es")) {
			idioma = new Locale("es", "ES");
			bundle = ResourceBundle.getBundle("Idiomas/idioma", idioma);
			escolhaPais = "ES";
		} else if (escolha.equals("en")) {
			idioma = new Locale("en", "US");
			bundle = ResourceBundle.getBundle("Idiomas/idioma", idioma);
			escolhaPais = "US";
		} else {
			System.out.println(request.getParameter("idioma"));
			idioma = new Locale("pt", "BR");
			bundle = ResourceBundle.getBundle("Idiomas/idioma", idioma);
			escolhaPais = "BR";
		}
		
		session.setAttribute("idioma", escolha);
		session.setAttribute("pais", escolhaPais);
	%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> <% out.print(bundle.getString("FrmLogin.titulo")); %> </title>
</head>
<body>
	


	<form action="principal.jsp">
		<fieldset>
			<p>
				<label> <% out.print(bundle.getString("FrmLogin.rotulo.login")); %> </label>
				<input type="text" name="flogin">
			</p>

			<p>
				<label> <% out.print(bundle.getString("FrmLogin.rotulo.senha")); %> </label>
				<input type="password" name="fsenha">
			</p>
			
			<p id="btnForm">
				<input type="submit" value=<%out.print(bundle.getString("botao.confirmar"));%> class="botoes">
			</p>
		</fieldset>
	</form>
</body>
</html>