<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import = "java.util.Locale" import = "java.util.ResourceBundle" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
		<select name="forigem"></select></p>
		
		<p><label><% out.print(bundle.getString("rotulo.destino")); %></label>
		<select name="fdestino"></select></p>
		
		<p><label><% out.print(bundle.getString("rotulo.escalas")); %></label>
		<select name="fescala"></select></p>
				
		<p><label><% out.print(bundle.getString("rotulo.tipo.aeronave")); %></label>
		<select name="faeronave"></select></p>
		
		<p><label><% out.print(bundle.getString("rotulo.situacao")); %></label>
		<select name="fsituacao"></select></p>
		
		<p id="btnForm">
		<input type="submit" value=<% out.print(bundle.getString("cadastrar")); %> class="botoes">
		<input type="reset" value=<% out.print(bundle.getString("botao.limpar")); %> class="botoes">
		</p>
		</fieldset>
		</form>
</body>
</html>