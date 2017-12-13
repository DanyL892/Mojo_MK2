package webshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;

/**
 * @author Daniel Friedrichs
 * @version 2.3
 * </br>
 * </br>
 * Description:</br> 
 * This servlet is used to store Item-Objects and display Item details to each Item found
 * in the shop.jsp page.
 */
@WebServlet("/Item")
public class Item extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String image;
	private String item;
	private String price;
	private String text;
	
	
	public String getImage(){
		return this.image;
	}
	public void setImage(String image){
		this.image = image;
	}
	
	public String getItem(){
		return this.item;
	}
	public void setItem(String item){
		this.item = item;
	}
	
	public String getPrice(){
		return this.price;
	}
	public void setPrice(String price){
		this.price = price;
	}
	
	public String getText(){
		return this.text;
	}
	public void setText(String text){
		this.text = text;
	}
	
	
	public static List<Item> getItems() {
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
		
		return currSet;  
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//store detail information as session variables
		String image = request.getParameter("image"); 
		String item  = request.getParameter("item");
		String price = request.getParameter("price");
		String text  = request.getParameter("text");
		
		request.getSession(true).setAttribute("image", image);
		request.getSession(true).setAttribute("item", item);
		request.getSession(true).setAttribute("price", price);
		request.getSession(true).setAttribute("text", text);
		
		//display the detail page
		request.getRequestDispatcher("detail.jsp").include(request, response);  
	}
	
	

}
