package classes;

public class Salary {
	private int empId;
	private String empName;	
	private double numOfHours;
	private String position;
	private double salary = 0;
	private boolean isApproved;

	public Salary() {}
	
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
	public double getNumOfHours() {
		return numOfHours;
	}
	public void setNumOfHours(double numOfHours) {
		this.numOfHours = numOfHours;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
}
