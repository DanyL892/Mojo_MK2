package webshop;
import java.io.IOException;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Daniel Friedrichs
 * @version 2.0
 * </br>
 * </br>
 * Description:</br> 
 * This servlet is used to store Address objects and change/update
 * user addressdata via its doPost method. </br> </br>
 * It implements 5 methods: </br>
 * hasAnAdress: Checks for existing address of user </br>
 * getUserAdress: fetches adressdata from database </br>
 * changeAddress: changing adressdata based on user's form input </br>
 * saveAddress: saves newly created address to database </br>
 * doPost: controller method for directing post-requests
 */
@WebServlet("/Adresse")
public class Adresse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int userid;   
	private String street;
	private String number;
	private String zip;
	private String city;
	private boolean hasAdress = false;
	
	
	public Adresse() {
        super();
    }
	
	public int getId() {
		return userid;
	}
	public void setId(int id) {
		userid = id;
	}
	
	public boolean getHasAdress() {
		return hasAdress;
	}
	
	public void setHasAdress(boolean has) {
		hasAdress = has;
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
	
	
    public boolean hasAnAdress(int userid){
    	//HttpSession session = request.getSession();
		//Integer user_id	= this.userid;
		Connection con 	= null;
		Statement st 	= null;
		ResultSet rs 	= null;
		boolean success	= false;	
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
			st = con.createStatement();
			
			// check for existing user adress
			String query = "SELECT * FROM adress WHERE userid='"+userid+ "'";
			rs = st.executeQuery(query);
			
			if (rs.next()) {
				success = true;
			}
			else {
				success = false;
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
		
		return success;	
    }
    
    public void getUserAdress(int user_id) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
			st = con.createStatement();
			
			// check for existing user adress
			String query = "SELECT * FROM adress WHERE userid='"+user_id+ "'";
			rs = st.executeQuery(query);
			
			if (rs.next()) {
				this.street = rs.getString(3);
				this.number = rs.getString(4);
				this.zip = rs.getString(5);
				this.city = rs.getString(6);
				this.hasAdress = true;
			}
			
			else {
				this.street = "";
				this.number = "";
				this.zip = "";
				this.city = "";
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
    
    
    private static void changeAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// create new adress
		boolean success	= false;
		String error   	= "";
		Connection con 	= null;
		Statement st 	= null;
		
		Integer userid     	= Integer.parseInt(request.getParameter("userid"));
		String street      	= request.getParameter("chg_street");
		String housenumber 	= request.getParameter("chg_housenumber");
		String postalcode  	= request.getParameter("chg_postalcode");
		String city        	= request.getParameter("chg_city");
		String streetRegex 	= "([A-z����\\.-]+[\\s]{0,})+";
		String hnRegex 		= "[0-9-]+[\\s]{0,}[A-z-]{0,}";
		String pcRegex 		= "[0-9]{5,5}";
		String cityRegex 	= "([A-z����\\.-]+[\\s]{0,})+";
		
		// check user input
		if (street.equals("") || street.equals("Stra�e")) {
			error = "Bitte gib einen Stra�ennamen ein.";
		} else if (street.matches(streetRegex) == false) {
			error = "Bitte gib eine g�ltige Stra�e ein.";
		} else if (housenumber == "") {
			error = "Bitte gib eine Hausnummer ein.";
		} else if (housenumber.matches(hnRegex) == false) {
			error = "Bitte gib eine g�ltige Hausnummer ein.";
		} else if (postalcode == "") {
			error = "Bitte gib eine Postleitzahl ein.";
		} else if (postalcode.matches(pcRegex) == false) {
			error = "Bitte gib eine g�ltige Postleitzahl ein.";
		} else if (city.equals("") || city.equals("Stadt")) {
			error = "Bitte gib eine Stadt ein.";
		} else if (city.matches(cityRegex) == false) {
			error = "Bitte gib eine g�ltige Stadt ein.";
		}

		if (error == "") {
			// no errors occured, check existing input on database
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
				st = con.createStatement();

				// check for preexisting adress
				st.executeUpdate("UPDATE adress SET street = '"+street+
						  "', housenumber = '" +housenumber+
						  "', postalcode = '" +postalcode+ 
						  "', city = '" +city+
						  "' WHERE userid = '" +userid+ "'");
				success = true;

				// update successful, lead to konto.jsp
				request.getRequestDispatcher("/Konto").include(request, response);
				
			}
			catch (Exception e) {
				System.out.print(e);
				e.printStackTrace();
				success = false;
			}
			finally {
	      	    DbUtils.closeQuietly(st);
	      	    DbUtils.closeQuietly(con);
			}

			if (success == false) {
				// check for left errors
				error = "Es gab einen Fehler bei der Speicherung der Adresse. Bitte versuche es sp�ter erneut.";
			}
		}

		// check if errors occured
		if (error != "") {
			// set error session variable and lead to error page
			HttpSession session = request.getSession();
			session.setAttribute("error", error);
			request.getRequestDispatcher("error.jsp").include(request, response);
		}
    }
    
    
    
    private static void saveAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// create new adress
		boolean success	= false;
		String error   	= "";
		Connection con 	= null;
		Statement st 	= null;
		ResultSet rs 	= null;
		
		Integer userid     	= Integer.parseInt(request.getParameter("userid"));
		String street      	= request.getParameter("add_street");
		String housenumber 	= request.getParameter("add_housenumber");
		String postalcode  	= request.getParameter("add_postalcode");
		String city        	= request.getParameter("add_city");
		String streetRegex 	= "([A-z����\\.-]+[\\s]{0,})+";
		String hnRegex 		= "[0-9-]+[\\s]{0,}[A-z-]{0,}";
		String pcRegex 		= "[0-9]{5,5}";
		String cityRegex 	= "([A-z����\\.-]+[\\s]{0,})+";

		// check user input
		if (street.equals("") || street.equals("Stra�e")) {
			error = "Bitte gib einen Stra�ennamen ein.";
		} else if (street.matches(streetRegex) == false) {
			error = "Bitte gib eine g�ltige Stra�e ein.";
		} else if (housenumber == "") {
			error = "Bitte gib eine Hausnummer ein.";
		} else if (housenumber.matches(hnRegex) == false) {
			error = "Bitte gib eine g�ltige Hausnummer ein.";
		} else if (postalcode == "") {
			error = "Bitte gib eine Postleitzahl ein.";
		} else if (postalcode.matches(pcRegex) == false) {
			error = "Bitte gib eine g�ltige Postleitzahl ein.";
		} else if (city.equals("") || city.equals("Stadt")) {
			error = "Bitte gib eine Stadt ein.";
		} else if (city.matches(cityRegex) == false) {
			error = "Bitte gib eine g�ltige Stadt ein.";
		}

		if (error == "") {
			// no errors occured, check existing input on database
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
				st = con.createStatement();

				// check for preexisting adress
				String query = "SELECT * FROM adress WHERE userid='" +userid+ "'";
				rs = st.executeQuery(query);
				if (rs.isBeforeFirst()) {
					error = "Du hast bereits eine Adresse angelegt.";
					success = true;
				}

				if (error == "") {
					// no errors occured, attempt adress creation
					String insertQuery = "INSERT INTO adress(userid,street,housenumber,postalcode,city) " 
							+ "VALUES('"+userid+"','"+street+"','"+housenumber+"','"+postalcode+"','"+city+"')";
					st.executeUpdate(insertQuery);
					success = true;
					// creation successful, lead to konto.jsp
					request.getRequestDispatcher("/Konto").include(request, response);
				}
			}
			catch (Exception e) {
				System.out.print(e);
				e.printStackTrace();
				success = false;
			}
			finally {
				DbUtils.closeQuietly(rs);
	      	    DbUtils.closeQuietly(st);
	      	    DbUtils.closeQuietly(con);
			}

			if (success == false) {
				// check for left errors
				error = "Es gab einen Fehler bei der Speicherung der Adresse. Bitte versuche es sp�ter erneut.";
			}
		}

		// check if errors occured
		if (error != "") {
			// set error session variable and lead to error page
			HttpSession session = request.getSession();
			session.setAttribute("error", error);
			request.getRequestDispatcher("error.jsp").include(request, response);
		}
    }
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("button-saveAddress")!= null) {
            Adresse.saveAddress(request, response);
        } 
		else if (request.getParameter("button-changeAddress")!= null) {
            Adresse.changeAddress(request, response);
        }
	}
}
	

