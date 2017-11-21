package webshop;

import java.io.IOException;
import java.security.MessageDigest;
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
 * Servlet implementation class createAdress
 */
@WebServlet("/createAdress")
public class createAdress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createAdress() {
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
		//register
				boolean success = false;
			    String error    = "";
			    //Get from session?
			    Integer userid		= 0;
			    String street     	= request.getParameter("street");
			    String housenumber  = request.getParameter("housenumber");
			    String postalcode   = request.getParameter("postalcode");
				String city 		= request.getParameter("city");	
				
				//check user input
				if (street == "") {
					error = "Bitte gib einen Straﬂennamen ein.";
				} else if (housenumber == "") {
					error = "Bitte gib eine Hausnummer ein.";
				} else if (postalcode == "") {
					error = "Bitte gib eine Postleitzahl ein.";
				} else if (city == "") {
					error = "Bitte gib eine Stadt ein.";	
				}
				
				if (error == "") {
					//no errors occured, check existing input on database
					try {
						Class.forName("com.mysql.jdbc.Driver");
			            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
			            Statement st=con.createStatement();
			          
			            //check for preexisting adress
			            String query;
			            //Check whether this works
			      	  	query="SELECT * FROM adress WHERE "
			      	  			+ "street='"+street+"',  "
			      	  			+ "housenumber ='"+housenumber+"', "
			      	  			+ "postalcode = '"+postalcode+"',  "
			      	  			+ "city = '"+city+"'  ";
			      	  	ResultSet rs = null;
			      	  	rs = st.executeQuery(query);
			      	  	if (rs.next()) {
			        	  error = "Ein Eintrag mit diesen Daten existiert bereits";
			      	  	} 
			          
			    
			      	  	if (error == "") {
			       		  //no errors occured, attempt registration
				       	  st.executeUpdate("insert into adress(userid,street,housenumber,postalcode, city) "
				       	  		+ "values('"+userid+"','"+street+"','"+housenumber+"''"+postalcode+"','"+city+"',)");
			       		  success = true;
			       		  //registration successful, lead to index.jsp
			      	      request.getRequestDispatcher("index.jsp").include(request, response);
			          }
			       }
				
			       catch(Exception e) {
			    	   System.out.print(e);
			    	   e.printStackTrace();
			    	   success = false;
			       }
					
					if (success == false) {
						//check for left errors
					}
						error = "Es gab einen Fehler bei der Registrierung. Bitte versuche es sp‰ter erneut.";
					}
					
					//check if errors occured
					if (error != "") {
						//set error session variable and lead to error page
						HttpSession session=request.getSession();  
				     	session.setAttribute("error",error); 
						request.getRequestDispatcher("error.jsp").include(request, response); 
					}
			}

}
