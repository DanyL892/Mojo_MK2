<%/** 
 * @author Jessica Buschkamp
 * @version 1.4
 * </br>
 * </br>
 * Description:</br> 
 * This file creates the order overview
 */%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="webshop.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
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
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			List<Order> orderList = Order.getOrders(userid);
			if(orderList.isEmpty()) {%>
				<p>Du hast bisher keine Bestellungen.</p>
			<% } else {
			int ordernumber  = orderList.get(0).getNummer();
			float preisGesamt= 0;
			int first        = 1;
			%>
				<table>
				<thead>
					<tr>
						<th>Datum</th>
						<th>Status</th>
						<th>Produkt</th>
						<th>Anzahl</th>
						<th>Preis</th>
						<th>Zustand</th>
					</tr>
				</thead>
					<tr>
			<%Iterator<Order> it = orderList.iterator();
	  		for (Order currOrder : orderList) {
				String datum   = currOrder.getDate();
				int Status     = currOrder.getStatus();
				int nummer     = currOrder.getNummer();
				String produkt = currOrder.getItem();
				int anzahl     = currOrder.getAnzahl();
				float preis    = currOrder.getPreis();
				String zustand = currOrder.getZustand();
				preisGesamt    = preisGesamt + preis;
				String stat    = "";
				if(Status == 1) {
					stat = "In Bearbeitung";
				} else if(Status == 2) {
					stat = "Versandt";
				} else if(Status == 3) {
					stat = "Storniert";
				}
				if(ordernumber == nummer) {
					if(first == 1) {%>
						<form method="post" action="Stornierung">
						<tr><td class="heading">Bestellung <%=ordernumber %></td>
							<input type="hidden" name="orderId" value="<%=ordernumber %>">
							<td><button type="submit" class="button-small">Stornieren</button></td></tr>
							<tr>
						<td><p class="hidden_values">Datum: </p><%=datum%></td>
						<td><p class="hidden_values">Status: </p><%=stat%></td>
						<td><%=produkt%></td>
						<td><p class="hidden_values">Anzahl: </p><%=anzahl%></td>
						<td><p class="hidden_values">Preis: </p><fmt:formatNumber 
																type = "number" 
       		 													minFractionDigits = "2" 
       		 													value = "<%=preis%>" /> Euro</td>
						<td><p class="hidden_values">Zustand: </p><%=zustand %></td>
					</tr>
					<% first ++; } else {
			%>
					<tr>
						<td><p class="hidden_values">Datum: </p><%=datum%></td>
						<td><p class="hidden_values">Status: </p><%=stat%></td>
						<td><%=produkt%></td>
						<td><p class="hidden_values">Anzahl: </p><%=anzahl%></td>
						<td><p class="hidden_values">Preis: </p><fmt:formatNumber 
																type = "number" 
       		 													minFractionDigits = "2" 
       		 													value = "<%=preis%>" /> Euro</td>
						<td><p class="hidden_values">Zustand: </p><%=zustand %></td>
					</tr>
			<%}} else { 
					ordernumber = nummer;%>
				<form method="post" action="Stornierung">
					<tr><td class="heading">Bestellung <%=ordernumber %></td>
						<input type="hidden" name="orderId" value="<%=ordernumber %>">
						<td><button type="submit" class="button-small">Stornieren</button></td></tr>
					<tr>
				</form>
						<td><p class="hidden_values">Datum: </p><%=datum%></td>
						<td><p class="hidden_values">Status: </p><%=stat%></td>
						<td><%=produkt%></td>
						<td><p class="hidden_values">Anzahl: </p><%=anzahl%></td>
						<td><p class="hidden_values">Preis: </p><fmt:formatNumber 
																type = "number" 
       		 													minFractionDigits = "2" 
       		 													value = "<%=preis%>" /> Euro</td>
						<td><p class="hidden_values">Zustand: </p><%=zustand %></td>
					</tr>
				
			<%}}}%></table>
	</div>
	</div>
</body>
</html>