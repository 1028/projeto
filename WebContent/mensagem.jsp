<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Locale" import="java.util.ResourceBundle"%>
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
<title>Insert title here</title>
</head>
<body>

<script>

</script>


		<%
			String msg = bundle.getString("mensagem.sobre");

			out.print(msg);
		%>
	
</body>
</html>