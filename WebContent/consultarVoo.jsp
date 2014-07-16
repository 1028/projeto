<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "java.util.Locale" 
    		 import = "java.util.ResourceBundle" 
    		 import = "java.util.ArrayList"
    		 import = "Model.VooTO"
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consultar Voo</title>
<%
String lingua, pais;
lingua = session.getAttribute("idioma").toString();
pais = session.getAttribute("pais").toString();

Locale idioma = new Locale(lingua, pais);
ResourceBundle bundle = ResourceBundle.getBundle("Idiomas/idioma", idioma);
%>
<link rel="stylesheet" type="text/css" href="estilo.css">
<style>
	table { 
		}
</style>
</head>
<body>
	<div id="wrap" >
			<%
				String ret = (String) request.getAttribute("ret");
				
				ret = (ret != null ? ret : "");
			%>
			<form action="IncluiVoo" method="get" >
				<fieldset>
					<legend><% out.print(bundle.getString("FrmConsultarVoo.titulo")); %></legend>
					<input value="pesquisar" type="submit" />
					<input type="hidden" value="listar" name="op" />
				</fieldset>
			</form>
			<table>
				<tr>
					<th>Código</th>
					<th>Destino</th>
					<th>Data/hora</th>
					<th>Valor</th>
				</tr>
					<%
						if(ret.equals("listar")) {
								ArrayList<VooTO> lista = (ArrayList<VooTO>) request.getAttribute("lst");
								for (VooTO voo : lista) {
									out.print("<tr>");
									out.print("<td>" + voo.getCodigoVoo() + "</td>"); 
									out.print("<td>" + voo.getDestino() + "</td>");
									out.print("<td>" + voo.getDateHora() + "</td>");
									out.print("<td>" + voo.getValor() + "</td>");
									out.print("</tr>");
								}
							}
						
						
					%>
			</table>
		</div>
</body>
</html>