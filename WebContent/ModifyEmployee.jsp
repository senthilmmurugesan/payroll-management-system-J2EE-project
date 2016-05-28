<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="Style.css" type="text/css" >
<title>Modify Employee</title>
</head>
<body bgcolor='#333333' font='Bodoni MT'>
<header id="header">
<p><h2>University of Central Missouri</h2></p>
</header>
<%@ include file="Logout.jsp" %>
<h4>Add/Modify Employee</h4>
<% if("success".equals(request.getAttribute("result"))) { %>
	<font color="#00ff00"><h5>Success</h5></font>
<% } %>
<% if("failure".equals(request.getAttribute("result"))) { %>
	<font color="#ff0000"><h5>Failed...Wrong details</h5></font>
<% } %>
<% if("password".equals(request.getAttribute("result"))) { %>
	<font color="#ff0000"><h5>Password doesn't match</h5></font>
<% } %>
<% if("empty".equals(request.getAttribute("result"))) { %>
	<font color="#ff0000"><h5>Please enter all the fields</h5></font>
<% } %>

<form action="ModifyEmployee" method="post">

<label>Employee ID :</label> <input name="empId" type="text"
value = "${employeeBean.empId }"/> 
 <input name="button" type="submit" value="Search" /></p>

<label>Employee Name:</label> <input name="empName" type="text"
value = "${employeeBean.empName }" 
/></p>

<label>Username:</label> <input name="username" type="text" 
value = "${employeeBean.username }"
/></p>

<% if("no".equals(request.getAttribute("hide-passwd"))) { %>

<label>Password:</label> <input name="password" type="password" /></p>

<label>Confirm Password:</label> <input name="confirmPassword" type="password" /></p>

<% } %>
</p>
<label>Position:</label> <input name="position" type="text" 
value = "${employeeBean.position }"/></p>

<label>Salary:</label> <input name="salary" type="text" 
value = "${employeeBean.salary }"/></p>

<p></p>

<p><input name="button" type="submit" value="Add Employee" />  <input name="button" type="submit" value="Update" /> <input name="button" type="submit" value="Delete" />  <input name="button" type="submit" value="Cancel" /></p>

<p style="text-align: center;"></p>

<p></p>

</form>
</body>
</html>