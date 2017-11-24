<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="webshop.*" %>
<%@page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Bucks Money - Einkaufswagen</title>
		<link href="style.css" rel="stylesheet">
	</head>
	<body>
		<div class="header-cart">
		<%@include file="menu_part.jsp" %>
		<h1>Dein Warenkorb</h1>
		<%
			float price = 0;
			java.util.List<ShoppingItem> itemList = (java.util.List<ShoppingItem>)request.getSession().getAttribute("items");
			if(itemList == null) {%>
				<h2>Du hast zur Zeit keine Artikel im Warenkorb.</h2>
			<%} else {
			int anzahl = 1;
			String item = "";
	
			for(int i=0; i<itemList.size(); i++) {
				ShoppingItem shopItem = itemList.get(i);
				price = price + shopItem.getPrice();%>
				<div class="items">
					<form method="get" action="cart">
						<p><%=shopItem.getItem() %></p>
						<input type="hidden" name="shopItem" value="<%=shopItem.getItem()%>">
						<p>Einzelpreis: <%=shopItem.getPrice() %> Euro</p>
						<p>Zustand: <%=shopItem.getZustand() %></p>
						<input type="hidden" name="shopZustand" value="<%=shopItem.getZustand()%>">
						<p>Anzahl: <%=shopItem.getAnzahl() %></p>
						<button class="button-shop" type="submit">ENTFERNEN</button>
					</form>
				</div>
				<%}}%>
				<h2 class="gesamt" style="clear: both;">Gesamtpreis: <%=price %> Euro</h2>
		</div>
	</body>
</html>