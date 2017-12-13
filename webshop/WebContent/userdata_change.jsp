<%/** 
 * @author Daniel Friedrichs
 * @version 1.2
 * </br>
 * </br>
 * Description:</br> 
 * This file creates the interface used for userdata changes.</br>
 * Displays current user data and offers forms to change those.
 */%>
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
			<div class="change-data">
	      	<h1>Ändere hier deine persönlichen Daten</h1>
	      	<form class="dark" method="post" action="User" id="Userdata">
	      	 <label for="chg_name">Neuer Name</label>
	   		 <input type="text" name="chg_name" value="<%=currUser.getName()%>" onclick="this.value='<%=currUser.getName()%>'"><br/>  
	   		 <label for="chg_password">Neues Passwort</label>
	   		 <input type="password" name="chg_passwort"  value="*****" onclick="this.value=''"><br/>
	   		 <label for="chg_password2">Neues Passwort bestätigen</label>
	   		 <input type="password" name="chg_passwort2"  value="*****" onclick="this.value=''"><br/>   
	   		 <label for="chg_email">Neue E-Mail Adresse</label>
	   		 <input type="text" name="chg_email"  value="<%=currUser.getEmail()%>" onclick="this.value='<%=currUser.getEmail()%>'"><br/>  
	   		 <input type="hidden" name="button-changeUserdata" />
	   		 <input type="hidden" name="userid" value="<%=userid%>">
	   		 <button type="submit" class="button" value="Neue Daten speichern">Daten ändern</button>
	     	</form>  </div>

	      	<%
	      	Adresse adresse = new Adresse(); 
  			if(adresse.hasAnAdress(userid) == true) {
  				adresse.getUserAdress(userid);
  				%>
  				<div class="change-data">
	      	<h1>Ändere hier deine Adresse</h1>
	      	<form class="dark" method="post" action="Adresse" id="Adresse">
	      	 <label for="chg_street">Neue Straße</label>
	   		 <input type="text" name="chg_street" value="<%=adresse.getStreet()%>" onclick="this.value='<%=adresse.getStreet()%>'"><br/>  
	   		 <label for="chg_housenumber">Neue Hausnummer</label>
	   		 <input type="text" name="chg_housenumber"  value="<%=adresse.getNumber()%>" onclick="this.value='<%=adresse.getNumber()%>'"><br/>  
	   		 <label for="chg_postalcode">Neue Postleitzahl</label>
	   		 <input type="text" name="chg_postalcode"  value="<%=adresse.getZip()%>" onclick="this.value='<%=adresse.getZip()%>'"><br/>  
	   		 <label for="chg_city">Neue Stadt</label>
	   		 <input type="text" name="chg_city"  value="<%=adresse.getCity()%>" onclick="this.value='<%=adresse.getCity()%>'"><br/>  
	   		 <input type="hidden" name="button-changeAddress" />
	   		 <input type="hidden" name="userid" value="<%=userid%>">
	   		 <button type="submit" class="button" value="Adresse speichern">Adresse ändern</button>
	     	</form> </div>
	     	<%}%> 
	  	</div>
	</body>
</html>