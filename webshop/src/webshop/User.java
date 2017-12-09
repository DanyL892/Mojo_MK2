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
	
	
	
	protected static void updateDataset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		boolean success		= false;
		String error    	= "";
		Connection con 		= null;
		Statement st 		= null;
		ResultSet rs 		= null;
		
		Integer userid  	= Integer.parseInt(request.getParameter("userid"));
		String name     	= request.getParameter("chg_name");
	    String mail     	= request.getParameter("chg_email");
	    String pass     	= request.getParameter("chg_passwort");
	    String passCheck	= request.getParameter("chg_passwort2");
	    String mailRegex	= "[A-z0-9ßäöü._%+-]+@[A-z0-9ßäöü.-]+\\.[A-z]{2,}";
        
	    //check if passwords match and email adress is valid
	    if (!pass.equals(passCheck)) {
	    	error = "Die eingegebenen Passwörter stimmen nicht überein.";
	    }
	    else if (mail.matches(mailRegex) == false) {
	    	error = "Bitte gib eine gültige E-Mail Adresse ein.";
	    }
	    
        
		// no errors occured, check existing input on database
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
			st = con.createStatement();
			
			//check for preexisting username
      	  	rs = st.executeQuery("SELECT name FROM users WHERE name='"+name+"'");
      	  	if (rs.isBeforeFirst()) {
      	  		rs.next();
      	  		if (rs.getString(1).equals(name)) {
      	  			error = "";
      	  		}
      	  		else {
      	  			error = "Ein User mit diesem Namen existiert bereits!";
      	  		}
      	  	}
				
      	  	if (error == "") {
				
      	  		//if password has been changed
				if (!pass.equals("") && !pass.equals("*****")) {
					//no errors occurred, attempt change
					//hash password for safety purposes
					MessageDigest alg = MessageDigest.getInstance("MD5");
					alg.reset();
					alg.update(pass.getBytes());
					byte[] digest = alg.digest();
					StringBuffer hashedpasswd = new StringBuffer();
					String hx;
					for (int m=0; m<digest.length; m++) {
						hx =  Integer.toHexString(0xFF & digest[m]);
						if(hx.length() == 1){hx = "0" + hx;}
						hashedpasswd.append(hx);
					}
					pass = hashedpasswd.toString();
					
					st.executeUpdate("UPDATE users SET name = '"+name+
							  "', passwort = '" +pass+
							  "', mail = '" +mail+ 
							  "' WHERE id = '" +userid+ "'");
					success = true;
	      	  		}
				//else update data without touching pw
				else {
					st.executeUpdate("UPDATE users SET name = '"+name+
							  "', mail = '" +mail+ 
							  "' WHERE id = '" +userid+ "'");
					success = true;
				}
			//update successful, change name and lead to Konto
			session.setAttribute("name", name);
			request.getRequestDispatcher("/Konto").include(request, response);
			}
		    else if (error != "") {
		    	//set error session variable and lead to error page
		    	session.setAttribute("error",error); 
		    	request.getRequestDispatcher("error.jsp").include(request, response); 
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
    }
        
	
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("button-changeUserdata")!= null) {
            User.updateDataset(request, response);
		}
	}
}