package webshop;

import java.sql.*;
import java.util.*;
import javax.crypto.*;
import javax.servlet.http.HttpServlet;

import java.security.MessageDigest;
import java.sql.DriverManager;

public class Benutzer {
	
	public String register(String name, String pass, String mail) {
		
		boolean success = false;
	    String error = "";
	    String message = "";
		
		//Eingabe prüfen
		if (name == "") {
			error = "Bitte gib einen Nutzernamen ein.";
		} else if (pass == "") {
			error = "Bitte gib ein Passwort ein.";
		} else if (mail == "") {
			error = "Bitte gib eine E-Mail Adresse ein.";
		}
		
		try {
	          Class.forName("com.mysql.jdbc.Driver");
	          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
	          Statement st=con.createStatement();
	          
	          //prüfe ob Username bereits existiert
	          String sql;
	      	  sql="SELECT name FROM users WHERE name='"+name+"'";
	      	  ResultSet rs = null;
	    	  rs=st.executeQuery(sql);
	          if (rs.next()) {
	        	  error = "Ein User mit diesem Namen existiert bereits!";
	          } 
	          
	          //prüfe ob die Mail bereits verwendet wird
	          sql="SELECT mail FROM users WHERE mail='"+mail+"'";
	          ResultSet rs2 = null;
	          rs2=st.executeQuery(sql);
	       	  if (rs2.next()) {
	       		  error = "Diese E-Mail wird bereits verwendet.";
	       	  } 
	          if (error == "") {
	       		  MessageDigest alg = MessageDigest.getInstance("MD5");
	       		  alg.reset();
	       		  alg.update(pass.getBytes());
	       		  byte[] digest = alg.digest();
	       		  StringBuffer hashedpasswd = new StringBuffer();
	       		  String hx;
		       	  for (int m=0; m<digest.length; m++){
		       			hx =  Integer.toHexString(0xFF & digest[m]);
		       			if(hx.length() == 1){hx = "0" + hx;}
		       			hashedpasswd.append(hx);
		       	  }
		       	  pass = hashedpasswd.toString();
		       	  int i=st.executeUpdate("insert into users(name,passwort,mail) values('"+name+"','"+pass+"','"+mail+"')");
	       		  success = true;
	          }
	       }
		
	       catch(Exception e){
	       System.out.print(e);
	       e.printStackTrace();
	       success = false;
	       }
		
			if (error != "") {
				message = error;
			}
			else if (success == true) {
				message = "Du hast dich erfolgreich registriert";
			} else if (success == false) { 
				message = "Es gab einen Fehler bei der Registrierung. Bitte versuche es später erneut.";
		    } 
			
			return message;
	}

	public String login(String username, String pass) {
		
		//Variablen 
		String passwort = "";
		String error    = "";
		String text     = "";
		boolean success = false;
		
		//Eingabe prüfen
		if (username == "") {
			error = "Bitte gib einen Nutzernamen ein.";
		} else if (pass == "") {
			error = "Bitte gib ein Passwort ein.";
		} 
		
		try {
	          Class.forName("com.mysql.jdbc.Driver");
	          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
	          Statement st=con.createStatement();
	          
	          //prüfe ob Username existiert
	          String sql;
	      	  sql="SELECT * FROM users WHERE name='"+username+"'";
	    	  ResultSet rs=st.executeQuery(sql);
	          if (rs.next() == false) {
	        	  error = "Ein User mit diesem Namen existiert nicht";
	          } else {
	        	  MessageDigest alg = MessageDigest.getInstance("MD5");
	       		  alg.reset();
	       		  alg.update(pass.getBytes());
	       		  byte[] digest = alg.digest();
	       		  StringBuffer hashedpasswd = new StringBuffer();
	       		  String hx;
		       	  for (int m=0; m<digest.length; m++){
		       			hx =  Integer.toHexString(0xFF & digest[m]);
		       			if(hx.length() == 1){hx = "0" + hx;}
		       			hashedpasswd.append(hx);
		       	  }
		       	  pass = hashedpasswd.toString();
		       	  
		       	  //Prüfe Passwort auf Gültigkeit
		       	  passwort = rs.getString("passwort");
		       	  if (pass.equals(passwort)) {
		       		  success = true;
		       		  text = "Welcome";
		       		  //sessions setzen
		       	  } 
	          }
	          
	       }
		
	       catch(Exception e){
	       System.out.print(e);
	       e.printStackTrace();
	       success = false;
	       }
		
		if (error != "") {
			text = error;
		}
		
		return text;
		
	}
	
}
