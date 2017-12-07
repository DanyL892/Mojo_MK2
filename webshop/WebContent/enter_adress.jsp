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
	  	<div class="header">
	  	<%@include file="menu_part.jsp" %>
	      	<h1>Neue Adresse eingeben</h1>
	      	<form class="dark" method="post" action="Adresse" id="Adresse">
	   		 <input type="text" name="add_street" value="Stra�e" onclick="this.value=''"><br/>  
	   		 <input type="text" name="add_housenumber"  value="Hausnummer" onclick="this.value=''"><br/>  
	   		 <input type="text" name="add_postalcode"  value="Postleitzahl" onclick="this.value=''"><br/>  
	   		 <input type="text" name="add_city"  value="Stadt" onclick="this.value=''"><br/>  
	   		 <input type="hidden" name="userid" value="<%=session.getAttribute("userid") %>">
	   		 <input type="hidden" name="button-saveAddress" />
	   		 <button type="submit" class="button" value="Adresse speichern">Adresse speichern</button>
	     	</form>  
	  	</div>
	</body>
</html>