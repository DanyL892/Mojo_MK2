package webshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


import org.apache.commons.dbutils.DbUtils;

/**
 * Servlet implementation class shop
 * This servlet class is used to display all items from
 * the database in the webshop.
 */
@WebServlet("/Shop")
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	public List<Item> getItems() {
		//get items from the database
		String error = "";
		boolean success = false;
		Connection con 	= null;
		Statement st 	= null;
		ResultSet rs 	= null;
		List<Item> currSet = new ArrayList<Item>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
	        st = con.createStatement();
	        
	        //select items from the database
	        String sql;
	      	sql="SELECT * FROM products";
	    	rs = st.executeQuery(sql);
	        
	    	if (!rs.isBeforeFirst()) {
	        	//no items found
	        	error = "Es konnte keine Verbindung zur Datenbank aufgebaut werden.";
	        } 
	        
	        while(rs.next()) {
	           Item result = new Item();
	           result.setImage(rs.getString(1));
	           result.setItem(rs.getString(2));
	           result.setText(rs.getString(3));
	           result.setPrice(rs.getString(4));

	           currSet.add(result);
	        }
			success = true;
		}
		catch(Exception e){
		     System.out.print(e);
		     e.printStackTrace();
		     success = false;
		}
		finally {
			DbUtils.closeQuietly(rs);
      	    DbUtils.closeQuietly(st);
      	    DbUtils.closeQuietly(con);
		}
		
		if (success == false) {
			error = "Leider ist ein Fehler aufgetreten. Bitte versuche es später erneut.";
		}
		
		if (error != "") {
			//store error variable in session variable and lead to error.jsp
		}
		
		return currSet;  
	}
}
