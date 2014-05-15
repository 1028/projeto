<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "java.util.Locale"
    import ="java.util.ResourceBundle" %>
<!DOCTYPE html>
<html>
<head>
<%
String lingua, pais;
lingua = session.getAttribute("idioma").toString();
pais = session.getAttribute("pais").toString();
Locale idioma = new Locale(lingua, pais);
ResourceBundle bundle = ResourceBundle.getBundle("Idiomas/idioma", idioma);
%>
	<title> <% out.print(bundle.getString("FrmCadastrarPassageiro.titulo")); %> </title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" >
	<link rel="stylesheet" type="text/css" href="estilo.css" >
	<script type="text/javascript" src="scripts/jquery-1.11.0.min.js" > </script>
	<script type="text/javascript" src="scripts/jquery.maskedinput.js" > </script>
	<script type="text/javascript" src="scripts/script.js" > </script>
	<!--<script type="text/javascript" src="scripts/ajax.js" > </script>-->
</head>
<body>
	<div id="container" >
		<form action="IncluiPassageiro" method=post >
		<fieldset>
		<legend><% out.print(bundle.getString("FrmCadastrarPassageiro.titulo")); %></legend>
			<p><label for="nome" ><% out.print(bundle.getString("rotulo.nome")); %></label> <input type="text" id="nome" name="nome" required/></p>
			<p><label for="sobrenome" ><% out.print(bundle.getString("rotulo.sobrenome")); %>: </label> <input type="text" id="sobrenome" name="sobrenome" /></p>
			<p><label for="celular" ><% out.print(bundle.getString("rotulo.telefone")); %>: </label> <input type="text" id="celular" name="celular" pattern="\(\d{2}\) \d{5}-\d{4}" /></p>
			<p><label for="nascimento" ><% out.print(bundle.getString("rotulo.data.nasc")); %></label> <input type="text" id="nascimento" name="nascimento" maxlength="10" /></p>
			<p><label for="email" ><% out.print(bundle.getString("rotulo.email")); %></label> <input type="email" id="email" name="email" required/></p>
			<p>
				<label for="tratamento"><% out.print(bundle.getString("rotulo.forma.trat")); %></label>
				<select id="tratamento" name="tratamento" >
					<option value="1" >Tratamento1</option>
					<option value="2">Tratamento2</option>
					<option value="3">Tratamento3</option>
				</select>
			</p>
			<p>
				<label for="perfil" ><% out.print(bundle.getString("rotulo.tipo.pass")); %></label>
				<select id="perfil" name="perfil" >
					<option value="1">Perfil 1</option>
					<option value="2">Perfil 2</option>
					<option value="3">Perfil 3</option>
				</select>
			</p>
			<p id="btnForm" >
			<input type="submit" value=<% out.print(bundle.getString("cadastrar")); %> class="botoes" />
			<input type="reset" value=<% out.print(bundle.getString("botao.limpar")); %> class="botoes" />
			</p>
		</fieldset>
		</form>
		<div id="msg" >
			<%
				out.print(request.getParameter("msg"));
			%>
		</div>
	</div>
</body>
</html>