<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ResourceBundle" import="java.util.Locale"%>

<!DOCTYPE HTML>
<html lang="pt-br">
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

	<div id="wrap" >
			<nav>
				<ul class="menu">
					<li><a>  <% out.print(bundle.getString("FrmSistemaPassagensAereas.menu.sistema")); %> </a><!-- Bot�o Sistema -->
						<ul>
							<li><a href="mensagem.jsp"><% out.print(bundle.getString("FrmSistemaPassagensAereas.submenu.sobre")); %></a></li><!-- SubBot�o Sobre -->
							<li><a href="#"><% out.print(bundle.getString("FrmSistemaPassagensAereas.submenu.sair")); %></a></li><!-- SubBot�o Sair -->
						</ul></li>
					<li><a><% out.print(bundle.getString("FrmSistemaPassagensAereas.menu.passagem")); %></a><!-- Btn Passagem -->
						<ul>
							<li><a href="comprarPassagem"><% out.print(bundle.getString("FrmSistemaPassagensAereas.submenu.comprar")); %></a></li><!-- Btn Comprar-->
							<li><a href="#"><% out.print(bundle.getString("FrmSistemaPassagensAereas.submenu.cancelar")); %></a></li><!-- Btn Cancelar -->
							<li><a href="#">2.3</a></li>
						</ul></li>
			
					<li><a href="#"><% out.print(bundle.getString("FrmSistemaPassagensAereas.submenu.checkin")); %></a></li><!-- Btn Check-in -->
			
					<li><a><% out.print(bundle.getString("FrmSistemaPassagensAereas.menu.voo")); %></a><!-- Btn Voo -->
						<ul>
							<li><a href="cadastroVoo.jsp"><% out.print(bundle.getString("cadastrar")); %></a></li><!-- Btn Cadastrar -->
							<li><a href="consultarVoo.jsp"><% out.print(bundle.getString("consultar")); %></a></li><!-- Btn Consultar -->
							<li><a href="#"><% out.print(bundle.getString("alterar")); %></a></li><!-- Btn Alterar -->
							<li><a href="#"><% out.print(bundle.getString("excluir")); %></a></li><!-- Btn Excluir -->
						</ul>
					</li>
					<li><a><% out.print(bundle.getString("FrmSistemaPassagensAereas.menu.aeronave")); %></a><!-- Btn Aeronave -->
						<ul>
							<li><a href="cadastroAeronave.jsp"><% out.print(bundle.getString("cadastrar")); %></a></li><!-- Btn Aeronave - Cadastrar -->
							<li><a href="#"><% out.print(bundle.getString("consultar")); %></a></li><!-- Btn Aeronave - Consultar -->
							<li><a href="#"><% out.print(bundle.getString("alterar")); %></a></li><!-- Btn Aeronave - Alterar -->
							<li><a href="#"><% out.print(bundle.getString("excluir")); %></a></li><!-- Btn Aeronave - Excluir -->
						</ul>
					</li>
					<li><a>Tabelas</a>
					</li>
				</ul>
			</nav>
	</div>
</body>
</html>