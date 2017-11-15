package webshop;
import java.sql.*;

public class DBConnector {
	
	Connection connection;
	 
	public DBConnector() {
	 
	}
	 
	public static Connection connect() {
	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
	} catch(Exception e) {
		throw new Error(e);
	}
	}

	public static boolean close(Connection c) {
		try {
			c.close();
			return true;
		} catch(Exception e) {
			return false;
		}
	}

}
