package webshop;
 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
/**
 * @author Daniel Friedrichs
 * @version 2.0
 * </br>
 * </br>
 * Description:</br> 
 * This servlet is used as a controller to direct users to different JSP-pages, depending on their
 * login status.
 */
@WebServlet("/Konto")
public class Konto extends HttpServlet {
    private static final long serialVersionUID = 1L;
        
     
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        if (session.getAttribute("name") == null || session.getAttribute("name") == "MEIN KONTO") { 
            request.getRequestDispatcher("konto.jsp").include(request, response);
        } else { 
            request.getRequestDispatcher("userdata.jsp").include(request, response);
        }
    }
 
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
 
}