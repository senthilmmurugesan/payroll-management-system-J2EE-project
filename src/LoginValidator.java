import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseAccess.DBConnect;

/**
 * Servlet implementation class LoginValidator
 */
@WebServlet("/LoginValidator")
public class LoginValidator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginValidator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		DBConnect db = new DBConnect();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String address = null;
		if(username != null && password != null) {
			db.OpenConnection();
			if(db.ValidateEmployee(username, password)) {
				String position = db.GetPosition(username);
				session.setAttribute("position", position);
				session.setAttribute("username", username);
				request.setAttribute("username", username);
				Cookie c = new Cookie("fred", "1");
				response.addCookie(c);
				if(username.equals("admin"))
					address = "/AdminUpdate.jsp";
				else
					address = "/TimeSheet.jsp";
			}
			else {
				request.setAttribute("result", "Error");
				address = "/DemoServlet";
			}
			db.CloseConnection();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
