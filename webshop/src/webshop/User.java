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

import org.apache.commons.dbutils.DbUtils;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int userid; 
	private String name;
	private String email;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
    }
    
    public int getUserid() {
		return userid;
	}
	public void setUserid(int id) {
		userid = id;
	}
    
    public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public String getEmail(){
		return this.email;
	}
	public void setEmail(String email){
		this.email = email;
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	public User receiveDataset(int userid) {
        User currUser = new User();
        boolean success    = false;
		String error       = "";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
        
        if (error == "") {
			// no errors occured, check existing input on database
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
				st = con.createStatement();

				// check for preexisting adress
				String query = "SELECT * FROM users WHERE id = '" +userid+ "'";
				rs = st.executeQuery(query);
				if (!rs.next()) {
					error = "Du hast bereits eine Adresse angelegt.";
					success = true;
				}

				currUser.setName(rs.getString(2));
				currUser.setEmail(rs.getString(4));
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
        }
        return currUser;
	}
	
	
	
	public void updateDataset(User newData) {
		boolean success    = false;
		String error       = "";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String pw = "";
        
        if (error == "") {
			// no errors occured, check existing input on database
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
				st = con.createStatement();

				st.executeUpdate("UPDATE users SET name = '"+newData.name+
								  "', passwort = '" +pw+
								  "', mail = '" +newData.email+ 
								  "' WHERE id = '" +newData.userid+ "'");
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
        }
	}
}