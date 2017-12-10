<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="webshop.Adresse" %>
<%@ page import="webshop.User" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Bucks Money - Neues Produkt</title>
		<link href="style.css" rel="stylesheet">
	</head>
	<body>
		<div class="header-cart">
		<%@include file="menu_part.jsp" %>

			<div class="change-data">
	      	<h1>Neues Produkt anlegen</h1>
	      	<form class="dark" method="post" action="Upload" id="Userdata">
		      	 <label for="produktname">Produktname</label>
		   		 <input type="text" name="produktname"><br/>  
		   		 <label for="produkttext">Produkttext</label>
		   		 <input type="text" name="produkttext"><br/>
		   		 <label for="preis">Preis</label>
		   		 <input type="text" name="preis"><br/> 
		   		 <!--  <label for="file">Datei</label>
		   		 <input type="file" name="file"  > <br/>  -->
		   		 <button type="submit" class="button">SPEICHERN</button>
	     	</form>  </div>
	  	</div>
	</body>
</html>