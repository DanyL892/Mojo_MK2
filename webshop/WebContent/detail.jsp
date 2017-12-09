<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	    <title>Bucks Money - Produkt</title>
	    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="utf-8">
        <link href="style.css" rel="stylesheet">
	</head>
	<body>
  		<div class="big detail">
    	<%@include file="menu_part.jsp" %>
    	<form method="post" action="cart">
    		<h1><%=session.getAttribute("item") %></h1>
    		<input type="hidden" name="item" value="<%=session.getAttribute("item")%>">
    		<img src="img/<%=session.getAttribute("image") %>"/>
    		<p><%=session.getAttribute("text") %>
    		<br/>
    		<br/>
    		<label>Zustand 
  				<select name="zustaende"> 
  					<option label="druckfrisch">Druckfrisch</option>
  					<option label="retro">Retro</option>
  					<option label="vintage">Vintage</option>
  				</select>
  			</label>
  			<br/>
  			<h2>Preis: <fmt:formatNumber type = "number" 
       		 			minFractionDigits = "2" value = "<%=session.getAttribute(\"price\") %>"/>  Euro</h2>
  			<input type="hidden" name="price" value="<%=session.getAttribute("price")%>">
  			<button type="submit" class="button-shop">IN DEN WARENKORB</button>
  		</form>
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