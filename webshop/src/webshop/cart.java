package webshop;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class cart
 * This servlet class is used to let the user
 * put items into a shopping cart and remove them.
 */
@WebServlet("/cart")
public class cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//ArrayList to store all cart items
	public ArrayList<ShoppingItem> shoppingItems = new ArrayList<ShoppingItem>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cart() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//delete items from shopping cart
		
		String item = request.getParameter("shopItem");
		String zustand = request.getParameter("shopZustand");
		
		//find the item within the ArrayList
		for(int i=0; i<shoppingItems.size(); i++) {
			ShoppingItem shopItem = shoppingItems.get(i);
			if (shopItem.getItem().equals(item)) {
				if (shopItem.getZustand().equals(zustand)) {
					shoppingItems.remove(i);
				}
			} 
		}
		//load shop page
		request.getRequestDispatcher("shop.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Add item to cart
		
		String item     = request.getParameter("item");
		String preis    = request.getParameter("price");
		float  price    = Float.parseFloat(preis);
		String zustand  = "neu";
		
		//create a ShoppingItem with given values
		ShoppingItem myItem = new ShoppingItem(item, price, zustand);
		//see if there are similar items in the cart yet
		for(int i=0; i<shoppingItems.size(); i++) {
			ShoppingItem shopItem = shoppingItems.get(i);
			if (shopItem.getItem().equals(myItem.getItem())) {
				if (shopItem.getZustand().equals(myItem.getZustand())) {
					//aggregate items of the same type
					myItem.addAnzahl();
					//delete duplicates
					shoppingItems.remove(i);
				}
			} 
		}
		//add the new item to cart
		shoppingItems.add(myItem);
		request.getSession(true).setAttribute("items", shoppingItems);
		//load the cart page
		request.getRequestDispatcher("cart.jsp").include(request, response);
	}
}