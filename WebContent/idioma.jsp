<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.Locale"
	import="java.util.ResourceBundle"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Idioma/Language</title>
<link rel="stylesheet" type="text/css" href="estilo.css" />
</head>
<body>

	<div id="container">
		<form action="GerenciaIdioma" method="post" id="idioma">
			<table>
				<tr>
					<td><input type="submit" id="btnEspanhol" name="idioma" value="es"></td>
					<td><input type="submit" id="btnIngles" name="idioma" value="en"></td>
					<td><input type="submit" id="btnPortugues" name="idioma" value="pt"></td>
				</tr>
			</table>
		</form>
	</div>

	<%
		session.setAttribute("msg", "mensagem.branco");
	%>
</body>
</body>
</html>