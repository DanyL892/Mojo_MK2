package webshop;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.dbutils.DbUtils;

/**
 * @author Jessica Buschkamp
 * @version 1.1
 * </br>
 * </br>
 * Description:</br> 
 * This servlet is used to store newly generated products in the database
 */
@WebServlet("/Upload")
@MultipartConfig(fileSizeThreshold=1024*1024*2,
maxFileSize=1024*1024*5)
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR="img/";
       

    public Upload() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error 		= "";
		String item 		= request.getParameter("produktname");
	    String text 		= request.getParameter("produkttext");
	    String preisChecker = request.getParameter("preis");
	    String preisRegex 	= "([0-9.])+";
	    
	    if (preisChecker.matches(preisRegex) == false) {
			error = "Bitte gib einen gültigen Preis ein. Separiere eventuelle Nachkommastellen mit einem Punkt.";
	    }
	    
	    Connection con 	= null;
		Statement st 	= null;
	    
	    if(error == "") {
		    try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
				st = con.createStatement();
				
				float preis = Float.parseFloat(preisChecker);
				st.executeUpdate("INSERT INTO products(title,text,preis) VALUES('"+item+"','"+text+"','"+preis+"')");
			}
			catch (Exception e) {
				System.out.print(e);
				e.printStackTrace();
			}
		    finally {
	      	    DbUtils.closeQuietly(st);
	      	    DbUtils.closeQuietly(con);
			}
		    request.getRequestDispatcher("shop.jsp").include(request, response); 
	    }
	    else {
	    	HttpSession session = request.getSession();
			session.setAttribute("error", error);
			request.getRequestDispatcher("error.jsp").include(request, response);
	    }
	}
	
}
