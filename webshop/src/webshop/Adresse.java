package webshop;

import java.io.IOException;
import java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Adresse
 */
@WebServlet("/Adresse")
public class Adresse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int userid;   
	private String street;
	private String number;
	private String zip;
	private String city;
	
	public int getId() {
		return userid;
	}
	public void setId(int id) {
		userid = id;
	}
	
	public String getStreet(){
		return this.street;
	}
	public void setStreet(String street){
		this.street = street;
	}
	
	public String getNumber(){
		return this.number;
	}
	public void setNumber(String number){
		this.number = number;
	}
	
	public String getZip(){
		return this.zip;
	}
	public void setZip(String zip){
		this.zip = zip;
	}
	
	public String getCity(){
		return this.city;
	}
	public void setCity(String city){
		this.city = city;
	}	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Adresse() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    public void showAdress() {
		//HttpSession session = request.getSession();
		Integer user_id    = getId();
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
			Statement st = con.createStatement();

			// check for existing user adress
			String query = "SELECT * FROM adress WHERE userid='"+user_id+ "'";
			rs = st.executeQuery(query);
			
			this.street = rs.getString(3);
			this.number = rs.getString(4);
			this.zip = rs.getString(5);
			this.city = rs.getString(6);			
			
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
    
	public ResultSet showAdresses() {
		//HttpSession session = request.getSession();
		Integer user_id    = getId();
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
			Statement st = con.createStatement();

			// check for existing user adress
			String query = "SELECT * FROM adress WHERE userid='"+user_id+ "'";
			rs = st.executeQuery(query);
			while(rs.next()) {
				System.out.println(rs.getString(3));
			}
		}
		
		catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public void createAdress() {
		//to be done
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
