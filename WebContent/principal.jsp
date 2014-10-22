<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ResourceBundle" import="java.util.Locale"%>
<!DOCTYPE HTML>
<html lang="pt-br">
<head>
<%
ResourceBundle bundle;
bundle = (ResourceBundle) session.getAttribute("idioma");
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> <% out.print(bundle.getString("FrmSistemaPassagensAereas.titulo")); %></title>
<link rel="stylesheet" type="text/css" href="estilo.css">
<link href='http://fonts.googleapis.com/css?family=Roboto:400,500' rel='stylesheet' type='text/css'>
</head>
<body>
<div id="wrap" >
<ul class="menu">
<li><a><% out.print(bundle.getString("FrmSistemaPassagensAereas.menu.sistema")); %></a><!-- Botão Sistema -->
<ul>
<li><a href="GerenciaMenuSistema?op=1"><% out.print(bundle.getString("FrmSistemaPassagensAereas.submenu.sobre")); %></a></li><!-- SubBotão Sobre -->
<li><a href="GerenciaMenuSistema?op=2"><% out.print(bundle.getString("FrmSistemaPassagensAereas.submenu.sair")); %></a></li><!-- SubBotão Sair -->
</ul></li>
<li><a><% out.print(bundle.getString("FrmSistemaPassagensAereas.menu.passagem")); %></a><!-- Btn Passagem -->
<ul>
<li><a href="comprarPassagem"><% out.print(bundle.getString("FrmSistemaPassagensAereas.submenu.comprar")); %></a></li><!-- Btn Comprar-->
<li><a href="#"><% out.print(bundle.getString("FrmSistemaPassagensAereas.submenu.cancelar")); %></a></li><!-- Btn Cancelar -->
<li><a href="gerenciaPassageiro.jsp"><%out.print(bundle.getString("FrmPassageiro.titulo"));%></a></li>
</ul></li>
<li><a href="#"><% out.print(bundle.getString("FrmSistemaPassagensAereas.submenu.checkin")); %></a></li><!-- Btn Check-in -->
<li><a><% out.print(bundle.getString("FrmSistemaPassagensAereas.menu.voo")); %></a><!-- Btn Voo -->
<ul>
<li><a href="cadastroVoo.jsp"><% out.print(bundle.getString("cadastrar")); %></a></li><!-- Btn Cadastrar -->
<li><a href="consultarVoo.jsp"><% out.print(bundle.getString("consultar")); %></a></li><!-- Btn Consultar -->
<li><a href="gerenciaVoo.jsp"><% out.print(bundle.getString("alterar")); %></a></li><!-- Btn Alterar -->
<li><a href="#"><% out.print(bundle.getString("excluir")); %></a></li><!-- Btn Excluir -->
</ul>
</li>
<li><a><% out.print(bundle.getString("FrmSistemaPassagensAereas.menu.aeronave")); %></a><!-- Btn Aeronave -->
<ul>
<li><a href="gerenciaAeronave.jsp"><% out.print(bundle.getString("FrmAeronave.titulo")); %></a></li><!-- Btn Aeronave - Cadastrar -->
</ul>
</li>
<li><a>Tabelas</a>
</li>
</ul>
</div>
<div id = msg>
<%
session.setAttribute("msg", "mensagem.branco");
String msg = request.getAttribute("msg").toString();
out.print(bundle.getString(msg));
%>
</div>
</body>
</html>
