<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="style_home.css" rel="stylesheet"> 
<title>Dein Konto</title>
</head>
<body>
<div class="header">
	<%@include file="menu_part.jsp"%>
	<% if (session.getAttribute("name") == null) { %>
	<%@include file="login_part.jsp"%>
	<%
	 } else { %>
	 	<h2>Hallo <%=session.getAttribute("name") %>!</h2>
	 
	 <p>Hier kannst du bald deine Daten einsehen.</p>
	 <a href="enter_adress.jsp"><button class="button">Neue Adresse</button></a>
	 	<div class="keep-separated"></div>
		<div><a href="logout">Logout</a></div>
	<%} %>
</div>

</body>
</html>