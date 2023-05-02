<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Page</title>
</head>
<body>
	Error in data entered
	
	<%
	
		if (session.getAttribute("username")==null)
		{
			response.sendRedirect("Login.jsp");
		}
	%>
	
	<h1 style="color: blue; font-family : serif; font-style:italic" > ReLogin
	<a href="./Login.jsp"> Login</a>
	</h1>
	
</body>
</html>