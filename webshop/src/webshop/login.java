package webshop;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {  
        super();
        // TODO Auto-generated constructor stub
    } 
      //...
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
		            throws ServletException, IOException {  
    	
    	//variablen ....
    	String passwort = "";
    	String error    = "";
    	String text     = "";
    	boolean success = false;
    			
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter();  
		request.getRequestDispatcher("home.jsp").include(request, response);  
		  
		String name     = request.getParameter("username");  
		String password = request.getParameter("password");  
		
		//Eingabe prüfen
		if (name == "") {
			error = "Bitte gib einen Nutzernamen ein.";
		} else if (password == "") {
			error = "Bitte gib ein Passwort ein.";
		} 
		
		try {
	          Class.forName("com.mysql.jdbc.Driver");
	          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
	          Statement st=con.createStatement();
	          
	          //prüfe ob Username existiert
	          String sql;
	      	  sql="SELECT * FROM users WHERE name='"+name+"'";
	    	  ResultSet rs=st.executeQuery(sql);
	          if (rs.next() == false) {
	        	  error = "Ein User mit diesem Namen existiert nicht";
	          } else {
	        	  MessageDigest alg = MessageDigest.getInstance("MD5");
	       		  alg.reset();
	       		  alg.update(password.getBytes());
	       		  byte[] digest = alg.digest();
	       		  StringBuffer hashedpasswd = new StringBuffer();
	       		  String hx;
		       	  for (int m=0; m<digest.length; m++){
		       			hx =  Integer.toHexString(0xFF & digest[m]);
		       			if(hx.length() == 1){hx = "0" + hx;}
		       			hashedpasswd.append(hx);
		       	  }
		       	  password = hashedpasswd.toString();
		       	  
		       	  //Prüfe Passwort auf Gültigkeit
		       	  passwort = rs.getString("passwort");
		       	  if (password.equals(passwort)) {
		       		  success = true;
		       		  text = "Welcome";
		       		  //sessions setzen
		       		  HttpSession session=request.getSession();  
		       		  session.setAttribute("name",name);  
		       		  out.println("success");
		       	  }  
		       	  else {  
		       		  out.print("Sorry, username or password error!");  
		       		  request.getRequestDispatcher("error.jsp").include(request, response);  
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
		       	out.close();  
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


}
