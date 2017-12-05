<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="webshop.Adresse" %>
<%@page import="java.sql.ResultSet" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Bucks Money - Meine Daten �ndern</title>
		<link href="style.css" rel="stylesheet">
	</head>
	<body>
		<div class="header-cart">
		<%@include file="menu_part.jsp" %>
			<h2>Hallo <%=session.getAttribute("name")%>!</h2>
  			<p>Hier kannst du deine Daten einsehen und �ndern.</p>
  			
  			  <%Adresse adresse = new Adresse();
  				adresse.setId(Integer.parseInt(session.getAttribute("userid").toString()));
    			adresse.getUserAdress();%>
  				<%
  					if(adresse.getHasAdress() == true) {
  				%>
	  				<p><%=adresse.getStreet()%> <%=adresse.getNumber() %></p>
					<p><%=adresse.getZip()%></p>
					<p><%=adresse.getCity()%></p>
					
				<% } else { %>	
    				<a href="enter_adress.jsp"><button class="button">Neue Adresse anlegen</button></a>
    			<%} %>
    			<a href="userdata_change.jsp"><button>Meine Daten �ndern</button></a>
    			<a href="orders.jsp"><button>Meine Bestellungen</button></a>
    	<br/>
  		<a class="logout" href="logout">Logout</a>
		</div>
	</body>
</html>