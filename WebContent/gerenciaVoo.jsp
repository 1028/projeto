<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "java.util.Locale" 
    		 import = "java.util.ResourceBundle" 
    		 import = "java.util.ArrayList"
    		 import = "Model.VooTO"
    %>
<%
	
	ResourceBundle bundle;
	bundle = (ResourceBundle) session.getAttribute("idioma");
	
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
			var btnAlterar = document.getElementById("btnAlterar");
			var btnExcluir = document.getElementById("btnExcluir");
			var btnConsultar = document.getElementById("btnConsulta");
			
			btnConsultar.addEventListener('click',function(event) {
				var oper = document.getElementsByName("op")[0];
				var exibe = document.getElementById("mostraOp");
				
				if(oper.value != "listar") {
					oper.value = "listar";
					exibe.innerHTML = oper.value;
				}
			});
			
			//trata os eventos do botão alterar
			btnAlterar.addEventListener('click', function(event) {
				var oper = document.getElementsByName("op")[0];
				var exibe = document.getElementById("mostraOp");
				
				if(oper.value ==="listar") {
					document.getElementById("formulario").onsubmit = function() {return false};
					oper.value = "alterar";
					exibe.innerHTML = oper.value;					
				}
				else if(oper.value ==="alterar") {
					window.alert(document.getElementById("fcodigo").value);
					document.getElementById("fcodigo").disabled = false;
					window.alert("mandou Alterar");
				}
			});
			
			//trata os eventos do botão excluir
			btnExcluir.addEventListener('click', function(event) {
				var oper = document.getElementsByName("op")[0];
				
				if(oper.value === "alterar") {
					oper.value = "excluir";
				}
				else {
					window.alert("Voo ainda não selelcionado");
					document.getElementById("formulario").onsubmit = function() {return false};
				}
				
			});
			
			//trata os eventos do botão cadastrar
			btnCadastrar.addEventListener('click',function(){
				var oper = document.getElementsByName("op")[0];
				var exibe = document.getElementById("mostraOp");
				
				if(oper.value != "cadastro") {
					document.getElementById("formulario").reset();
					oper.value = "cadastro";
					exibe.innerHTML = "Operação: " + oper.value;
					document.getElementById("formulario").onsubmit = function() {return false};
				}
				else {
					document.getElementById("formulario").onsubmit = function() {return true};
				}
				
			});
			
			
			//altera o botão alterar no evento checked
			var ckSelecao = document.getElementsByName("voo");
			var i = 0;
			while(i < ckSelecao.length)
			{
				//alert(ckSelecao.item(i));
				
				ckSelecao[i].onclick = function modoAlterar() {
					var btnAlterar = document.getElementById("btnAlterar");
					var vooAntigo = document.getElementById("vooAntigo");
					
					
					
					var codigo = document.getElementById("fcodigo");
					var valor = document.getElementsByName("fvalor")[0];
					var data = document.getElementsByName("fdata")[0];
					codigo.disabled = true;
					codigo.value = this.value;
					
					valor.value = document.getElementsByName("exibeVal" + codigo.value)[0].innerHTML;
					data.value = document.getElementsByName("exibeData" + codigo.value)[0].innerHTML;
					var operacao = document.getElementsByName("op")[0];
					
					var origem = document.getElementsByName("forigem")[0];
					var oriVoo = document.getElementsByName("exibeOri" + codigo.value)[0].innerHTML;
					origem.selectedIndex = (oriVoo.substring(0,1)-1);
					
					var destino = document.getElementsByName("fdestino")[0];
					var destVoo = document.getElementsByName("exibeDes" + codigo.value)[0].innerHTML;
					destino.selectedIndex = (destVoo.substring(0,1)-1);
					
					var escala = document.getElementsByName("fescala")[0];
					var escVoo = document.getElementsByName("exibeEscala" + codigo.value)[0].innerHTML;
					escala.selectedIndex = (escVoo.substring(0,1)-1);
					
					//falta complementar com o código da situação e também da aeronavee
					vooAntigo.value = valor.value + "#" + data.value + "#" + (oriVoo.substring(0,1)) + "#" + (destVoo.substring(0,1)) + "#" + (escVoo.substring(0,1));
					window.alert(vooAntigo.value);
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
			<p id="mostraOp" >Operação: ${op }</p>
			<c:if test="${empty pageAt }" >
						<c:out value="parametro nulo" ></c:out>
			</c:if>
					
			<form action="GerenciaVoo" method="get" id="formulario" name="formulario" >
				<fieldset>
					<legend><% out.print(bundle.getString("FrmConsultarVoo.titulo")); %></legend>
					
					<input type="hidden" name="op" value="${operacao }" />
					<input type="hidden" name="carrega" value="${carrega }" />
					<input type="hidden" value="${pageAt }" name="pageAt" />
					<input type="hidden" value="" name="vooAntigo" id="vooAntigo" />
					
					
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
								<a href="GerenciaVoo?pageAt=${valor }&op=${operacao}&carrega=${carrega}" onclick="" >${cont }</a>
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
										<tr name="${voo.codigoVoo }" >
											<td><input type="radio" value="${voo.codigoVoo }" name="voo" /></td>
											<td name="exibecod${voo.codigoVoo }" >${voo.codigoVoo }</td>
											<td name="exibeOri${voo.codigoVoo }" >${voo.origem }</td>
											<td name="exibeDes${voo.codigoVoo }" >${voo.destino}</td>
											<td name="exibeEscala${voo.codigoVoo }" >${voo.escala}</td>
											<td name="exibeData${voo.codigoVoo }" >${voo.dateHora}</td>
											<td name="exibeVal${voo.codigoVoo }" >${voo.valor}</td>
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