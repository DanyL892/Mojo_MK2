package webshop;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShoppingItem
 * This sevlet class is used to create items that
 * can later be stored in or deleted from the Cart. 
 */
@WebServlet("/ShoppingItem")
public class ShoppingItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public String item;
    public float  price;
    public String zustand;
    public int    anzahl;


    public ShoppingItem(String item, float price, String zustand) {
        this.item    = item;
        this.price   = price;
        this.zustand = zustand;
        this.anzahl  = 1;
    }
    
    public String getItem() {
    	return item;
    }
    
    public float getPrice() {
    	return price;
    }
    
    public String getZustand() {
    	return zustand;
    }
    
    public int getAnzahl() {
    	return anzahl;
    }
    
    public void addAnzahl() {
    	this.anzahl++;
    }

	

}
