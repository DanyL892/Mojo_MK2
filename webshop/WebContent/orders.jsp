<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="webshop.order" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.*" %>
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
			order order = new order();
			List<order> orderList = order.getOrders(userid);
			int ordernumber  = orderList.get(0).getNummer();
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
			<%Iterator<order> it = orderList.iterator();
	  		for (order currOrder : orderList) {
				String datum   = currOrder.getDate();
				int Status     = currOrder.getStatus();
				int nummer     = currOrder.getNummer();
				String produkt = currOrder.getItem();
				int anzahl     = currOrder.getAnzahl();
				int preis      = currOrder.getPreis();
				String zustand = currOrder.getZustand();
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