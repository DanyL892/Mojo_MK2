package webshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class search
 */
@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search() {
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
		
		String searchtext = request.getParameter("searchtext");
		String error = "";
		boolean success = false;
		ResultSet rs = null;
        String[] splittedSearch = searchtext.trim().split("\\s+");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
	        Statement st = con.createStatement();
	        
	        //select items from the database
	        String sql="SELECT * FROM products WHERE ";
	        for( int i = 0; i <= splittedSearch.length - 1; i++){
	            if (i==0){
	            	sql = sql + "title LIKE '%" + splittedSearch[i] + "%' ";  
	            }
	            else{
	            	sql = sql + "OR title LIKE '%" + splittedSearch[i] + "%' "; 
	            }
	        }
	    	rs = st.executeQuery(sql);
	        if (rs.next() == false) {
	        	success = true;
	        	request.setAttribute("message","Es wurden leider keine passenden Produkte gefunden :(");
				request.getRequestDispatcher("search_results.jsp").include(request, response);
	        }
	        List<item> results = new ArrayList<item>();

	        while(rs.next()) {
	           item result = new item();
	           result.setImage(rs.getString(1));
	           result.setItem(rs.getString(2));
	           result.setText(rs.getString(3));
	           result.setPrice(rs.getString(4));

	           results.add(result);
	        }
	        
	        success = true;
	        request.setAttribute("results", results);
	        request.setAttribute("message","");
			request.getRequestDispatcher("search_results.jsp").include(request, response);
	        
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
		
		
	}
		
}


