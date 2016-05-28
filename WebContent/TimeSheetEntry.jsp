<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="Style.css" type="text/css" >
<title>Enter TimeSheet</title>
</head>
<body bgcolor='#333333' font='Bodoni MT'>
<header id="header">
<p><h2>University of Central Missouri</h2></p>
</header>
<%@ include file="Logout.jsp" %>

<h4> Enter your start and end time:</h4>


<form action="InsertTimeSheet" method="post">

<p align="center">Choose Date:
<select name="all-days">
<option selected="selected" value="">--------</option>
<%  java.util.ArrayList<String> days = (java.util.ArrayList<String>)session.getAttribute("all-days");
	if(days != null)
		for(int i = 0; i < days.size(); i++) { %>
			<option selected="selected"><%=days.get(i) %></option>
<% } %>
</select></p>

<p align="center">Start Time:<input name="startTime" size="5" type="text" />  hh:mm </p>

<p align="center">EndTime: <input name="endTime" size="5" type="text" />  hh:mm </p>

<p align="center"> <input name="button" type="submit" value="Timesheet Home" />  &nbsp; &nbsp;   <input name="button" type="submit" value="Save" /> </p>

</form>


<% if("success".equals(request.getAttribute("result"))) { %>
	<ul>
		<li><span class="marker"><font color="#00ff00">Timesheet entry saved!!!</font></span></li>
	</ul>
<% } else if("failure".equals(request.getAttribute("result"))) { %>
	<ul>
		<li><span class="marker"><font color="#ff0000">Error in Timesheet entry!!!</font></span></li>
	</ul>
<% } %>

</body>

</html>