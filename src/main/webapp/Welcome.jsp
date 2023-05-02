<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
<style type="text/css">
body {background-color:lightgrey;}
h1	 {color:green;}
form {color:blue;} 
</style>

</head>
<body>

	<%
	
		if (session.getAttribute("username")==null)
		{
			response.sendRedirect("Login.jsp");
		}
	%>
	
	<h1>Welcome ${username} </h1>
	

	<h3 style="color: blue; font-family : serif; font-style:italic" > Shop
	<a href="./Shopping.jsp"> Shop</a>
	</h3>
	
	<h3 style="color: blue; font-family : serif; font-style:italic" > Purchase History
	<a href="./PurchaseHistory.jsp"> Show Purchase History</a>
	</h3>

</body>
</html>