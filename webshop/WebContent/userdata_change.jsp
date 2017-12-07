<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="webshop.Adresse" %>
<%@ page import="webshop.User" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Bucks Money - Meine Daten ändern</title>
		<link href="style.css" rel="stylesheet">
	</head>
	<body>
		<div class="header-cart">
		<%@include file="menu_part.jsp" %>


			<%
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			User currUser = new User();
			currUser = currUser.receiveDataset(userid);
			%>
	      	<h1>Ändere hier deine persönlichen Daten</h1>
	      	<form class="dark" method="post" action="" id="Adresse">
	   		 <input type="text" name="Name" value=<%=currUser.getName()%> onclick="this.value=''"><br/>  
	   		 <input type="text" name="Passwort"  value="" onclick="this.value=''"><br/>  
	   		 <input type="text" name="E-Mail"  value=<%=currUser.getEmail()%> onclick="this.value=''"><br/>  
	   		 <button type="submit" class="button" value="Neue Daten speichern">Daten ändern</button>
	     	</form>  

	      	<%
	      	Adresse adresse = new Adresse(); 
  			if(adresse.hasAnAdress(userid) == true) {
  				adresse.getUserAdress(userid);
  				%>
	      	<h1>Ändere hier deine Adresse</h1>
	      	<form class="dark" method="post" action="Adresse" id="Adresse">
	   		 <input type="text" name="street" value=<%=adresse.getStreet()%> onclick="this.value=''"><br/>  
	   		 <input type="text" name="housenumber"  value=<%=adresse.getNumber()%> onclick="this.value=''"><br/>  
	   		 <input type="text" name="postalcode"  value=<%=adresse.getZip()%> onclick="this.value=''"><br/>  
	   		 <input type="text" name="city"  value=<%=adresse.getCity()%> onclick="this.value=''"><br/>  
	   		 <input type="hidden" name="button-changeAddress" />
	   		 <button type="submit" class="button" value="Adresse speichern">Adresse ändern</button>
	     	</form> 
	     	<%}%> 
	  	</div>
	</body>
</html>