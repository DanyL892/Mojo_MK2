<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="webshop.shop" %>
<%@page import="java.sql.ResultSet" %>

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
  	  	<%@include file="menu_part.jsp" %>
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
  	  <div class="big">
      	<h1>Lorem ipsum dolor</h1>
      	<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
  	  </div>
  	  <div class="content shopping" id="content">
  		<%
  		shop shop = new shop();
  		ResultSet items = shop.getItems();
  		while (items.next()) {
  			String item    = (items.getString(2));
  			String imgname = items.getString(1);
  			String text    = items.getString(3);
  			String price   = items.getString(4);
  		%>
  		
  		<div class="small shop">
  		 	<% if (session.getAttribute("changemode") == null || session.getAttribute("changemode").equals("none")) {%>
  			<form method="post" action="item"><%} %>
  				<h1><%=item %></h1>
  				<input type="hidden" name="item" value="<%=item%>">
  				<img src=" img/<%= imgname %>.jpeg">
  				<input type="hidden" name="image" value="<%=imgname%>.jpeg">
  				<% 
  					if(session.getAttribute("changemode") != null) {
  						if(session.getAttribute("changemode").equals("change")) {%>
  						<form method="post" action="Change">
  							<input type="hidden" name="changeitem" value="<%=item%>">
  							<textarea type="text" class="biginput" name="text"><%=text %></textarea>
  							<button type="submit">Text ändern</button>
  						</form>
  					<% } else {%>
  						<p><%=text %>
  						<input type="hidden" name="text" value="<%=text %>">	
  					<%}} %>
  				<br/>
  				<h3>Preis: <%=price %> Euro</h3>
  				<input type="hidden" name="price" value="<%=price%>">
  				<% if (session.getAttribute("changemode") == null || session.getAttribute("changemode").equals("none")) {%>
  				<button class="button-shop" type="submit">ZUM PRODUKT</button>
  				<%} %>
  			</form>
  		</div>
  		
  	<% }
  %>

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