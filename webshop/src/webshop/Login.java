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

import org.apache.commons.dbutils.DbUtils;

/**
 * Servlet implementation class Login
 * This servlet class is used to log in to 
 * an excisting account on the platform
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {  
        super();
    } 

    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {      	
    	Connection con 	= null;
		Statement st 	= null;
		ResultSet rs 	= null;
    	
		String name;
		String password;
    	String passwort = "";
    	String error    = ""; 
		String userid   = "";
		
		if (request.getParameter("button-login")!= null) {
			name     = request.getParameter("username");
			password = request.getParameter("password");
        } 
		else {
			name     = (String)request.getAttribute("username");
			password = (String)request.getAttribute("password");
        }
		
		
    			
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter();     
		
		//check input fields
		if (name == "") {
			error = "Bitte gib einen Nutzernamen ein.";
		} else if (password == "") {
			error = "Bitte gib ein Passwort ein.";
		} 
		
		if (error == "") {
			
			try {
				//Login attempt
				Class.forName("com.mysql.jdbc.Driver");
		        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
		        st = con.createStatement();
		          
		        //check if username exists in the database
		        rs=st.executeQuery("SELECT * FROM users WHERE name='"+name+"'");
		    	
		        if (rs.next() == false) {
		        	error = "Ein User mit diesem Namen existiert nicht";
		        } else {
		        	//hash input password and validate from database
		        	MessageDigest alg = MessageDigest.getInstance("MD5");
		       		alg.reset();
		       		alg.update(password.getBytes());
		       		byte[] digest = alg.digest();
		       		StringBuffer hashedpasswd = new StringBuffer();
		       		String hx;
			       	for (int m=0; m<digest.length; m++) {
			       		hx =  Integer.toHexString(0xFF & digest[m]);
			       		if(hx.length() == 1) {
			       			hx = "0" + hx;
			       		}
			       		hashedpasswd.append(hx);
			       	}
			       	password = hashedpasswd.toString();
			       	  
			       	//validate password
			       	passwort = rs.getString("passwort");
			       	if (password.equals(passwort)) {
			       		//set session variables
			       		HttpSession session=request.getSession();  
			       		session.setAttribute("name",name);
			       		userid = rs.getString(1);
			       		System.out.println(rs.getString(1));
			       		session.setAttribute("userid", userid);
			       		//check for admin
			       		if (name.equals("admin")) {
			       			session.setAttribute("admin", true);
			       		}
			       		//load index.jsp
			       		request.getRequestDispatcher("/Konto").include(request, response);
			       	}  
			       	else {
			       		//wrong password
			       		  error = "Das eingegebene Passwort ist falsch.";
			       	  }  
		          }
			}
			catch(Exception e){
				System.out.print(e);
			     e.printStackTrace();
			     error = "Es gab einen Fehler beim Login. Bitte versuche es später erneut.";
			}
			finally {
				DbUtils.closeQuietly(rs);
	      	    DbUtils.closeQuietly(st);
	      	    DbUtils.closeQuietly(con);
			}
			
		}
			
		if (error != "") {
			//set error session variable and lead to error page
			HttpSession session=request.getSession();  
	     	session.setAttribute("error",error); 
			request.getRequestDispatcher("error.jsp").include(request, response);
		}
		out.close();  
	}  


}
