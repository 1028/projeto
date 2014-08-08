<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ResourceBundle" import="java.util.Locale"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String lingua, pais;
	lingua = session.getAttribute("idioma").toString();
	pais = session.getAttribute("pais").toString();
	Locale idioma = new Locale(lingua, pais);
	ResourceBundle bundle = ResourceBundle.getBundle("Idiomas/idioma", idioma);
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> <% out.print(bundle.getString("FrmSistemaPassagensAereas.titulo")); %></title>
<link rel="stylesheet" type="text/css" href="estilo.css">

</head>
<body>


	<nav>
	<ul class="menu">
		<li><a>  <% out.print(bundle.getString("FrmSistemaPassagensAereas.menu.sistema")); %> </a>
			<ul>
				<li><a href="#" onclick="mensagem('<% out.print(bundle.getString("mensagem.sobre")); %>')"> <% out.print(bundle.getString("FrmSistemaPassagensAereas.submenu.sobre")); %> </a></li>
				<li><a href="#"><% out.print(bundle.getString("FrmSistemaPassagensAereas.submenu.sair")); %></a></li>
			</ul></li>
		<li><a><% out.print(bundle.getString("FrmSistemaPassagensAereas.menu.passagem")); %></a>
			<ul>
				<li><a href="comprarPassagem"><% out.print(bundle.getString("FrmSistemaPassagensAereas.submenu.comprar")); %></a></li>
				<li><a href="#"><% out.print(bundle.getString("FrmSistemaPassagensAereas.submenu.cancelar")); %></a></li>
				<li><a href="#">2.3</a></li>
			</ul></li>

		<li><a href="#"><% out.print(bundle.getString("FrmSistemaPassagensAereas.submenu.checkin")); %></a></li>

		<li><a><% out.print(bundle.getString("FrmSistemaPassagensAereas.menu.voo")); %></a>
			<ul>
				<li><a href="cadastroVoo.jsp"><% out.print(bundle.getString("cadastrar")); %></a></li>
				<li><a href="consultarVoo.jsp"><% out.print(bundle.getString("consultar")); %></a></li>
				<li><a href="#"><% out.print(bundle.getString("alterar")); %></a></li>
				<li><a href="#"><% out.print(bundle.getString("excluir")); %></a></li>
			</ul>
		</li>
		<li><a><% out.print(bundle.getString("FrmSistemaPassagensAereas.menu.aeronave")); %></a>
			<ul>
				<li><a href="cadastroAeronave.jsp"><% out.print(bundle.getString("cadastrar")); %></a></li>
				<li><a href="#"><% out.print(bundle.getString("consultar")); %></a></li>
				<li><a href="#"><% out.print(bundle.getString("alterar")); %></a></li>
				<li><a href="#"><% out.print(bundle.getString("excluir")); %></a></li>
			</ul>
		</li>
		<li><a>Tabelas</a>
		</li>
	</ul>
	</nav>
	
<!--	<script>
	function mensagem(texto){
		alert(texto);
	}
	</script> -->
</body>
</html>