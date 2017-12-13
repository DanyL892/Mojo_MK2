<%/** 
 * @author Jessica Buschkamp
 * @version 1.0
 * </br>
 * </br>
 * Description:</br> 
 * Error page, displays custom errors received from HTTPrequest
 */%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="style.css" rel="stylesheet">
		<title>Fehlermeldung</title>
	</head>
	<body>
		<div class="header header-bug">
			<h1>Fehlermeldung</h1>
			<h2>Leider gab es einen Bug...</h2>
			<p><%=session.getAttribute("error") %></p>
		</div>
	</body>
</html>