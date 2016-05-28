

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.TimeSheet;
import databaseAccess.DBConnect;

/**
 * Servlet implementation class PayPeriodServlet
 */
@WebServlet("/PayPeriodServlet")
public class PayPeriodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADD_NEW = "Add new TimeSheet";
	private static final String MODIFY = "View TimeSheet";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayPeriodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBConnect db = new DBConnect();
		db.OpenConnection();
		String button = request.getParameter("go");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String address = "";
		String period = request.getParameter("pay_period");
		ArrayList<String> days = GetDates(period.substring(0, 2), period.substring(3));
		if(ADD_NEW.equals(button)) {
			session.setAttribute("all-days", days);
			session.setAttribute("pay_period", period);
			address = "/TimeSheetEntry.jsp";
			if(!db.isNotApprovedPeriod(username, period)) {
				request.setAttribute("isApproved", "yes");
				session.setAttribute("all-days", days);
				address = "/TimeSheet.jsp";
			}
		} else if(MODIFY.equals(button)) { 
			String name = (String) session.getAttribute("username");
			int id = db.GetEmpID(name);
			ArrayList<TimeSheet> allEntries = db.GetTimeSheetEntries(username, period);
			request.setAttribute("all-entries", allEntries);
			request.setAttribute("empId", id);
			request.setAttribute("empName", name);
			address = "/UpdateTimeSheet.jsp";
			if(!db.isNotApprovedPeriod(username, period)) {
				request.setAttribute("isApproved", "yes");
				session.setAttribute("all-days", days);
				address = "/TimeSheet.jsp";
			}
		}
				
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public ArrayList<String> GetDates(String month, String period) {
		Calendar c = Calendar.getInstance();
		String startDay = ("1".equals(period)) ? "01" : "16";
		String endDay = ("1".equals(period)) ? "15" : "31";
		String startDate = "2016-" + month + "-" + startDay;
		String endDate = "2016-" + month + "-" + endDay;
		ArrayList<String> allDates = new ArrayList<>();
		allDates.add(startDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentMonth = month;
		while(!endDate.equals(startDate) && month.equals(currentMonth)) {
			try {
				c.setTime(sdf.parse(startDate));
				c.add(Calendar.DATE, 1);  // number of days to add
				startDate = sdf.format(c.getTime());  // dt is now the new date
				currentMonth = startDate.substring(5, 7);
				if(month.equals(currentMonth))
					allDates.add(startDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return allDates;
	}

}
