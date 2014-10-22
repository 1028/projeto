<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.Locale"
	import="java.util.ResourceBundle"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	ResourceBundle bundle;
	bundle = (ResourceBundle) session.getAttribute("idioma");
%>

<title>
	<%
		out.print(bundle.getString("FrmLogin.titulo"));
	%>
</title>
<link rel="stylesheet" type="text/css" href="estilo.css">
<link href='http://fonts.googleapis.com/css?family=Roboto:400,500'
	rel='stylesheet' type='text/css'>
</head>
<body>
<div id="container">
	<form action="GerenciaLogin" method="post">
		<fieldset>
		<legend><%out.print(bundle.getString("FrmLogin.titulo"));%></legend>
			<p>
				<label> <%
 	out.print(bundle.getString("FrmLogin.rotulo.login"));
 %>
				</label> <input type="text" name="flogin">
			</p>

			<p>
				<label> <%
 	out.print(bundle.getString("FrmLogin.rotulo.senha"));
 %>
				</label> <input type="password" name="fsenha">
			</p>

			<p id="btnForm">
				<input type="submit"
					value=<%out.print(bundle.getString("botao.confirmar"));%>
					class="botoes">
			</p>
		</fieldset>
	</form>

	<div id=msg>
		<%
			String msg;
			if(session.getAttribute("msg").toString().equals("")){
				msg = request.getAttribute("msg").toString();
			}else{
				msg = session.getAttribute("msg").toString();
			}
				session.setAttribute("msg", "");				
				out.print(bundle.getString(msg));
		%>
	</div>
</div>
</body>
</html>