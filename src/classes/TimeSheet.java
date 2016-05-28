package classes;

public class TimeSheet {
	private int empId;
	private String empName;
	private String startTime;
	private String endTime;
	private String date;
	private int weekNum;
	private String period;
	private boolean isApproved;
	private boolean isCompleted;
	
	public TimeSheet(int empId, String empName, String startTime, String endTime, String date, int weekNum,
			String period, boolean isApproved, boolean isCompleted) {
		this.empId = empId;
		this.empName = empName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
		this.weekNum = weekNum;
		this.period = period;
		this.isApproved = isApproved;
		this.isCompleted = isCompleted;
	}
	
	public TimeSheet() { }
		
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getWeekNum() {
		return weekNum;
	}
	public void setWeekNum(int weekNum) {
		this.weekNum = weekNum;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
}
