

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseAccess.DBConnect;

/**
 * Servlet implementation class InsertTimeSheet
 */
@WebServlet("/InsertTimeSheet")
public class InsertTimeSheet extends HttpServlet {
	private static final String TIMESHEET_BUTTON = "Timesheet Home";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertTimeSheet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address;
		String button = request.getParameter("button");
		if(TIMESHEET_BUTTON.equals(button)) {
			address = "/TimeSheet.jsp";
		} else {
			address = "/TimeSheetEntry.jsp";
			HttpSession session = request.getSession();
			DBConnect db = new DBConnect();
			db.OpenConnection();
			String period = (String) session.getAttribute("pay_period");
			String username = (String) session.getAttribute("username");
			int id = db.GetEmpID(username);
			String date = request.getParameter("all-days");
			String start = date + " " + request.getParameter("startTime") + ":00";
			String end = date + " " + request.getParameter("endTime") + ":00";
			int weekNum = db.FindWeekNum(date);
			if(db.InsertTimeSheetEntry(id, username, start, end, date, weekNum, period, false, false))
				request.setAttribute("result", "success");
			else
				request.setAttribute("result", "failure");
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
