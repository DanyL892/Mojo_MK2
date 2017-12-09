<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>

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

  		<div class="header">
			<img src="img/bmlogo.png" class="small_logo">
    		<%@include file="menu_part.jsp"%>
    		<img id="logo" src="img/bmlogo.png">
      		<h1>Bucks Money</h1>
      		<hr/>
      		<h2>Get more Bucks for your Money</h2>
      		<a href="#content"><button>MORE</button></a>
  		</div>
  		<div class="content" id="content">
    		<div class="small">
      			<img src="img/fresh.jpeg">
      			<h1>Druckfrisch</h1>
      			<p>Wer kennt es nicht? Das Dinner im Restaurant, der Besuch des örtlichen Theaters oder auch
      			   die Übergabe eines Geldgeschenkes - häufig sehen die eigenen Geldscheine einfach
      			   abgenutzt und unästhetisch aus. Die Banknoten unserer "Druckfrisch"-Serie sind genau das 
      			   Richtige, um solcheunangenehmen Situationen zu vermeiden. Mit einem Exemplar dieser Serie 
      			   sind Sie immer bestens vorbereitet.</p>
      			</div>
    		<div class="small">
      			<img src="img/used.jpeg">
      			<h1>Everyday</h1>
      			<p>Es muss nicht immer extravagant sein. Häufig sind die einfachen Dinge zielführend. Das 
      			   Gleiche gilt auch für unsere Gelscheine der "Everday"-Reihe. Stets zweckmäßig, nie unpassend
      			   und garantiert immer eine gute Wahl.</p><br/>
      			</div>
    		<div class="small">
      			<img src="img/vintage.jpeg">
     		    <h1>Vintage</h1>
     		    <p>Für echte Sammler und Begeisterte der ersten Stunde: Unsere Vintagekollektion! Die
     		       Sammlung beherbergt Scheine der ersten Stunde sowie Banknoten, denen der Zahn der 
     		       Zeit ordentlich zugesetzt hat. Und das darf man auch gerne sehen.</p><br/>
      		</div>
    		<div class="big">
      			<h1>Unsere patentierte Wasch- und Bügeltechnik (refurbished bucks)</h1>
      			<p>Ein unschöner Geldschein kann häufig die Wirkung des Portemonnaies und dessen Inhabers
      			   stark beeinträchtigen. Wir von Bucks Money wollen das verhindern. Mit der Auswahl an 
      			   Geldscheinen aus unseren Kollektionen ist für jede Situation die passende Banknote
      			   nur einen Klick entfernt.</p>
      			<a href="shop.jsp"><button>ZUM SHOP</button></a>
  			</div>
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
			    $("html").hide().fadeIn(1000);
			  });
		</script>
	</body>
</html>