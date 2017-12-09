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

import org.apache.commons.dbutils.DbUtils;

/**
 * Servlet implementation class Stornierung
 * This servlet class lets a user cancel an Order. The status of the Order is changed in the database
 */
@WebServlet("/Stornierung")
public class Stornierung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stornierung() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get Order that has to be canceled
		int order = Integer.parseInt(request.getParameter("orderId").toString());
		Connection con 	= null;
		Statement st 	= null;
		ResultSet rs 	= null;
		
		//change database entry
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
			st = con.createStatement();
			
			st.executeUpdate("UPDATE orders SET status = 3 WHERE id = '"+order+"'");
		}
		catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
		finally {
			DbUtils.closeQuietly(rs);
      	    DbUtils.closeQuietly(st);
      	    DbUtils.closeQuietly(con);
		}
		
		//lead user to orders page
		request.getRequestDispatcher("orders.jsp").include(request, response); 
	}

}
