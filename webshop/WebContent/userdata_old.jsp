<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="webshop.getAdresses" %>
<%@page import="java.sql.ResultSet" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Bucks Money - Meine Daten</title>
		<link href="style.css" rel="stylesheet">
	</head>
	<body>
  		<h2>Hallo <%=session.getAttribute("name")%>!</h2>
  		<p>Hier kannst du deine Daten einsehen.</p>
  		 
  		 <div class="content" id="content">
    		<div class="small">
    		<%
    			getAdresses getAdresses = new getAdresses();
    		    ResultSet adresses = getAdresses.showAdresses();
    		    if(adresses.next() == false) {%>
    		    	<h1>Du hast zur Zeit keine Adressen gepflegt.</h1>
    		    <% } else {
    				while(adresses.next()) {%>
    					<h1>Adresse</h1>
			      			<ul style="list-style-type:none">
							  <li><%=adresses.getString(3)%> <%=adresses.getString(4) %></li>
							  <li><%=adresses.getString(5)%></li>
							  <li><%=adresses.getString(6)%></li>
							</ul>
    				<%}}%>
    		</div>
   		</div>
  		 
 		<a href="enter_adress.jsp"><button class="button">Neue Adresse</button></a>
 		<div class="keep-separated"></div>
  		<a href="logout">Logout</a>
	</body>
</html>