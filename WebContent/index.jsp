<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="Style.css" type="text/css" >
<title>Login : MyCentral</title>
</head>

<body>
<div id="page">
<header id="header">
<p><h2>University of Central Missouri</h2></p>
</header>

<h2 style="text-align: center;">Payroll Management</h2>
<form action="LoginValidator" method="post">
<p style="text-align: center;">Username: &nbsp; &nbsp;<input name="username" size="20" type="text"
<% if(request.getAttribute("username") != null) {%>
	 value = <%= request.getAttribute("username") %>
<% } %> 
/></p>

<p style="text-align: center;">Password: &nbsp; &nbsp;&nbsp;<input name="password" size="20" type="password" /></p>

<p style="text-align: center;"><input name="login" type="submit" value="Login" /></p>
<% if("Error".equals(String.valueOf(request.getAttribute("result")))) { %>
	<ul>
		<li><span class="marker"><font color="#ff0000">Incorrect Username/Password Combination</font></span></li>
	</ul>
<% } %>
</form>
</div>
</body>
</html>