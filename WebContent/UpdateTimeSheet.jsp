<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="classes.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="Style.css" type="text/css" >
<title>View Timesheet Entries</title>
</head>
<body  bgcolor='#333333' font='Bodoni MT'>
<header id="header">
<p><h2>University of Central Missouri</h2></p>
</header>
<%@ include file="Logout.jsp" %>
<h4>Timesheet Entries</h4>

<p><label>Employee ID :</label> <%= (request.getAttribute("empId") != null) ? request.getAttribute("empId") : "" %> </p>

<p><label>Employee Name:</label> <%= (request.getAttribute("empName") != null) ? request.getAttribute("empName") : "" %> </p>

<table align="center" border="1" cellpadding="1" cellspacing="1" style="width:500px;">
	<thead>
		<tr>
			<th scope="col"><span style="font-weight: bold; text-align: center;">Date</span></th>
			<th scope="col">Start Time</th>
			<th scope="col">End Time</th>
		</tr>
	</thead>
	<tbody>
		
		<%
			ArrayList<TimeSheet> allEntries = (ArrayList<TimeSheet>) request.getAttribute("all-entries");
		if(allEntries != null)
			for(int i=0; i< allEntries.size(); i++) { 
				String start = allEntries.get(i).getStartTime();
				String end = allEntries.get(i).getEndTime();
				start = start.substring(11, 16);
				end = end.substring(11, 16);
				%>
				<tr>
				<td> <%= allEntries.get(i).getDate() %> </td>
				<td> <%= start %></td>
				<td> <%= end %></td>
				</tr>	
		<%	}
		%>
		
	</tbody>
</table>

<p>&nbsp;</p>
</body>
</html>