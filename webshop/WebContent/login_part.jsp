<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% if (session.getAttribute("name") == null) { %>
	<form method="post" action="login" id="login">
  	  	<p>Du bist zur Zeit nicht eingeloggt. Bitte melde dich mit deinen Nutzerdaten an</p>
  	  	<input type="text" name="username" value="Username" onclick="this.value=''"><br/>
  	  	<input type="password" name="password" value="Passwort" onclick="this.value''"><br/>
  	  	<button type="submit" value="Einloggen" class="button button-small">Einloggen</button>
  	  </form>
  	  <p class="close">Noch kein Mitglied?</p>
	  <a href="register.jsp"><button class="button">Registrieren</button></a>
	  <%
	  } else { %>
	  <h2>Hallo <%=session.getAttribute("name") %>!</h2>
	  <a href="logout">Logout</a>
	  <%} %>
</body>
</html>