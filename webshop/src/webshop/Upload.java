package webshop;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
@MultipartConfig(fileSizeThreshold=1024*1024*2,
maxFileSize=1024*1024*5)
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR="img/";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
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
		// TODO Auto-generated method stub
		 //String savePath  = File.separator + SAVE_DIR;
         //File fileSaveDir = new File(savePath);
         //if(!fileSaveDir.exists()){
         //    fileSaveDir.mkdir();
        // }
         
		String item = request.getParameter("produktname");
	    String text = request.getParameter("produkttext");
	    int preis   = Integer.parseInt(request.getParameter("preis"));
	    //Part file = request.getPart("file");
	    
	    //String fileName=extractfilename(file);
	    //file.write(savePath + File.separator + fileName);
	    //String filePath= savePath + File.separator + fileName ;
	    
	    try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "");
			Statement st = con.createStatement();
			st.executeUpdate("INSERT INTO products(title,text,preis) VALUES('"+item+"','"+text+"','"+preis+"')");
		}
		catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
	    request.getRequestDispatcher("shop.jsp").include(request, response); 
	}
	
	private String extractfilename(Part file) {
        String cd = file.getHeader("content-disposition");
        String[] items = cd.split(";");
        for (String string : items) {
            if (string.trim().startsWith("filename")) {
                return string.substring(string.indexOf("=") + 2, string.length()-1);
            }
        }
        return "";
    }


}
