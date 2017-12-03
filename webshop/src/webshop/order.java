package webshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class order
 * This servlet class lets a user order items from the shop
 */
@WebServlet("/order")
public class order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public order() {
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
		//generate an order
		HttpSession session = request.getSession();
		ResultSet rs = null;
		int adress_id = 0;
		int order_id  = 0;
		int status    = 1;
		int anzahl    = 0;
		float preis   = 0;
		String item   = "";
		
		//get user from session variable
		int userid = Integer.parseInt(session.getAttribute("userid").toString());
		
		//get adress id from user
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
			Statement st = con.createStatement();
			
			// get user adress
			String query = "SELECT * FROM adress WHERE userid='"+userid+ "'";
			rs = st.executeQuery(query);
			while(rs.next()) {
				adress_id = Integer.parseInt(rs.getString(1).toString());
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
        String date = c.get(Calendar.DAY_OF_MONTH) + "." + (c.get(Calendar.MONTH) + 1) + "." + c.get(Calendar.YEAR);
        
        //creader order_id
        order_id = ThreadLocalRandom.current().nextInt(1, 200 + 1);
        order_id = order_id + userid;
        
		//insert order to order table "orders"
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
			Statement st = con.createStatement();
			
			st.executeUpdate("INSERT INTO orders(id,cust_id,adr_id,date,status) VALUES('"+order_id+"','"+userid+"','"+adress_id+"','"+date+"','"+status+"')");
			System.out.println(order_id + "" + userid + "" + adress_id + "" + date + "" + status);
		}
		catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
		
		//insert related items of order to table order_items
		//get items from session
		java.util.List<ShoppingItem> itemList = (java.util.List<ShoppingItem>)request.getSession().getAttribute("items");

		for(int i=0; i<itemList.size(); i++) {
			ShoppingItem shopItem = itemList.get(i);
			preis  = shopItem.getPrice();
			item   = shopItem.getItem();
			anzahl = shopItem.getAnzahl();
			
			//insert into database
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
				Statement st = con.createStatement();
				
				st.executeUpdate("INSERT INTO order_items(ord_id,product,anzahl,preis) VALUES('"+order_id+"','"+item+"','"+anzahl+"','"+preis+"')");
			}
			catch (Exception e) {
				System.out.print(e);
				e.printStackTrace();
			}
			
			//empty cart
			session.removeAttribute("items");
			
			//lead user to orders page
			request.getRequestDispatcher("orders.jsp").include(request, response);  
			
		}
	}

}
