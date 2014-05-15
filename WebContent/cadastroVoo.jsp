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
<title><% out.print(bundle.getString("FrmConsultarVoo.titulo")); %></title>
<link rel="stylesheet" type="text/css" href="estilo.css">
</head>
<body>
		<form>
			<fieldset>
		<legend><% out.print(bundle.getString("FrmConsultarVoo.titulo")); %></legend>
		<p><label><% out.print(bundle.getString("rotulo.valor")); %></label>
		<input type="text" name="fvalor"></p>
		
		<p><label><% out.print(bundle.getString("rotulo.data.hora")); %></label>
		<input type="text" name="fdata"></p>
		
		<p><label><% out.print(bundle.getString("rotulo.origem")); %></label>
		<select></select></p>
		
		<p><label><% out.print(bundle.getString("rotulo.destino")); %></label>
		<select></select></p>
		
		<p><label><% out.print(bundle.getString("rotulo.escalas")); %></label>
		<select></select></p>
				
		<p><label><% out.print(bundle.getString("rotulo.tipo.aeronave")); %></label>
		<select></select></p>
		
		<p><label><% out.print(bundle.getString("rotulo.situacao")); %></label>
		<select></select></p>
		
		<p id="btnForm">
		<input type="submit" value=<% out.print(bundle.getString("cadastrar")); %> class="botoes">
		<input type="reset" value=<% out.print(bundle.getString("botao.limpar")); %> class="botoes">
		</p>
		</fieldset>
		</form>
</body>
</html>