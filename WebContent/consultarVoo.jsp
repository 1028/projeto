<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "java.util.Locale" 
    		 import = "java.util.ResourceBundle" 
    		 import = "java.util.ArrayList"
    		 import = "Model.VooTO"
    %>
<%
	String lingua, pais;
	lingua = session.getAttribute("idioma").toString();
	pais = session.getAttribute("pais").toString();
	
	Locale idioma = new Locale(lingua, pais);
	ResourceBundle bundle = ResourceBundle.getBundle("Idiomas/idioma", idioma);
	
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="">
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8" >
	<title><% out.print(bundle.getString("FrmConsultarVoo.titulo")); %></title>
	<link rel="stylesheet" type="text/css" href="estilo.css">
	<script type="text/javascript" rel="javascript" >
		window.onload = function() {
			var btns = getElementByTagName("a");
		}
	</script>
<style>
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
					<label for="codigo" ><% out.print(bundle.getString("rotulo.codigo")); %></label><!-- Código do Voo -->
					<input type="text" id="codigo"/>
					
					<label for="valor" ><% out.print(bundle.getString("rotulo.valor")); %></label><!-- Valor do Voo -->
					<input type="text" id="valor" />
					
					<label for="origem" ><% out.print(bundle.getString("rotulo.origem")); %></label><!-- Origem do Voo -->
					<input type="text" id="origem" />
					
					<label for="destino" ><% out.print(bundle.getString("rotulo.destino")); %></label><!-- Destino -->
					<input type="text" id="destino" />
					
					<label for="data" ><% out.print(bundle.getString("rotulo.data.hora")); %></label><!-- Destino do Voo -->
					<input type="text" id="data" />
					<br />
					<input value="<% out.print(bundle.getString("consultar")); %>" type="submit" id="btnConsulta" />
					<input type="hidden" value="listar" name="op" />
					
					<c:if test="${empty param.pag }" >
						<c:out value="parametro nulo" ></c:out>
					</c:if>
					
					<input type="hidden" value="${param.pag }" name="pagAt" />
				</fieldset>
			</form>
			
			<form action="" method="get" id="boxExibicao" >
				<fieldset>
					<table>
						<tr>
							<th></th>
							<th><% out.print(bundle.getString("rotulo.codigo")); %></th><!-- Código -->
							<th><% out.print(bundle.getString("rotulo.origem")); %></th><!-- Origem -->
							<th><% out.print(bundle.getString("rotulo.destino")); %></th><!-- Destino -->
							<th><% out.print(bundle.getString("rotulo.escalas")); %></th><!-- Escala -->
							<th><% out.print(bundle.getString("rotulo.data.hora")); %></th><!-- Data/hora -->
							<th><% out.print(bundle.getString("rotulo.valor") + "  (" + bundle.getString("moeda") + ")");%></th><!-- Valor -->
						</tr>
							<c:choose>
								<c:when test="${not empty lst }" >
									<c:forEach var="voo" items="${lst}" >
										<tr>
											<td><input type="radio" value="${voo.codigoVoo }" name="voo" /></td>
											<td>${voo.codigoVoo }</td><td>${voo.origem }</td>
											<td>${voo.destino}</td><td>${voo.escala}</td>
											<td>${voo.dateHora}</td><td>${voo.valor}</td>
										</tr>
									</c:forEach>
								</c:when>
							</c:choose>
					</table>
				</fieldset>
			</form>
			<nav id="paginacao" >
				<c:out value="${param.pag }" ></c:out> 
				<c:if test="${not empty total}" >
					<c:forEach var="cont" begin="1" end="${total }" >
						<c:set var="valor" value="${cont * 2-cont}" ></c:set>
						<a href="./consultarVoo.jsp?pag=${valor }" >${cont }</a>
					</c:forEach>
				</c:if>
			</nav>
		</div>
</body>
</html>