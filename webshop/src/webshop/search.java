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

import org.apache.commons.dbutils.DbUtils;

/**
 * Servlet implementation class search
 */
@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public search() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String error = "";
		boolean success = false;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		response.setContentType("text/html");  
		String searchtext = request.getParameter("searchtext");
        
		String[] splittedSearch = searchtext.trim().split("\\s+");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
	        st = con.createStatement();
	        
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
	        if (!rs.isBeforeFirst()) {
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
	}	
}


