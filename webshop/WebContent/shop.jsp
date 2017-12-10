<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="webshop.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		  <title>Bucks Money</title>
		  <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed" rel="stylesheet">
		  <meta name="viewport" content="width=device-width, initial-scale=1">
		  <meta charset="utf-8">
		  <link href="style.css" rel="stylesheet">
	</head>
	<body>
  	  <div class="header header-shop">
		<img src="img/bmlogo.png" class="small_logo">
  	  	<%@include file="menu_part.jsp" %>
  	  	<img id="logo_shop" src="img/bmlogo.png">
      	<h1>Unser Shop</h1>
      	<hr/>
      	<a href="#content"><button class="button">JETZT EINKAUFEN</button></a>
      	<% 
  			if(session.getAttribute("admin") != null) {%>
  				<form method="post" action="ChangeMode">
  					<input type="hidden" name="changemode" value="<%=session.getAttribute("changemode")%>">
  					<button type="submit">TOGGLE ÄNDERUNGSMODUS</button>
  				</form>
  			<%} %>
  	  </div>
  	  <div class="big empty">
      	<h1>Unsere Stylevielfalt</h1>
      	<p>Durch unsere unterschiedlichsten Angebote finden all unsere Kunden genau das, was sie brauchen.
      	   Unsere Mitarbeiter behandeln das Geld stets liebevoll und voller Hingabe - und das merkt man auch
      	   am Endprodukt.</p>
      	   <%if(session.getAttribute("admin") != null) {%>
      	   	<a href="upload.jsp"><button>Neues Produkt anlegen</button></a>
      	   <%} %>
  	  </div>

  	  <div class="content shopping" id="content">
  		<%
  		Shop shop = new Shop();
  		List<Item> items = shop.getItems();
  		Iterator<Item> it = items.iterator();
  		for (Item current : items) {
  		    String item = current.getItem();
  		  	String imgname = current.getImage();
  		  	String text = current.getText();
  		  	String price = current.getPrice();
  		%>
  		
  		<div class="small shop search-results shop-items">
  		 	<% if (session.getAttribute("changemode") == null || session.getAttribute("changemode").equals("none")) {%>
  			<form method="post" action="Item"><%} %>
  				<input type="hidden" name="item" value="<%=item%>">
  				<img src=" img/<%= imgname %>.jpeg">
  				<h1 class="different"><%=item %></h1>
  				<input type="hidden" name="image" value="<%=imgname%>.jpeg">
  				<p><%=text %></p>
  				<input type="hidden" name="text" value="<%=text %>">
  				<% 
  					if(session.getAttribute("changemode") != null) {
  						if(session.getAttribute("changemode").equals("change")) {%>
  						<form method="post" action="Change">
  							<input type="hidden" name="changeitem" value="<%=item%>">
  							<textarea type="text" class="biginput" name="text"><%=text %></textarea>
  							<button type="submit">Text ändern</button>
  						</form>
  					<% } %>
  					<%} %>
  				<br/>
  				<h3>Preis: <fmt:formatNumber type = "number" 
       		 	minFractionDigits = "2" value = "<%=price%>"/> Euro</h3>
  				<input type="hidden" name="price" value="<%=price%>">
  				<% if (session.getAttribute("changemode") == null || session.getAttribute("changemode").equals("none")) {%>
  				<button class="button-shop" type="submit">ZUM PRODUKT</button>
  				<%} %>
  			</form>
  		</div>
  		
  	<% }%>
  </div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script>
// Select all links with hashes
$('a[href*="#"]')
  // Remove links that don't actually link to anything
  .not('[href="#"]')
  .not('[href="#0"]')
  .click(function(event) {
    // On-page links
    if (
      location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '')
      &&
      location.hostname == this.hostname
    ) {
      // Figure out element to scroll to
      var target = $(this.hash);
      target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
      // Does a scroll target exist?
      if (target.length) {
        // Only prevent default if animation is actually gonna happen
        event.preventDefault();
        $('html, body').animate({
          scrollTop: target.offset().top
        }, 1000, function() {
          // Callback after animation
          // Must change focus!
          var $target = $(target);
          $target.focus();
          if ($target.is(":focus")) { // Checking if the target was focused
            return false;
          } else {
            $target.attr('tabindex','-1'); // Adding tabindex for elements not focusable
            $target.focus(); // Set focus again
          };
        });
      }
    }
  });

</script>
</body>
</html>