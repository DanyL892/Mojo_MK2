package webshop;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChangeMode
 * This Servlet Class is used to toggle the admin change mode on the shop.jsp
 * page
 */
@WebServlet("/ChangeMode")
public class ChangeMode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();  
		
		System.out.println(request.getParameter("changemode"));
		
		if (request.getParameter("changemode").equals("change")) {
			session.setAttribute("changemode", "none");
		} else {
			session.setAttribute("changemode","change");
		}
		request.getRequestDispatcher("shop.jsp").include(request, response);
	}

}
