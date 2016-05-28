
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<%@ include file="Logout.jsp" %>
<p>Hello 
<%= request.getAttribute("username") %>
 !!!
</p>

<p style="text-align: center;"><a href="TimeSheet.jsp">TimeSheet</a></p>

<p style="text-align: center;"><a href="http://linkPayInfo">Pay Information</a></p>

<% if("yes".equals(request.getAttribute("admin"))) { %>
	<p style="text-align: center;"><a href="AdminUpdate.jsp">Admin Page</a></p>	
<% } %>

</body>
</html>