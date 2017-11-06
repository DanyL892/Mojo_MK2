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
 * Servlet implementation class shop
 */
@WebServlet("/shop")
public class shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shop() {
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public ResultSet getItems() {
		String error = "";
		boolean success = false;
		ResultSet rs = null;
		
		try {
	          Class.forName("com.mysql.jdbc.Driver");
	          /* "jdbc:mysql://localhost:3306/webshop" 
	           * "jdbc:mysql://192.168.64.2/webshop" */
	          Connection con = DriverManager.getConnection("jdbc:mysql://192.168.64.2/webshop", "root", "");
	          Statement st=con.createStatement();
	          String sql;
	      	  sql="SELECT * FROM products";
	    	  rs = st.executeQuery(sql);
	          if (rs.next() == false) {
	        	  error = "Leider ist ein Fehler aufgetreten. Bitte versuchen Sie es später erneut.";
	          } 
		}
		catch(Exception e){
		       System.out.print(e);
		       e.printStackTrace();
		       success = false;
		}
		return rs;  
	}

}