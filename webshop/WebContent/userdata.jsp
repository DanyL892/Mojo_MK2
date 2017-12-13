<%/** 
 * @author Daniel Friedrichs
 * @version 1.3
 * </br>
 * </br>
 * Description:</br> 
 * This file creates the user interface for logged-in users.
 * It offers different sets of buttons depending on the presence of an user address
 */%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="webshop.Adresse" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
  			<p>Hier kannst du deine Daten einsehen und ändern.</p>
  			
  			  <%Adresse adresse = new Adresse();
  			  	int userid = Integer.parseInt(session.getAttribute("userid").toString());
    			adresse.getUserAdress(userid);%>
  				<%
  				if(!adresse.hasAnAdress(userid)) { %>	
    				<a href="enter_adress.jsp"><button class="button">Neue Adresse anlegen</button></a>
    			<%} 
    			%>
    			<a href="userdata_change.jsp"><button>Meine Daten ändern</button></a>
    			<a href="orders.jsp"><button>Meine Bestellungen</button></a>
    	<br/>
  		<a class="logout" href="Logout">Logout</a>
		</div>
	</body>
</html>