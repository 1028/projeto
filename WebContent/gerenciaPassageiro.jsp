<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Locale" import="java.util.ResourceBundle"
	import="java.util.List" import="java.util.ArrayList"
	import="Model.FormaTratamento" import="Model.FormaTratamentoTO"
	import="Model.Perfil" import="Model.PerfilTO"
	import="Model.PassageiroTO" import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<%
ResourceBundle bundle;
bundle = (ResourceBundle) session.getAttribute("idioma");
%>
<title>
	<%
		out.print(bundle.getString("FrmPassageiro.titulo"));
	%>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="estilo.css">
<script type="text/javascript" src="scripts/jquery-1.11.0.min.js">
	
</script>
<script type="text/javascript" src="scripts/jquery.maskedinput.js">
	
</script>
<script type="text/javascript" src="scripts/script.js">
	
</script>
<!--<script type="text/javascript" src="scripts/ajax.js" > </script>-->
<link href='http://fonts.googleapis.com/css?family=Roboto:400,500'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<%
		String consultar = (String) request.getAttribute("consultou");

		ArrayList<PassageiroTO> consulta = new ArrayList<PassageiroTO>();
		PassageiroTO element = null;
		if (consultar == "ok") {
			consulta = (ArrayList<PassageiroTO>) request.getAttribute("con");
			Iterator itr = consulta.iterator();
			while (itr.hasNext()) {
				element = (PassageiroTO) itr.next();
			}
		} else {
			PassageiroTO naoConsultado = new PassageiroTO();
			naoConsultado.setNome("");
			naoConsultado.setSobrenome("");
			naoConsultado.setCelular("");
			naoConsultado.setDataNascimento("");
			naoConsultado.setEmail("");
			consulta.add(naoConsultado);
			Iterator itr = consulta.iterator();
			while (itr.hasNext()) {
				element = (PassageiroTO) itr.next();
			}
		}
	%>
	<div id="container">
		<form action="IncluiPassageiro" method="post">
			<fieldset>
				<legend>
					<%
						out.print(bundle.getString("FrmPassageiro.titulo"));
					%>
				</legend>
				<input type="hidden" name="codigo" value="<%out.print(element.getCodigo());%>">
				<p>
					<label for="nome"> <%
 	out.print(bundle.getString("rotulo.nome"));
 %>
					</label> <input type="text" id="nome" name="nome" required value="<%out.print(element.getNome());%>"/>
				</p>
				<p>
					<label for="sobrenome"> <%
 	out.print(bundle.getString("rotulo.sobrenome"));
 %>
					</label> <input type="text" id="sobrenome" name="sobrenome" value="<%out.print(element.getSobrenome());%>"/>
				</p>
				<p>
					<label for="celular"> <%
 	out.print(bundle.getString("rotulo.telefone"));
 %>
					</label> <input type="text" id="celular" name="celular"
						pattern="\(\d{2}\) \d{5}-\d{4}" value="<%out.print(element.getCelular());%>"/>
				</p>
				<p>
					<label for="nascimento"> <%
 	out.print(bundle.getString("rotulo.data.nasc"));
 %>
					</label> <input type="text" id="nascimento" name="nascimento"
						maxlength="10" value="<%out.print(element.getDataNascimento());%>"/>
				</p>
				<p>
					<label for="email"> <%
 	out.print(bundle.getString("rotulo.email"));
 %>
					</label> <input type="email" id="email" name="email" value="<%out.print(element.getEmail());%>"/>
				</p>
				<p>
					<label for="tratamento"> <%
 	out.print(bundle.getString("rotulo.forma.trat"));
 %>
					</label> <select id="tratamento" name="tratamento">
						<%
							FormaTratamento oTratamento = new FormaTratamento();

							List<FormaTratamentoTO> auxFormaTrata = oTratamento
									.consultarFormaTratamento();

							for (int i = 0; i < auxFormaTrata.size(); i++) {
								// ìndices são invertidos pois na instanciação
								String nome = "forma.trat." + auxFormaTrata.get(i).getCodigo();
								String forma = (i + 1) + " - " + bundle.getString(nome);
								out.print("<option value =" + (i + 1) + ">" + forma
										+ "</option>");
							}
						%>
					</select>
				</p>
				<p>
					<label for="perfil"> <%
 	out.print(bundle.getString("rotulo.tipo.pass"));
 %>
					</label> <select id="perfil" name="perfil">
						<%
							Perfil oPerfil = new Perfil();

							List<PerfilTO> auxPerfil = oPerfil.consultarPerfil();

							for (int i = 0; i < auxPerfil.size(); i++) {
								String nome = "tipo.pass." + auxPerfil.get(i).getCodigo();
								String forma = (i + 1) + " - " + bundle.getString(nome);
								out.print("<option value =" + (i + 1) + ">" + forma
										+ "</option>");
							}
						%>
					</select>
				</p>
				<p id="btnForm">
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
		<div id="msg">
			<%
				String msg;
				if (session.getAttribute("msg").toString().equals("")) {
					msg = request.getAttribute("msg").toString();
				} else {
					msg = session.getAttribute("msg").toString();
				}
				session.setAttribute("msg", "");
				out.print(bundle.getString(msg));
			%>
		</div>
	</div>
</body>
</html>