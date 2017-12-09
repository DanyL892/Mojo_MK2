package webshop;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Item
 * This class is used to display Item details to each Item found
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
