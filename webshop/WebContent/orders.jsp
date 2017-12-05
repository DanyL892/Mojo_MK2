<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="webshop.order" %>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 <title>Bucks Money - Bestellungen</title>
	 <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed" rel="stylesheet">
	 <meta name="viewport" content="width=device-width, initial-scale=1">
	 <meta charset="utf-8">
	 <link href="style.css" rel="stylesheet">
</head>
<body>
	<div class="header-cart">
		<%@include file="menu_part.jsp" %>
		<h1>Meine Bestellungen</h1>
		<div class="wrapper">
		<%
			order order = new order();
			ResultSet orders = order.getOrders(Integer.parseInt(session.getAttribute("userid").toString()));
			int ordernumber  = Integer.parseInt(orders.getString(6).toString());
			int preisGesamt  = 0;
			%>
				<table>
					<tr>
						<th>Datum</th>
						<th>Status</th>
						<th>Produkt</th>
						<th>Anzahl</th>
						<th>Preis</th>
						<th>Zustand</th>
					</tr>
					<tr>
			<%while(orders.next()) {
				String datum   = orders.getString(4);
				int Status     = Integer.parseInt(orders.getString(5).toString());
				int nummer     = Integer.parseInt(orders.getString(6).toString());
				String produkt = orders.getString(7);
				int anzahl     = Integer.parseInt(orders.getString(8).toString());
				int preis      = Integer.parseInt(orders.getString(9).toString());
				String zustand = orders.getString(10);
				preisGesamt    = preisGesamt + preis;
				String stat    = "";
				if(Status == 1) {
					stat = "In Bearbeitung";
				} else if(Status == 2) {
					stat = "Versandt";
				} else if(Status == 3) {
					stat = "storniert";
				}
				if(ordernumber == nummer) {
			%>
					<tr>
						<td><%=datum%></td>
						<td><%=stat%></td>
						<td><%=produkt%></td>
						<td><%=anzahl%></td>
						<td><%=preis%> Euro</td>
						<td><%=zustand %></td>
					</tr>
			<%} else { 
					ordernumber = nummer;%>
				<form method="post" action="Stornierung">
					<tr><td class="heading">Bestellung <%=ordernumber %></td>
						<input type="hidden" name="orderId" value="<%=ordernumber %>">
						<td><button type="submit" class="button-small">Stornieren</button></td></tr>
					<tr>
				</form>
						<td><%=datum%></td>
						<td><%=stat%></td>
						<td><%=produkt%></td>
						<td><%=anzahl%></td>
						<td><%=preis%> Euro</td>
						<td><%=zustand %></td>
					</tr>
				
			<%} }%></table>
	</div>
	</div>
</body>
</html>