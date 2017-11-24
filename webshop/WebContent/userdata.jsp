<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Bucks Money - Meine Daten</title>
	</head>
	<body>
  		<h2>Hallo <%=session.getAttribute("name") %>!</h2>
  		<p>Hier kannst du bald deine Daten einsehen.</p>
  		 
  		 <div class="content" id="content">
    		<div class="small">
      			<h1>Waschen</h1>
      			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
    		</div>
   		</div>
  		 
 		<a href="enter_adress.jsp"><button class="button">Neue Adresse</button></a>
 		<div class="keep-separated"></div>
  		<a href="logout">Logout</a>
	</body>
</html>