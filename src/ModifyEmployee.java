

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Employee;
import databaseAccess.DBConnect;

/**
 * Servlet implementation class ModifyEmployee
 */
@WebServlet("/ModifyEmployee")
public class ModifyEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBConnect db = new DBConnect();
		db.OpenConnection();
		String button = IsMissingValue(request.getParameter("button"),"");
		String address = "";
		switch(button) {
		case "Add Employee":
			String empId = request.getParameter("empId");
			String empName = request.getParameter("empName");
			String username = request.getParameter("username");
			String password1 = request.getParameter("password");
			String password2 = request.getParameter("confirmPassword");
			String position = request.getParameter("position");
			String salary = request.getParameter("salary");
			if(empId != null && empName != null && username !=null &&
					position != null && salary != "") {
				if(password1 != null && password1.equals(password2)) {
					Employee anEmployee = new Employee(empId, empName, username, password1, 
							position, Double.parseDouble(salary));
					if(db.InsertEmployee(anEmployee)) {
						request.setAttribute("result", "success");
						address = "/ModifyEmployee.jsp";
					}
					else {
						request.setAttribute("result", "failure");
						address = "/ModifyEmployee";
					}
				} else {
					request.setAttribute("result", "password");
					address = "/ModifyEmployee";
				}
			} else {
				request.setAttribute("result", "empty");
				request.setAttribute("hide-passwd", "no");
				request.setAttribute("hide-button", "yes");
				address = "/ModifyEmployee.jsp";
			}
			break;
		case "Search":
			address = "/ModifyEmployee.jsp";
			String id = request.getParameter("empId");
			Employee anEmp = db.GetEmployee(id);
			request.setAttribute("employeeBean", anEmp);
			request.setAttribute("hide-passwd", "yes");
			request.setAttribute("hide-button", "no");
			break;
		case "Update":
			address = "/ModifyEmployee.jsp";
			empId = request.getParameter("empId");
			empName = request.getParameter("empName");
			username = request.getParameter("username");
			position = request.getParameter("position");
			salary = request.getParameter("salary");
			db.UpdateEmployee(Integer.parseInt(empId), empName, username, position, Double.parseDouble(salary));
			request.setAttribute("hide-passwd", "yes");
			request.setAttribute("hide-button", "yes");
			request.setAttribute("result", "success");
			request.setAttribute("hide-passwd", "no");
			break;
		case "Delete":
			address = "/ModifyEmployee.jsp";
			empId = request.getParameter("empId");
			db.DeleteEmployee(empId);
			request.setAttribute("result", "success");
			request.setAttribute("hide-passwd", "no");
			request.setAttribute("hide-button", "yes");
			break;
		case "Cancel":
			address = "/AdminUpdate.jsp";
			break;
		default:
			address = "/ModifyEmployee.jsp";
			request.setAttribute("hide-passwd", "no");
			request.setAttribute("hide-button", "yes");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	private String IsMissingValue(String parameter, String string) {
		if(parameter == null) {
			return string;
		} 
		return parameter;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
