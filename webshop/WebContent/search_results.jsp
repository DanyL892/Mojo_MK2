<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="webshop.shop" %>
<%@page import="java.sql.ResultSet" %>
<%@ page import="java.util.*" %>
<%@ page import="webshop.item" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		  <title>Suchergebnisse</title>
		  <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed" rel="stylesheet">
		  <meta name="viewport" content="width=device-width, initial-scale=1">
		  <meta charset="utf-8">
		  <link href="style.css" rel="stylesheet">
</head>
<body>

<div class="content shopping" id="content">
<%@include file="menu_part.jsp" %>
  		<%
  		if (request.getAttribute("message")!= ""){%>
  			<h1><%=request.getAttribute("message")%></h1>
  		<%}
  		else{
  		shop shop = new shop();
  		ArrayList<item> items = (ArrayList<item>) request.getAttribute("results");
  		Iterator<item> it = items.iterator();
  		for (item current : items) {
  		    String item = current.getItem();
  		  	String imgname = current.getImage();
  		  	String text = current.getText();
  		  	String price = current.getPrice();
  		%>
  		
  		<div class="small shop">
  			<form method="post" action="item">
  				<h1><%=item %></h1>
  				<input type="hidden" name="item" value="<%=item%>">
  				<img src=" img/<%= imgname %>.jpeg">
  				<input type="hidden" name="image" value="<%=imgname%>.jpeg">
  				<p><%=text %></p>
  				<br/>
  				<h3>Preis: <fmt:formatNumber type = "number" 
       		 	minFractionDigits = "2" value = "<%=price%>"/> Euro</h3>
  				<input type="hidden" name="price" value="<%=price%>">
  			</form>
  		</div>
  		
  	<% }
  	}%>
  </div>

</body>
</html>