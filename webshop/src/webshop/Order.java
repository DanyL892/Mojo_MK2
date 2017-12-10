package webshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.DbUtils;

/**
 * Servlet implementation class Order
 * This servlet class lets a user Order items from the shop
 */
@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int id;
	private int cust_id;
	private int addr_id;
	private String date;
	private int status;
	private int nummer;
	private int anzahl;
	private float preis;
	private String item;
	private String zustand;
	
	public Order() {
        super();
    }
    
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCust_id() {
		return this.cust_id;
	}
	public void setCust_id(int id) {
		this.cust_id = id;
	}
	
	public int getAddr_id() {
		return this.addr_id;
	}
	public void setAddr_id(int id) {
		this.addr_id = id;
	}
	
	public String getDate() {
		return this.date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getStatus() {
		return this.status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getNummer() {
		return this.nummer;
	}
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	
	public int getAnzahl() {
		return this.anzahl;
	}
	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}
	
	public float getPreis() {
		return this.preis;
	}
	public void setPreis(float preis) {
		this.preis = preis;
	}
	
	public String getItem() {
		return this.item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
	public String getZustand() {
		return this.zustand;
	}
	public void setZustand(String zustand) {
		this.zustand = zustand;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//generate an Order
		response.setContentType("text/html"); 
		HttpSession session = request.getSession();
		Connection con 	= null;
		Statement st 	= null;
		ResultSet rs 	= null;
		
		int userid;
		int adress_id  	= 0;
		int order_id   	= 0;
		int status     	= 1;
		int anzahl     	= 0;
		float preis    	= 0;
		String date;
		String item    	= "";
		String zustand 	= "";
		
		//Check whether user has logged in
		if(session.getAttribute("userid")==null) {
			//set error session variable and lead to error page
	     	session.setAttribute("error","Bitte melde dich erst an."); 
			request.getRequestDispatcher("error.jsp").include(request, response);
		}
		
		//get user from session variable
		userid = Integer.parseInt(session.getAttribute("userid").toString());
		
		//Check whether user has an address
		Adresse adresse	= new Adresse();
		if (adresse.hasAnAdress(userid) == false) {
	     	session.setAttribute("error","Bitte lege zuerst eine Adresse an."); 
			request.getRequestDispatcher("error.jsp").include(request, response);
		}
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
			st = con.createStatement();
		
			try {
				// get user adress
				String query = "SELECT * FROM adress WHERE userid='"+userid+ "'";
				rs = st.executeQuery(query);
				
				while(rs.next()) {
					adress_id = rs.getInt(1);
				}
			}
			catch (Exception e) {
				System.out.print(e);
				e.printStackTrace();
			}
			
			
			//get date
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	        Calendar c = df.getCalendar();
	        c.setTimeInMillis(System.currentTimeMillis());
	        date = c.get(Calendar.DAY_OF_MONTH) + "." + (c.get(Calendar.MONTH) + 1) + "." + c.get(Calendar.YEAR);
	        
	        //create order_id
	        LocalDateTime now = LocalDateTime.now();
	        int year = now.getYear();
	        int month = now.getMonthValue();
	        int day = now.getDayOfMonth();
	        int hour = now.getHour();
	        int minute = now.getMinute();
	        int second = now.getSecond();
	        
	        StringBuilder sb = new StringBuilder();
	        sb.append("");
	        sb.append(userid);
	        sb.append(year);
	        sb.append(hour);
	        sb.append(second);
	        String strI = sb.toString();
	        order_id = Integer.parseInt(strI);
	        
			//insert Order to Order table "orders"
			
	        try {		
				st.executeUpdate("INSERT INTO orders(id,cust_id,adr_id,date,status) VALUES('"+order_id+"','"+userid+"','"+adress_id+"','"+date+"','"+status+"')");
			}
			catch (Exception e) {
				System.out.print(e);
				e.printStackTrace();
			}
			
			//insert related items of Order to table order_items
			//get items from session
			List<ShoppingItem> itemList = (List<ShoppingItem>)request.getSession().getAttribute("items");
	
			for(int i=0; i<itemList.size(); i++) {
				ShoppingItem shopItem = itemList.get(i);
				preis   = shopItem.getPrice();
				item    = shopItem.getItem();
				anzahl  = shopItem.getAnzahl();
				zustand = shopItem.getZustand();
				
				//insert into database
				try {
					st.executeUpdate("INSERT INTO order_items(ord_id,product,anzahl,preis,zustand) VALUES('"+order_id+"','"+item+"','"+anzahl+"','"+preis+"','"+zustand+"')");
				}
				catch (Exception e) {
					System.out.print(e);
					e.printStackTrace();
				}
				
				//empty Cart
				session.removeAttribute("items");
				session.setAttribute("empty_cart", "true");
				
				//lead user to orders page
				request.getRequestDispatcher("cart.jsp").include(request, response);  
				
			}
		}
		catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();	
		}
		finally {
			DbUtils.closeQuietly(rs);
      	    DbUtils.closeQuietly(st);
      	    DbUtils.closeQuietly(con);
		}
	}
	
	
	
	public static List<Order> getOrders(int userid) {
		//get orders from the database
		String error    = "";
		boolean success = false;
		Connection con 	= null;
		Statement st 	= null;
		ResultSet rs 	= null;
		List<Order> currOrder = new ArrayList<Order>();
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
			st = con.createStatement();
			        
			//select items from the database
			 String sql;
			 sql="SELECT * FROM orders LEFT JOIN order_items ON orders.id = order_items.ord_id WHERE orders.cust_id = '"+userid+"'";
			 rs = st.executeQuery(sql);
			 
			 if (!rs.isBeforeFirst()) {
			 //no orders found
				 String message = "Du hast bisher noch keine Bestellungen.";
			 } 
			 
			 while(rs.next()) {
		           Order result = new Order();
		           result.setDate(rs.getString(4));
		           result.setStatus(rs.getInt(5));
		           result.setNummer(rs.getInt(7));
		           result.setItem(rs.getString(8));
		           result.setAnzahl(rs.getInt(9));
		           result.setPreis(rs.getFloat(10));
		           result.setZustand(rs.getString(11));

		           currOrder.add(result);
		        }
			 
			 success = true;
	  
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
		
		
      	    if (success == false) {
  			error = "Leider ist ein Fehler aufgetreten. Bitte versuche es später erneut.";
      	    }
  				
      	    if (error != "") {
  			//store error variable in session variable and lead to error.jsp
      	    }
  		}
		return currOrder;
	}

}
