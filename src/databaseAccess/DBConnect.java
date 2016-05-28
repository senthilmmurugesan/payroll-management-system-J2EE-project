package databaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.sql.PreparedStatement;

import classes.Employee;
import classes.Salary;
import classes.TimeSheet;

public class DBConnect {
	Connection connection;
	Statement statement;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	ArrayList<String> allEmps = new ArrayList<>();
	ArrayList<Double> allNumOfHours = new ArrayList<>();
	
	public void OpenConnection() {
		try {
			System.out.println("Connecting....");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/payroll", "root", "");
			System.out.println("Connected!");
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void CloseConnection() {
		try {
			connection.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean InsertEmployee(Employee employee) {
		int res = 0;
		String query = "insert into Employees values (" + 
														employee.getEmpId() + ", '" +
														employee.getEmpName() + "', '" +
														employee.getUsername() + "', '" +
														employee.getPassword() + "', '" +
														employee.getPosition() + "', " +
														employee.getSalary() + ")";
		try {
			res = statement.executeUpdate(query);
		} catch (SQLException e) {
			if(e.getMessage().contains("Duplicate entry")) {
				System.err.println("Looks like you are not a new employee!!!");
			} else
				e.printStackTrace();
		}
		return res>0;
	}
	
	public boolean ValidateEmployee(String username, String password) {
		String query = "select * from Employees where username = '" + username + "' and password = '" + password + "'";
		System.out.println(query);
		try {
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Employee GetEmployee(String id) {
		Employee anEmp = new Employee();
		String sql = "select * from employees where empId = '" + id + "'";
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				anEmp.setEmpId(id);
				anEmp.setEmpName(rs.getString("empName"));
				anEmp.setPosition(rs.getString("position"));
				anEmp.setSalary(rs.getDouble("salary"));
				anEmp.setUsername(rs.getString("username"));
				return anEmp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String GetEmpName(int id) {
		String query = "select * from Employees where empId = " + id;
		String name = "";
		try {
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				name = rs.getString("empName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public int GetEmpID(String username) {
		String query = "select * from Employees where username = '" + username + "'";
		int id = 0;
		try {
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				id = rs.getInt("empId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public String GetPosition(String username) {
		String query = "select * from Employees where username = '" + username + "'";
		String position = "";
		try {
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				position = rs.getString("position");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(position);
		return position;
	}
	
	public double GetSalary(String username) {
		String query = "select * from Employees where username = '" + username + "'";
		double salary = 0;
		try {
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				salary = rs.getDouble("salary");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salary;
	}
	
	public boolean UpdateEmployee(int empId, String empName, String username, String position, double salary) {
		String sql = "update employees set empName = ?, username = ?, position = ?, salary = ? "
				+ "where empId = ?";
		int res = 0;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, empName);
			stmt.setString(2, username);
			stmt.setString(3, position);
			stmt.setDouble(4, salary);
			stmt.setInt(5, empId);
			res = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res>0;
	}
	
	public void DeleteEmployee(String empId) {
		String query = "delete from Employees where empId = '" + empId + "'";
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public double GetSalaryByEmpID(String empId) {
		String query = "select salary from Employees where empId = '" + empId + "'";
		double salary = 0;
		try {
			ResultSet result = statement.executeQuery(query);
			while(result.next()) {
				salary = result.getDouble("salary");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salary;
	}
	
	public boolean InsertTimeSheetEntry(int empId, String empName, String startTime, String endTime, 
									String date, int weekNum, String period, boolean isApproved, boolean isCompleted) {
		int res = 0;
		String query = "insert into timesheet values (" 
							+ empId + ", '" + empName + "', '" + startTime + "', '" + endTime + "', '" 
							+ date + "', " + weekNum + ", '" + period + "', " + isApproved + ", " + isCompleted + ")";
		try {
			res = statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res>0;
	}
	
	public ArrayList<TimeSheet> GetTimeSheetEntries(String username, String period) {
		ArrayList<TimeSheet> allEntries = new ArrayList<>();
		String sql = "select * from timesheet where empName = '" + username 
						+ "' and pay_period = '" + period + "'";
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				TimeSheet currentEntry = new TimeSheet();
				currentEntry.setEmpId(rs.getInt("empId"));
				currentEntry.setEmpName(rs.getString("empName"));
				currentEntry.setStartTime(rs.getString("startTime"));
				currentEntry.setEndTime(rs.getString("endTime"));
				currentEntry.setDate(rs.getString("date"));
				currentEntry.setWeekNum(rs.getInt("weekNum"));
				currentEntry.setPeriod(rs.getString("pay_period"));
				currentEntry.setApproved(rs.getBoolean("approved"));
				currentEntry.setCompleted(rs.getBoolean("completed"));
				allEntries.add(currentEntry);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allEntries;
	}
	
	public void DeleteTimeSheetEntry(int empId, String date) {
		String query = "delete from timesheet where empId = " + empId + " and date = '" + date + "'";
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isNotApprovedPeriod(String username, String period) {
		String sql = "select * from timesheet where empName = '" + username + "' and pay_period = '"
				+ period + "' and approved = " + false;
		try {
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void FindHours(String period) {
		allEmps = GetAllEmps(period);
		allNumOfHours = new ArrayList<>();
		for(int i = 0; i < allEmps.size(); i++) {
			allNumOfHours.add(GetNumOfHours(allEmps.get(i), period));
		}
	}
	
	public ArrayList<String> GetAllEmps() {
		return allEmps;
	}
	
	public ArrayList<Double> GetAllSalaries() {
		return allNumOfHours;
	}
	
	private ArrayList<String> GetAllEmps(String period) {
		ArrayList<String> allEmps = new ArrayList<>();
		String sql = "select * from timesheet where pay_period = '" + period + "'"
								+ " and approved =" + false;
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String name = rs.getString("empName");
				if(!allEmps.contains(name))
					allEmps.add(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allEmps;
	}

	public double GetNumOfHours(String name, String period) {
		String query = "select startTime, endTime from timesheet where empName = '" + name
											+ "' and pay_period = '" + period + "' and approved = " + false;
		double numOfHours = 0;
		try {
			ResultSet rs = statement.executeQuery(query);
			numOfHours = FindNumOfHours(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numOfHours;
	}
	
	public int FindWeekNum(String str) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Date date; int week = 0;
		try {
			date = dateFormat.parse(str);
			cal.setTime(date);
			week = cal.get(Calendar.WEEK_OF_YEAR);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return week;
	}
	
	public double FindNumOfHours(ResultSet resultSet) {
		StringBuilder builder = new StringBuilder();
		double numOfHours = 0;
		try {
			while(resultSet.next()) {
				Date startDate = timeFormat.parse(resultSet.getString("startTime"));
				Date endDate = timeFormat.parse(resultSet.getString("endTime"));
				long diffInHrs = ((endDate.getTime() - startDate.getTime()) / (60 * 60 * 1000));
				long diffInMins = (((endDate.getTime() - startDate.getTime()) / (60 * 1000)) % 60);
				builder.delete(0, builder.length());
				builder.append(diffInHrs).append(".").append(diffInMins);
				System.out.println(builder.toString());
				numOfHours = numOfHours + Double.parseDouble(builder.toString());
			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numOfHours;
	}
	
	public double GetNumOfHoursInAWeek(int empId, int weekNum) {
		String query = "select startTime, endTime from timesheet where empId = " + empId
							+ " and weekNum = " + weekNum;
		double numOfHours = 0;
		try {
			ResultSet rs = statement.executeQuery(query);
			numOfHours = FindNumOfHours(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numOfHours;
	}
	

	public void ApproveEmployees(String period, ArrayList<String> employees) {
		for(int i=0; i<employees.size(); i++) {
			ApproveByName(period, employees.get(i));
		}
	}
	
	public boolean ApproveByName(String period, String empName) {	
		String sql = "update timesheet set approved = " + true + " where empName = '"
						+ empName + "' and pay_period = '" + period + "'";
		int res = 0;
		try {
			res = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res>0;
	}
	

	public void MoveApprovedToPaid(String period, ArrayList<Salary> salaries) {
		for(int i=0; i<salaries.size(); i++) {
			InsertToSalary(period, salaries.get(i));
		}
	}
	
	public boolean InsertToSalary(String period, Salary salary) {
		String sql = "insert into salary values(?,?,?,?,?)";
		int res = 0;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, salary.getEmpId());
			stmt.setString(2, salary.getEmpName());
			stmt.setString(3, period);
			stmt.setDouble(4, salary.getNumOfHours());
			stmt.setDouble(5, salary.getSalary());
			res = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res>0;
	}
	
	public ArrayList<Salary> GetAllSalaryData(String period) {
		ArrayList<Salary> salaries = new ArrayList<>();
		String sql = "select * from salary where pay_period = '" + period + "'";
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				Salary salary = new Salary();
				salary.setEmpId(rs.getInt("empId"));
				salary.setEmpName(rs.getString("empName"));
				salary.setNumOfHours(rs.getDouble("numOfHours"));
				salary.setSalary(rs.getDouble("salary"));
				salaries.add(salary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salaries;
	}
	
	public void DisApproveAll() {
		String sql = "update timesheet set approved = false";
		String query = "delete from salary";
		try {
			statement.executeUpdate(sql);
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DBConnect connector = new DBConnect();
//		Employee anEmployee = new Employee("1001", "Jon Snow", "jonsnow", "forthewatch", "Night Watch", 2000);
		connector.OpenConnection();
		connector.DisApproveAll();
		System.out.println("Connection Open");
//		ArrayList<TimeSheet> allEntries = connector.GetTimeSheetEntries("jonsnow", "03-2");
//		System.out.println(allEntries.size());
//		connector.InsertEmployee(anEmployee);
//		connector.ApproveByName("04-1", "senthil");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			Date startDate = timeFormat.parse("2012-12-13 12:55:00");
			Date endDate = timeFormat.parse("2012-12-13 14:10:00");
			Date date = dateFormat.parse("2016-04-05");
//			connector.InsertTimeSheetEntry(1004, "Jon Snow", timeFormat.format(startDate), timeFormat.format(endDate),
//					dateFormat.format(date), 2, false, false);
//			connector.DeleteTimeSheetEntry(1001, dateFormat.format(date));
//			double num = connector.GetNumOfHours(1004, "2016-04-05", false);
//			System.out.println(num);
//			double numOfHours = connector.GetNumOfHoursInAWeek(1004, 2);
//			System.out.println(numOfHours);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		connector.CloseConnection();
		System.out.println("Connection Closed");
	}
}
