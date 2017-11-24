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
		
		Integer userid     = Integer.parseInt(request.getParameter("userid"));
		String answer;
		
		
			if (error == "") {
				// no errors occured, check existing input on database
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
					Statement st = con.createStatement();

					// check for preexisting adress
					String query = "SELECT * FROM adress WHERE userid='" +userid+ "'";
					ResultSet rs = null;
					rs = st.executeQuery(query);
					if (rs.next() == false) {
						answer = "Ein Eintrag mit diesen Daten existiert bereits";
					}

					if (error == "") {
						// no errors occured, attempt registration
						String insertQuery = "INSERT INTO adress(userid,street,housenumber,postalcode,city) " 
								+ "VALUES('"+userid+"','"+street+"','"+housenumber+"','"+postalcode+"','"+city+"')";
						st.executeUpdate(insertQuery);
						success = true;
						// registration successful, lead to index.jsp
						request.getRequestDispatcher("userdata.jsp").include(request, response);
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
