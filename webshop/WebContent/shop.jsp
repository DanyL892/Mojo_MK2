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
  <link href="style_shop.css" rel="stylesheet">
</head>
<body>

  <div class="header">
  <%@include file="menu_part.jsp" %>
      <h1>Unser Shop</h1>
      <hr/>
      <a href="#content"><button class="button">JETZT EINKAUFEN</button></a>
  </div>
  <div class="col-md-12">
    <h1>Lorem ipsum dolor</h1>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
  </div>
  <div class="content" id="content">
  <%
  	shop shop = new shop();
  	ResultSet items = shop.getItems();
  	while (items.next()) {
  		String item = (items.getString(2));
  		String imgname = items.getString(1);
  		%>
  		
  		<div class="col-md-4">
  		<h1><%=item %></h1>
  		<img src=" <%= imgname %>.jpeg">
  		<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
  		<br/>
  		<button class="button-shop">ZUM PRODUKT</button>
  		</div>
  		
  	<% }
  %>
    <div class="col-md-4" style="visibility: hidden;">
      <h1>5-Euro-Schein</h1>
      <img src="https://i0.wp.com/picjumbo.com/wp-content/uploads/IMG_3750.jpg?w=2210&quality=50">
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
      <div class="parameters">
        <span class="inline"><p>Menge: </p><p class="amount">1</p><div class="more">+</div><div class="less">-</div>
      </div>
      <button class="button-shop">JETZT KAUFEN</button>
    </div>
  </div>
  <div class="footer">
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

  $(document).ready(function() {
    $("html").hide().fadeIn(2000);
  });
</script>
</body>
</html>