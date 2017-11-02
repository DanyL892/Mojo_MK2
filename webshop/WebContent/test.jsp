<html>
<head><title>Enter to database</title></head>
<body>

<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%

try {
	Class.forName("com.mysql.jdbc.Driver");
	
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/webshop", "admin", "admin");
	Statement stmt = con.createStatement();
	
	ResultSet rs = stmt.executeQuery("select * from users");
	
		System.out.println("hello");
		con.close();


}finally{}

%>
</body>
</html>