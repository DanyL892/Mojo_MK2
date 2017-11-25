<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Bucks Money - Meine Daten</title>
		<link href="style.css" rel="stylesheet">
	</head>
	<body>
  		<h2>Hallo <%=session.getAttribute("name")%>!</h2>
  		<p>Hier kannst du bald deine Daten einsehen.</p>
  		 
  		 <div class="content" id="content">
    		<div class="small">
      			<h1>Adresse 1</h1>
      			<ul style="list-style-type:none">
				  <li><%=request.getAttribute("street")%> <%=request.getAttribute("housenumber")%></li>
				  <li><%=request.getAttribute("postalcode")%></li>
				  <li><%=request.getAttribute("city")%></li>
				</ul>
    		</div>
   		</div>
  		 
 		<a href="enter_adress.jsp"><button class="button">Neue Adresse</button></a>
 		<div class="keep-separated"></div>
  		<a href="logout">Logout</a>
	</body>
</html>