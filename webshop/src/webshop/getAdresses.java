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
	public int userid;
       
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
		
		HttpSession session = request.getSession();
		userid             = Integer.parseInt(session.getAttribute("userid").toString());
		
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter(); 
		
		request.getRequestDispatcher("userdata.jsp").include(request, response);
	}
	
	public void setId(int id) {
		userid = id;
	}
	
	public int getId() {
		return userid;
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
