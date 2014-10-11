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
	
	//verifica se há necessidade de carregar os comboBox das classes de domínios
	if(request.getAttribute("carrega") == null) {
		request.setAttribute("carrega", "true");
		request.getRequestDispatcher("GerenciaVoo").forward(request, response);
	}
	
	String ret = (String) request.getAttribute("ret");
	
	ret = (ret != null ? ret : "");
	
	//Verifica qual operação do CRUD está sendo realizada no momento: Valores: 1) cadastro 2)listar 3)alterar 4)excluir
	String operacao = (String) request.getAttribute("op");
	operacao = (operacao != null ? operacao : "listar");
	
	request.setAttribute("op", operacao);
	session.setAttribute("operacao", operacao);
	
	String paginaAtual = (String) request.getParameter("pageAt");
	
	paginaAtual = (paginaAtual != null ? (String)request.getParameter("pageAt") : "1");
	

	request.setAttribute("pageAt",paginaAtual);
	session.setAttribute("pageAt", paginaAtual);
	
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
			
			var operacao = "${operacao}";
			var btnCadastrar = document.getElementById("btnCadastrar");
			if(operacao === "listar") {
				var btnExcluir = document.getElementById("btnExcluir").disabled = true;
				var btnAlterar = document.getElementById("btnAlterar").disabled = true;
				btnCadastrar.disabled = true;
			}
			else if(operacao === "cadastro") {
				window.alert("cadastro");
			}
			else {
				window.alert("Indefinido");
			}
			
			//document.getElementById("formulario").onsubmit = cadastrar();
			
			function cadastrar() {
				window.alert("Tentou submeter o form");
				
				return;
			}
			
			//altera o botão alterar no evento checked
			var ckSelecao = document.getElementsByName("voo");
			var i = 0;
			while(i < ckSelecao.length)
			{
				//alert(ckSelecao.item(i));
				
				ckSelecao[i].onclick = function modoAlterar() {
					var btnAlterar = document.getElementById("btnAlterar");
					btnAlterar.disabled = false;
					
					var codigo = document.getElementsByName("fcodigo")[0];
					var valor = document.getElementsByName("fvalor")[0];
					codigo.disabled = true;
					codigo.value = this.value;
					valor.value = this.index;
					var operacao = document.getElementsByName("op")[0];
					
					operacao.value = "alterar";
					
				}
				i++;
			}
			
			
		}
		
	</script>
<style>
</style>
</head>
<body>
	<div id="wrap" >
			
			<c:if test="${empty pageAt }" >
						<c:out value="parametro nulo" ></c:out>
			</c:if>
					
			<form action="GerenciaVoo" method="get" id="formulario" name="formulario" >
				<fieldset>
					<legend><% out.print(bundle.getString("FrmConsultarVoo.titulo")); %></legend>
					
					<input type="hidden" name="op" value="${operacao }" />
					<input type="hidden" name="carrega" value="${carrega }" />
					
					<input type="hidden" value="${pageAt }" name="pageAt" />
					
					
					<p>
						<label for="fcodigo" ><% out.print(bundle.getString("rotulo.codigo")); %></label><!-- Código do Voo -->
						<input type="text" id="fcodigo" name="fcodigo" />
					</p>
					<p>
						<label for="fvalor" ><% out.print(bundle.getString("rotulo.valor")); %></label><!-- Valor do Voo -->
						<input type="text" id="fvalor" name="fvalor" />
					</p>
					<p>
					<label><% out.print(bundle.getString("rotulo.origem")); %></label>
					<select name="forigem">
						<c:choose>
								<c:when test="${not empty locais }" >
									<c:forEach var="local" items="${locais}" >
										<option value="${local }" >${local }</option>
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
										<option value="${local }" >${local }</option>
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
										<option value="${local }" >${local }</option>
									</c:forEach>
								</c:when>
							</c:choose>
					</select>
				</p>
					
					<label for="fdata" ><% out.print(bundle.getString("rotulo.data.hora")); %></label><!-- Destino do Voo -->
					<input type="text" id="fdata" name="fdata" />
					<br />
					
					<input value="<% out.print(bundle.getString("consultar")); %>" type="submit" id="btnConsulta" />
					<input value="<% out.print(bundle.getString("cadastrar")); %>" type="submit" id="btnCadastrar" />
					<input value="<% out.print(bundle.getString("alterar")); %>" type="submit" id="btnAlterar" />
					<input value="<% out.print(bundle.getString("excluir")); %>" type="submit" id="btnExcluir" />
					<nav id="paginacao" >
						<c:out value="${param.pag }" ></c:out> 
						<c:if test="${not empty total}" >
							<c:forEach var="cont" begin="1" end="${total }" >
								<c:set var="valor" value="${cont * 2-cont}" ></c:set>
								<a href="GerenciaVoo?pageAt=${valor }&op=${operacao}&carrega=${carrega}&fcodigo=" onclick="" >${cont }</a>
							</c:forEach>
						</c:if>
					</nav>
				</fieldset>
			</form>
			
			<form action="" method="get" id="boxExibicao" name="exibicao" >
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
											<td name="exibecod" >${voo.codigoVoo }</td><td name="exibeOri" >${voo.origem }</td>
											<td name="exibeDes" >${voo.destino}</td name="exibeEscala" ><td>${voo.escala}</td>
											<td name="exibeData" >${voo.dateHora}</td><td name="exibeVal" >${voo.valor}</td>
										</tr>
									</c:forEach>
								</c:when>
							</c:choose>
					</table>
				</fieldset>
			</form>
			
			<div id="msg" >
				<p>
					<c:choose>
						<c:when test="${not empty msg }" >
							<p>${msg}</p>
						</c:when>
					</c:choose>
					
				</p>
			</div>
			
		</div>
		
</body>
</html>