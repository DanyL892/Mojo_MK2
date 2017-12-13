package webshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;

/**
 * @author Jessica Buschkamp
 * @version 1.0
 * </br>
 * </br>
 * Description:</br> 
 * This servlet class lets the admin user make changes to Item texts and update the database 
 * with those texts.
 */
@WebServlet("/Change")
public class Change extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//change texts from items on the database
		Connection con 	= null;
		Statement st 	= null;
		
		//get information from the jsp 
		String text = request.getParameter("text");
		String item = request.getParameter("changeitem");
		
		
		//connect to database
		try {
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
            st=con.createStatement();
            //save new description text to database
            st.executeUpdate("UPDATE products SET text = '"+text+"' WHERE title = '"+item+"'");
            request.getRequestDispatcher("shop.jsp").include(request, response);
		}
		catch(Exception e) {
	    	   System.out.print(e);
	    	   e.printStackTrace();
	    }
		finally {
      	    DbUtils.closeQuietly(st);
      	    DbUtils.closeQuietly(con);
		}
	}

}
