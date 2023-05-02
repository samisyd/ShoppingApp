<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Added</title>
</head>
<body>
	
	<%
	
		if (session.getAttribute("username")==null)
		{
			response.sendRedirect("Login.jsp");
		}
	%>

	<h1>Items added successfully</h1>
	
	<h2 style="color: blue; font-family : serif; font-style:italic" > Continue
	<a href="./Welcome.jsp"> Continue</a>
	</h2>
	
	<h2 style="color: blue; font-family : serif; font-style:italic" > Logout
	<a href="./Logout.jsp"> Logout</a>
	</h2>

</body>
</html>