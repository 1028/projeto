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


if(request.getAttribute("carrega") == null) {
	System.out.println("Acabou de entrar valor é " + request.getAttribute("carrega"));
	request.setAttribute("carrega", true);
	System.out.println("Valor atribuido " + request.getAttribute("carrega"));
	request.getRequestDispatcher("IncluiVoo").forward(request, response);
	System.out.println("Valor de retorno " + request.getAttribute("carrega"));
}

%>
<title><% out.print(bundle.getString("FrmConsultarVoo.titulo")); %></title>
<link rel="stylesheet" type="text/css" href="estilo.css">
<script type="text/javascript" rel="javascript" ></script>
</head>
<body>
		<form method="post" action="incluiVoo" >
			<fieldset>
				<input type="hidden" name="op" value="cadastro" />
				<legend><% out.print(bundle.getString("FrmConsultarVoo.titulo")); %></legend>
				<p>
					<label><% out.print(bundle.getString("rotulo.valor")); %></label>
					<input type="text" name="fvalor">
				</p>
				
				<p>
					<label><% out.print(bundle.getString("rotulo.data.hora")); %></label>
					<input type="text" name="fdata">
				</p>
				
				<p>
					<label><% out.print(bundle.getString("rotulo.origem")); %></label>
					<select name="forigem">
						<c:choose>
								<c:when test="${not empty locais }" >
									<c:forEach var="local" items="${locais}" >
										<option value="${local.codigo }" >${local }</option>
									</c:forEach>
								</c:when>
							</c:choose>
					</select>
				</p>
				
				<p>
					<label><% out.print(bundle.getString("rotulo.destino")); %></label>
					<select name="fdestino">
						<c:choose>
								<c:when test="${not empty locais }" >
									<c:forEach var="local" items="${locais}" >
										<option value="${local.codigo }" >${local }</option>
									</c:forEach>
								</c:when>
							</c:choose>
					</select>
				</p>
				
				<p>
					<label><% out.print(bundle.getString("rotulo.escalas")); %></label>
					<select name="fescala">
						<c:choose>
								<c:when test="${not empty locais }" >
									<c:forEach var="local" items="${locais}" >
										<option value="${local.codigo }" >${local }</option>
									</c:forEach>
								</c:when>
							</c:choose>
					</select>
				</p>
						
				<p>
					<label><% out.print(bundle.getString("rotulo.tipo.aeronave")); %></label>
					<select name="faeronave">
						<c:choose>
								<c:when test="${not empty aeronaves }" >
									<c:forEach var="aeronave" items="${aeronaves}" >
										<option value="${aeronave.codigoAeronave }" >${aeronave }</option>
									</c:forEach>
								</c:when>
							</c:choose>
					</select>
				</p>
				
				<p>
					<label><% out.print(bundle.getString("rotulo.situacao")); %></label>
					<select name="fsituacao">
						<c:choose>
								<c:when test="${not empty situacoes }" >
									<c:forEach var="situacao" items="${situacoes}" >
										<option value="${situacao.codigo }" >${situacao }</option>
									</c:forEach>
								</c:when>
							</c:choose>
					</select>
				</p>
				
				<p id="btnForm">
					<input type="submit" value=<% out.print(bundle.getString("cadastrar")); %> class="botoes">
					<input type="reset" value=<% out.print(bundle.getString("botao.limpar")); %> class="botoes">
				</p>
			</fieldset>
		</form>
</body>
</html>