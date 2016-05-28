<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "java.util.*" %>
    <%@ page import = "classes.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="Style.css" type="text/css" >
<title>Admin Home</title>
</head>
<body bgcolor='#333333'  style="size:10px">
<header id="header">
<p><h2>University of Central Missouri</h2></p>
</header>
<%@ include file="Logout.jsp" %>
<h5>Timesheet Entries</h5>
<% ArrayList<Salary> salaries = (ArrayList<Salary>)request.getAttribute("table-data"); %>
<% ArrayList<Salary> salary_data = (ArrayList<Salary>)request.getAttribute("salary-data"); %>

<form action="AdminUpdateServlet" method="post">

<% String selected = (String) request.getAttribute("selected"); %>
<p>Pay Period : &nbsp;
<select name="pay_period" >
	<option value="03-2" <%= ("03-2".equals(selected))? "selected" : "" %> >Mar 16 to Mar 31</option>
	<option value="04-1" <%= ("04-1".equals(selected))? "selected" : "" %> >Apr 1 to Apr 15</option>
	<option value="04-2" <%= ("04-2".equals(selected))? "selected" : "" %> >Apr 16 to Apr 30</option>
	<option value="05-1" <%= ("05-1".equals(selected))? "selected" : "" %> >May 1 to May 15</option>
	<option value="05-2" <%= ("05-2".equals(selected))? "selected" : "" %> >May 16 to May 31</option></select>&nbsp;&nbsp;
<input name="button" type="submit" value="Load" /> 

<a  href="ModifyEmployee" style="float:right">Add/Modify an Employee</a>
</p>


<p style="text-align: center;">&nbsp;</p>

<table align="center" border="1" cellpadding="1" cellspacing="1" style="width:800px;">
	<thead>
		<tr>
			<th height="100" scope="col">Emp ID</th>
			<th height="100" scope="col">Employee Name</th>
			<th height="100" scope="col">Num of Hours</th>
			<th height="100" scope="col">Position</th>
			<th height="100" scope="col">Salary</th>
			<th height="100" scope="col">Approved</th>
		</tr>
	</thead>
	<tbody>
		
		
		<%	if(salaries != null) {
				for(int i = 0; i < salaries.size(); i++) {%>
					<tr>
					<td> <%= salaries.get(i).getEmpId() %> </td>
					<td> <%= salaries.get(i).getEmpName() %> </td>
					<td> <%= salaries.get(i).getNumOfHours() %> </td>
					<td> <%= salaries.get(i).getPosition() %> </td>
					<td> <%= salaries.get(i).getSalary() %> </td>
					<td> <%= salaries.get(i).isApproved() %> </td>
					</tr>
		<% }} %>
		
	</tbody>
</table>

<p style="text-align: center;"><input name="button" type="submit" value="Approve" /></p>

<h5>Other Pay Information</h5>

<table align="center" border="1" cellpadding="1" cellspacing="1" style="width:800px;">
	<thead>
		<tr>
			<th scope="col">Emp Id</th>
			<th scope="col">Employee Name</th>
			<th scope="col">Num of Hours</th>
			<th scope="col">Salary</th>
		</tr>
	</thead>
	<tbody>
	
		<%	if(salary_data != null) {
				for(int i = 0; i < salary_data.size(); i++) {%>
					<tr>
					<td> <%= salary_data.get(i).getEmpId() %> </td>
					<td> <%= salary_data.get(i).getEmpName() %> </td>
					<td> <%= salary_data.get(i).getNumOfHours() %> </td>
					<td> <%= salary_data.get(i).getSalary() %> </td>
					</tr>
		<% }} %>
	</tbody>
</table>


</form>
</body>
</html>