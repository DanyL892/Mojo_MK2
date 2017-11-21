package webshop;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class item
 * This class is used to display item details to each item found
 * in the shop.jsp page.
 */
@WebServlet("/item")
public class item extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public item() {
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

		//store detail information as session variables
		String image = request.getParameter("image"); 
		String item  = request.getParameter("item");
		String price = request.getParameter("price");
		
		request.getSession(true).setAttribute("image", image);
		request.getSession(true).setAttribute("item", item);
		request.getSession(true).setAttribute("price", price);
		
		//display the detail page
		request.getRequestDispatcher("detail.jsp").include(request, response);  
	}

}
