package webshop;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean success = false;
	    String error    = "";
	    String message  = "";
	    String name     = request.getParameter("username");
	    String mail     = request.getParameter("email");
	    String pass     = request.getParameter("password"); 
		
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
	          /* "jdbc:mysql://localhost:3306/webshop" */
	          Connection con = DriverManager.getConnection("jdbc:mysql://192.168.64.2/webshop", "root", "");
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
	      	      request.getRequestDispatcher("index.jsp").include(request, response);
	          }
	       }
		
	       catch(Exception e){
	       System.out.print(e);
	       e.printStackTrace();
	       success = false;
	       }
		
			if (error != "") {
				message = error;
	       		request.getRequestDispatcher("error.jsp").include(request, response);  
			}
			else if (success == true) {
				message = "Du hast dich erfolgreich registriert";
			} else if (success == false) { 
				message = "Es gab einen Fehler bei der Registrierung. Bitte versuche es später erneut.";
	       		request.getRequestDispatcher("error.jsp").include(request, response);  
		    } 
			
	}

}