<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="Style.css" type="text/css" >
<title>Time Sheet</title>
</head>
<body bgcolor='#333333' font='Bodoni MT' >

<header id="header">
<p><h2>University of Central Missouri</h2></p>
</header>
<%@ include file="Logout.jsp" %>
<% 
	String text = request.getHeader("user-agent");
	if(text != null && text.contains("MSIE")) { %>
	<%= text %>
	<br>	Please use Google Chrome for better results...
<%	} %>
<p align="center">Position Selection</p>

<form action="PayPeriodServlet" method="post">
<table align="center" border="1" cellpadding="1" cellspacing="1" style="width:500px;">
	<thead>
		<tr>
			<th scope="col">Title and Department</th>
			<th scope="col">Pay Period</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><%= session.getAttribute("position") %></td>
			<td>
			<select name="pay_period" >
				<option value="03-2">Mar 16 to Mar 31</option>
				<option value="04-1">Apr 1 to Apr 15</option>
				<option value="04-2">Apr 16 to Apr 30</option>
				<option value="05-1">May 1 to May 15</option>
				<option value="05-2">May 16 to May 31</option></select>
			</td>
		</tr>
	</tbody>
</table>


<% if(request.getAttribute("isApproved") != null) {%>
	<br> The requested timesheet is already approved
<% } %>

<p align="center"> <input name="go" type="submit" value="Add new TimeSheet" /> <input name="go" type="submit" value="View TimeSheet" /> </p>

</form>

</body>
</html>