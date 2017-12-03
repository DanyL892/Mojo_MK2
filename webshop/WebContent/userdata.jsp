<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="webshop.Adresse" %>
<%@page import="java.sql.ResultSet" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Bucks Money - Mein Konto</title>
		<link href="style.css" rel="stylesheet">
	</head>
	<body>
		<div class="header-cart">
		<%@include file="menu_part.jsp" %>
			<h2>Hallo <%=session.getAttribute("name")%>!</h2>
  			<p>Hier kannst du deine Daten einsehen.</p>
  			<%
    			Adresse adresse = new Adresse();
  				adresse.setId(Integer.parseInt(session.getAttribute("userid").toString()));
    		    ResultSet adresses = adresse.showAdresses();
    			while(adresses.next()) {%>
    				<h1>Adresse</h1>
    				<h1><%=session.getAttribute("userid") %></h1>
							<p><%=adresses.getString(3)%> <%=adresses.getString(4) %></p>
							<%System.out.println(adresses.getString(4)); %>
							<p><%=adresses.getString(5)%></p>
							<p><%=adresses.getString(6)%></p>
    				<%}%>
    	<a href="enter_adress.jsp"><button class="button">Neue Adresse</button></a>
    	<br/>
  		<a class="logout" href="logout">Logout</a>
		</div>
	</body>
</html>