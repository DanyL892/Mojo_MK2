package webshop;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class getAdresses
 */
@WebServlet("/getAdresses")
public class getAdresses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAdresses() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session=request.getSession();
		String error = "";
		Integer userid     = Integer.parseInt(session.getAttribute("userid").toString());
		String street;
		String housenumber;
		String postalcode;
		String city;
		Boolean success = false;
		
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter(); 

		
			if (error == "") {
				// no errors occured, check existing input on database
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
					Statement st = con.createStatement();

					// check for existing user adress
					String query = "SELECT * FROM adress WHERE userid='"+userid+ "'";
					ResultSet rs = null;
					rs = st.executeQuery(query);
					
					//While (rs.next()){...}
					//If more adresses exist, more need to be displayed
					if (rs.next() == false) {
						request.getRequestDispatcher("userdata.jsp").include(request, response);
					}

					else {
						// no errors occured, attempt registration
						street = rs.getString(3);
						housenumber = rs.getString(4);
						postalcode = rs.getString(5);
						city = rs.getString(6);
						
						success = true;
						//userid = rs.getString(1);
						request.setAttribute("street", street);
						request.setAttribute("housenumber", housenumber);
						request.setAttribute("postalcode", postalcode);
						request.setAttribute("city", city);
						// registration successful, lead to index.jsp
						request.getRequestDispatcher("userdata.jsp").include(request, response);
					}
				}
				
				catch (Exception e) {
					System.out.print(e);
					e.printStackTrace();
					success = false;
				}

				if (success == false) {
					// check for left errors
					error = "Es gab einen Fehler beim Abruf deiner Adressen. Bitte versuche es später erneut.";
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
