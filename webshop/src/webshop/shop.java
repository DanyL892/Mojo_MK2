package webshop;

import java.io.IOException;
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
 * Servlet implementation class shop
 * This servlet class is used to display all items from
 * the database in the webshop.
 */
@WebServlet("/shop")
public class shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	public ResultSet getItems() {
		//get items from the database
		String error = "";
		boolean success = false;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
	        Statement st = con.createStatement();
	        
	        //select items from the database
	        String sql;
	      	sql="SELECT * FROM products";
	    	rs = st.executeQuery(sql);
	        if (rs.next() == false) {
	        	//no items found
	        	error = "Es konnte keine Verbindung zur Datenbank aufgebaut werden.";
	        } 
			success = true;
		}
		
		catch(Exception e){
		     System.out.print(e);
		     e.printStackTrace();
		     success = false;
		}
		
		if (success == false) {
			error = "Leider ist ein Fehler aufgetreten. Bitte versuche es später erneut.";
		}
		
		if (error != "") {
			//store error variable in session variable and lead to error.jsp
		}
		
		return rs;  
	}

}
