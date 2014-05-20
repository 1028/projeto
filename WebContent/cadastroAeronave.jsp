<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import = "java.util.Locale" import = "java.util.ResourceBundle" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
String lingua, pais;
lingua = session.getAttribute("idioma").toString();
pais = session.getAttribute("pais").toString();

Locale idioma = new Locale(lingua, pais);
ResourceBundle bundle = ResourceBundle.getBundle("Idiomas/idioma", idioma);
%>
<title><% out.print(bundle.getString("FrmCadastrarAeronave.titulo")); %></title>
<link rel="stylesheet" type="text/css" href="estilo.css">
</head>
<body>
	<form action="IncluiAeronave" method="post">
		<fieldset>
			<legend><% out.print(bundle.getString("FrmCadastrarAeronave.titulo")); %></legend>
			<p>
				<label for="nome"><% out.print(bundle.getString("rotulo.nome")); %></label> <input type="text" id="nome" name="fnome">
			</p>
			<p>
				<label><% out.print(bundle.getString("rotulo.qntAssentos")); %></label> <input type="text" name="fqntassento">
			</p>

			<p>
				<label><% out.print(bundle.getString("rotulo.tipoAeronave")); %></label> <select name="ftipoaeronave">
					<option>1</option>
					<option>2</option>
				</select>
			</p>

			<p id=btnForm>
				<input type="submit" value=<% out.print(bundle.getString("cadastrar")); %> class="botoes"> <input
					type="reset" value=<% out.print(bundle.getString("botao.limpar")); %> class="botoes">
			</p>
		</fieldset>
	</form>
</body>
</html>