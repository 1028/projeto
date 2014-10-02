<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Locale" import="java.util.ResourceBundle"
	import="Model.AeronaveTO" import="java.util.ArrayList" import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	String lingua, pais;
	lingua = session.getAttribute("idioma").toString();
	pais = session.getAttribute("pais").toString();

	Locale idioma = new Locale(lingua, pais);
	ResourceBundle bundle = ResourceBundle.getBundle("Idiomas/idioma",
			idioma);
%>
<title>
	<%
		out.print(bundle.getString("FrmCadastrarAeronave.titulo"));
	%>
</title>
<link rel="stylesheet" type="text/css" href="estilo.css">
	<link href='http://fonts.googleapis.com/css?family=Roboto:400,500' rel='stylesheet' type='text/css'>
</head>
<body>

	<%
		String consultar = (String) request.getAttribute("consultou");
	
		ArrayList<AeronaveTO> consulta = new ArrayList<AeronaveTO>();
		AeronaveTO element = null;
		if (consultar == "ok") {
			consulta = (ArrayList<AeronaveTO>) request.getAttribute("con");
			 Iterator itr = consulta.iterator();
		      while(itr.hasNext()) {
		        element = (AeronaveTO) itr.next();
		         System.out.print(element.getCodigoAeronave() + " ");
		      }
		/*	for (AeronaveTO aTO : consulta) {
				out.print(aTO.getCodigoAeronave() + "/" + aTO.getNome()
						+ "/" + aTO.getQtdAssentos() + "/"
						+ aTO.getTipoAeronave());
			}*/
		}
		else {
			AeronaveTO naoConsultado = new AeronaveTO();
			naoConsultado.setNome("");
			consulta.add(naoConsultado);
			Iterator itr = consulta.iterator();
			 while(itr.hasNext()) {
			        element = (AeronaveTO) itr.next();
			         System.out.print(element + " ");
			      }
		}
	%>
	<form action="IncluiAeronave" method="post">
		<fieldset>
			<legend>
				<%
					out.print(bundle.getString("FrmCadastrarAeronave.titulo"));
				%>
			</legend>
			<p>
				<label for="codigo"> <%out.print(bundle.getString("rotulo.codigo")); %> </label> 
				<input type="text" id="codigo"
					name="fcodigo" value=<%out.print(element.getCodigoAeronave()); %>>
			</p>
			
			<p>
				<label for="nome"> <%out.print(bundle.getString("rotulo.nome"));%></label> 
				<input type="text" id="nome" name="fnome" value=<%out.print(element.getNome()); %>>
			</p>
			
			<p>
				<label> <%out.print(bundle.getString("rotulo.qntAssentos"));%></label> 
				<input type="text" name="fqntassento" value=<%out.print(element.getQtdAssentos()); %>>
			</p>

			<p>
				<label> <%out.print(bundle.getString("rotulo.tipoAeronave")); %></label>
				 <select name="ftipoaeronave">
					<option value="1"><%out.print(bundle.getString("tipoAeronave.comercial")); %></option>
					<option value="2"><%out.print(bundle.getString("tipoAeronave.luxo")); %></option>
				</select>
			</p>

			<p id=btnForm>
				<input type="hidden" name="operacao" value="cadastrar"> <input
					type="submit" name="btn"
					value=<%out.print(bundle.getString("cadastrar"));%> class="botoes">

				<input type="submit" name="btn"
					value=<%out.print(bundle.getString("consultar"));%> class="botoes">
				<input type="submit" name="btn"
					value=<%out.print(bundle.getString("alterar"));%> class="botoes">
				<input type="submit" name="btn"
					value=<%out.print(bundle.getString("excluir"));%> class="botoes">
				<input type="reset"
					value=<%out.print(bundle.getString("botao.limpar"));%>
					class="botoes">
			</p>
		</fieldset>
	</form>
	<div id = msg>
	<% 
		//String msg = request.getAttribute("msg").toString();
		//out.print(bundle.getString(msg));
	%>
	</div>
	<!-- <form action="IncluiAeronave" method="post">
		<input type="hidden" name="operacao" value="consultar">
		<input type="submit" name="btn" value="con" class="botoes">
	</form>
	<form action="IncluiAeronave" method="post">
		<input type="hidden" name="operacao" value="alterar">
		<input type="submit" name="btn" value="alt" class="botoes">
	</form>
	<form action="IncluiAeronave" method="post">
		<input type="hidden" name="operacao" value="excluir">
		<input type="submit" name="btn" value="ex" class="botoes">
	</form> -->
</body>
</html>