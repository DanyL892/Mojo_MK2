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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class register
 * This servlet class is used to register a new user to the platform.
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
		//register
		boolean success = false;
	    String error    = "";
	    String name     = request.getParameter("username");
	    String mail     = request.getParameter("email");
	    String pass     = request.getParameter("password"); 
	    String mailRegex = "[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{2,}";		
		
		//check user input
		if (name == "") {
			error = "Bitte gib einen Nutzernamen ein.";
		} else if (pass == "") {
			error = "Bitte gib ein Passwort ein.";
		} else if (mail == "" || mail == "Email") {
			error = "Bitte gib eine E-Mail Adresse ein.";
		} else if (mail.matches(mailRegex) == false) {
			error = "Bitte gib eine gültige E-Mail Adresse ein.";	
		}
		if (error == "") {
			//no errors occured, check existing input on database
			try {
				Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
	            Statement st=con.createStatement();
	          
	            //check for preexisting username
	            String sql;
	      	  	sql="SELECT name FROM users WHERE name='"+name+"'";
	      	  	ResultSet rs = null;
	      	  	rs = st.executeQuery(sql);
	      	  	if (rs.next()) {
	        	  error = "Ein User mit diesem Namen existiert bereits!";
	      	  	} 
	          
	      	  	//check for preexisting mail
	      	  	sql="SELECT mail FROM users WHERE mail='"+mail+"'";
	      	  	ResultSet rs2 = null;
	      	  	rs2 = st.executeQuery(sql);
	      	  	if (rs2.next()) {
	       		  error = "Diese E-Mail wird bereits verwendet.";
	       		  System.out.println("mail falsch");
	      	  	}
	      	  	
	      	  	if (error == "") {
	       		  //no errors occured, attempt registration
	      	  	  //hash password for safety purposes
	      	  	  MessageDigest alg = MessageDigest.getInstance("MD5");
	       		  alg.reset();
	       		  alg.update(pass.getBytes());
	       		  byte[] digest = alg.digest();
	       		  StringBuffer hashedpasswd = new StringBuffer();
	       		  String hx;
		       	  for (int m=0; m<digest.length; m++) {
		       		  hx =  Integer.toHexString(0xFF & digest[m]);
		       		  if(hx.length() == 1){hx = "0" + hx;}
		       		  hashedpasswd.append(hx);
		       	  }
		       	  pass = hashedpasswd.toString();
		       	  st.executeUpdate("INSERT INTO users(name,passwort,mail) VALUES('"+name+"','"+pass+"','"+mail+"')");
	       		  success = true;
	       		  //registration successful, lead to index.jsp
	      	      request.getRequestDispatcher("index.jsp").include(request, response);
	          }
	       }
		
	       catch(Exception e) {
	    	   System.out.print(e);
	    	   e.printStackTrace();
	    	   success = false;
	       }
			
			if (success == false) {
				//check for left errors
				error = "Es gab einen Fehler bei der Registrierung. Bitte versuche es später erneut.";
			}
		}
			
		//check if errors occured
		if (error != "") {
			//set error session variable and lead to error page
			HttpSession session=request.getSession();  
		   	session.setAttribute("error",error); 
			request.getRequestDispatcher("error.jsp").include(request, response); 
		}
	}

}
