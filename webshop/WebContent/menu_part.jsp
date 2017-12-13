<%/** 
 * @author Jessica Buschkamp
 * @version 1.0
 * </br>
 * </br>
 * Description:</br> 
 * This file creates the header menu (used on each jsp)
 */%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="shortcut icon" type="image/x-icon" href="img/bmlogo.png">
</head>
<body>
<div class="menu">
	  <a href="#" class="menu-icon"><hr><hr><hr></a>
      <ul>
        <a href="index.jsp"><li>HOME</li></a>
        <a href="shop.jsp"><li>SHOP</li></a>
        <a href="Konto"><li>
        <% 
        String name = "";
        if(session.getAttribute("name") != null) {
       		name = session.getAttribute("name").toString();
        } else {
        	name = "MEIN KONTO";
        } 
        %>
        <%=name %></li></a><!-- Wenn user eingeloggt, hier den Namen anzeigen -->
        <a href="cart.jsp"><li>EINKAUFSWAGEN</li></a>
        <li><%@include file="searchbar.jsp"%> </li>
      </ul>
    </div>
</body>
</html>