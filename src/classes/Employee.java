package classes;

public class Employee {
	private String empId;
	private String empName;
	private String username;
	private String password;
	private String position;
	private double salary;
	
	public Employee() {
		
	}
	
	public Employee(String empId, String empName, String username, String password, String position, double salary) {
		this.empId = empId;
		this.empName = empName;
		this.username = username;
		this.password = password;
		this.position = position;
		this.salary = salary;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
}
