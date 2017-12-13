package webshop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Jessica Buschkamp
 * @version 1.0
 * </br>
 * </br>
 * Description:</br>
 * This servlet class is used to log out a user from
 * the platform
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
    	//Logout
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter();   
		
		//invalidate the Login session variable
		HttpSession session=request.getSession();  
		session.invalidate();  
		request.getRequestDispatcher("index.jsp").include(request, response); 
		out.print("Du hast dich erfolgreich ausgeloggt!");  
		out.close();  
	}  


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
        request.getRequestDispatcher("index.jsp").include(request, response);  
          
        HttpSession session=request.getSession();  
        session.invalidate();  
          
        out.print("You are successfully logged out!");  
          
        out.close();
	}

}
