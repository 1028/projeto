<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Locale" import="java.util.ResourceBundle"
	import="Model.AeronaveTO" import="java.util.ArrayList"
	import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	ResourceBundle bundle;
	bundle = (ResourceBundle) session.getAttribute("idioma");
%>
<title>
	<%
		out.print(bundle.getString("FrmAeronave.titulo"));
	%>
</title>
<link rel="stylesheet" type="text/css" href="estilo.css">
<link href='http://fonts.googleapis.com/css?family=Roboto:400,500'
	rel='stylesheet' type='text/css'>
		<%
			String msg;
			if(session.getAttribute("msg").toString().equals("")){
				out.print("<script src='scripts/jquery-1.11.0.min.js'>"
				+"</script>"
				+"<script src='scripts/msg.js'></script>");
			}
		%>
</head>
<body>
	<%
		String consultar = (String) request.getAttribute("consultou");

		ArrayList<AeronaveTO> consulta = new ArrayList<AeronaveTO>();
		AeronaveTO element = null;
		if (consultar == "ok") {
			consulta = (ArrayList<AeronaveTO>) request.getAttribute("con");
			Iterator itr = consulta.iterator();
			while (itr.hasNext()) {
				element = (AeronaveTO) itr.next();
			}
		} else {
			AeronaveTO naoConsultado = new AeronaveTO();
			naoConsultado.setNome("");
			naoConsultado.setTipoAeronave("1");
			consulta.add(naoConsultado);
			Iterator itr = consulta.iterator();
			while (itr.hasNext()) {
				element = (AeronaveTO) itr.next();
			}
		}
	%>
	<form action="IncluiAeronave" method="post">
		<fieldset>
			<legend>
				<%
					out.print(bundle.getString("FrmAeronave.titulo"));
				%>
			</legend>
			<p>
				<label for="codigo"> <%
 	out.print(bundle.getString("rotulo.codigo"));
 %>
				</label> <input type="text" id="codigo" name="fcodigo"
					value=<%if (element.getCodigoAeronave() > 0) {
				out.print(element.getCodigoAeronave());
			} else {
				out.print("");
			}%>>
			</p>

			<p>
				<label for="nome"> <%
 	out.print(bundle.getString("rotulo.nome"));
 %></label> <input type="text" id="nome" name="fnome"
					value=<%out.print(element.getNome());%>>
			</p>

			<p>
				<label> <%
 	out.print(bundle.getString("rotulo.qntAssentos"));
 %></label> <input type="text" name="fqntassento"
					value=<%if (element.getQtdAssentos() > 0) {
				out.print(element.getQtdAssentos());
			} else {
				out.print("");
			}%>>
			</p>

			<p>
				<label> <%
 	out.print(bundle.getString("rotulo.tipoAeronave"));
 %></label> <select name="ftipoaeronave">
					<option value="1" <%if(element.getTipoAeronave().equals("1")){out.print("selected='selected'");} %>>
						<%
							out.print(bundle.getString("tipoAeronave.comercial"));
						%>
					</option>
					<option value="2" <%if(element.getTipoAeronave().equals("2")){out.print("selected='selected'");}%>>
						<%
							out.print(bundle.getString("tipoAeronave.luxo"));
						%>
					</option>
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
	<div id=msg>
		<%
			if (session.getAttribute("msg").toString().equals("")) {
				msg = request.getAttribute("msg").toString();
			} else {
				msg = session.getAttribute("msg").toString();
			}
			session.setAttribute("msg", "");
			out.print(bundle.getString(msg));
		%>
	</div>
</body>
</html>