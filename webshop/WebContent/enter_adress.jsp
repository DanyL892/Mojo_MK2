<%/** 
 * @author Daniel Friedrichs
 * @version 1.2
 * </br>
 * </br>
 * Description:</br> 
 * This file creates the interface used for registering new user addresses
 */%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="style.css" rel="stylesheet"> 
		<title>Adressverwaltung</title>
	</head>
	<body>
	  	<div class="header closer">
	  	<%@include file="menu_part.jsp" %>
	      	<h1>Neue Adresse eingeben</h1>
	      	<form class="dark" method="post" action="Adresse" id="Adresse">
	   		 <label for="add_street">Straßenname</label>
	   		 <input type="text" name="add_street" value="Straße" onclick="this.value=''"><br/>  
	   		 <label for="add_housenumber">Hausnummer</label>
	   		 <input type="text" name="add_housenumber"  value="Hausnummer" onclick="this.value=''"><br/>  
	   		 <label for="add_postalcode">Postleitzahl</label>
	   		 <input type="text" name="add_postalcode"  value="Postleitzahl" onclick="this.value=''"><br/>  
	   		 <label for="add_city">Stadt</label>
	   		 <input type="text" name="add_city"  value="Stadt" onclick="this.value=''"><br/>  
	   		 <input type="hidden" name="userid" value="<%=session.getAttribute("userid") %>">
	   		 <input type="hidden" name="button-saveAddress" />
	   		 <button type="submit" class="button" value="Adresse speichern">Adresse speichern</button>
	     	</form>  
	  	</div>
	</body>
</html>