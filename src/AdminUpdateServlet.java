

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Salary;
import databaseAccess.DBConnect;

/**
 * Servlet implementation class AdminUpdateServlet
 */
@WebServlet("/AdminUpdateServlet")
public class AdminUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		DBConnect db = new DBConnect();
		db.OpenConnection();
		String address = "/AdminUpdate.jsp";
		String button = request.getParameter("button");
		String period = request.getParameter("pay_period");
		db.FindHours(period);
		ArrayList<String> allEmps = db.GetAllEmps();
		System.out.println(allEmps.size() + " allEmps");
		ArrayList<Double> allNumOfHours = db.GetAllSalaries();
		ArrayList<Salary> salaryData = db.GetAllSalaryData(period);
		request.setAttribute("salary-data", salaryData);
		ArrayList<Salary> salaries = new ArrayList<>();
		for(int i=0; i<allEmps.size(); i++) {
			Salary salary = new Salary();
			salary.setEmpId(db.GetEmpID(allEmps.get(i)));
			salary.setEmpName(allEmps.get(i));
			salary.setNumOfHours(allNumOfHours.get(i));
			salary.setPosition(db.GetPosition(allEmps.get(i)));
			double currSalary = allNumOfHours.get(i) * db.GetSalary(allEmps.get(i));
			salary.setSalary(currSalary);
			salaries.add(salary);
		}
		if("Load".equals(button)) {
			request.setAttribute("table-data", salaries);
			request.setAttribute("selected", period);
		} else if("Approve".equals(button)) {
			db.ApproveEmployees(period, allEmps);	//Approved in timesheet table
			db.MoveApprovedToPaid(period, salaries); // Move to salary table
			salaryData = db.GetAllSalaryData(period);
			request.setAttribute("salary-data", salaryData);
		} ////// need to check the salary table after approves
		/// this part is incomplete. check approve method
		
		db.CloseConnection();
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
