package webshop;

import java.io.IOException;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// register
		boolean success    = false;
		String error       = "";
		Integer userid     = Integer.parseInt(request.getParameter("userid"));
		String street      = request.getParameter("street");
		String housenumber = request.getParameter("housenumber");
		String postalcode  = request.getParameter("postalcode");
		String city        = request.getParameter("city");
		String streetRegex = "([A-z\\.-]+[\\s]{0,})+";
		String hnRegex = "[0-9-]+[\\s]{0,}[A-z-]{0,}";
		String pcRegex = "[0-9]{5,5}";
		String cityRegex = "([A-z\\.-]+[\\s]{0,})+";

		// check user input
		if (street == "" || street == "Straße") {
			error = "Bitte gib einen Straßennamen ein.";
		} else if (street.matches(streetRegex) == false) {
			error = "Bitte gib eine gültige Straße ein.";
		} else if (housenumber == "") {
			error = "Bitte gib eine Hausnummer ein.";
		} else if (housenumber.matches(hnRegex) == false) {
			error = "Bitte gib eine gültige Hausnummer ein.";
		} else if (postalcode == "") {
			error = "Bitte gib eine Postleitzahl ein.";
		} else if (postalcode.matches(pcRegex) == false) {
			error = "Bitte gib eine gültige Postleitzahl ein.";
		} else if (city == "") {
			error = "Bitte gib eine Stadt ein.";
		} else if (city.matches(cityRegex) == false) {
			error = "Bitte gib eine gültige Stadt ein.";
		}

		if (error == "") {
			// no errors occured, check existing input on database
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
				Statement st = con.createStatement();

				// check for preexisting adress
				String query = "SELECT * FROM adress WHERE userid='" +userid+ "' AND " + "street='" +street+ "' AND " 
						+ "housenumber='" +housenumber+ "' AND " + "postalcode='" +postalcode+ "' AND " + "city='" +city+ "'";
				ResultSet rs = null;
				rs = st.executeQuery(query);
				if (rs.next()) {
					error = "Ein Eintrag mit diesen Daten existiert bereits";
				}

				if (error == "") {
					// no errors occured, attempt registration
					String insertQuery = "INSERT INTO adress(userid,street,housenumber,postalcode,city) " 
							+ "VALUES('"+userid+"','"+street+"','"+housenumber+"','"+postalcode+"','"+city+"')";
					st.executeUpdate(insertQuery);
					success = true;
					// registration successful, lead to index.jsp
					request.getRequestDispatcher("index.jsp").include(request, response);
				}
			}

			catch (Exception e) {
				System.out.print(e);
				e.printStackTrace();
				success = false;
			}

			if (success == false) {
				// check for left errors
				error = "Es gab einen Fehler bei der Speicherung der Adresse. Bitte versuche es später erneut.";
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

}