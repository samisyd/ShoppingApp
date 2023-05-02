<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shop</title>
<style type="text/css">
body {background-color:lightgrey;}
h1	 {color:green;}
form {color:blue;} 
</style>
</head>
<body>
<h1> Enter the no of items to buy</h1>

	<%
	
		if (session.getAttribute("username")==null)
		{
			response.sendRedirect("Login.jsp");
		}
	%>

<form name = "UserForm" action="ShoppingRequest" method="post">
		<table>
			<tbody>
			<tr>
				<td><label>Jeans :: </label></td>
				<td><input id="id_jeansQty" name="JeansQty" type="text"> </td>
			</tr>
			<tr>
				<td><label>Shirt :: </label></td>
				<td><input id="id_shirtQty" name="ShirtQty" type="text"> </td>
			</tr>						
			<tr align="center">
				<td colspan="2"><input type="submit" value="Shop"></td>	
			</tr>
			</tbody>			
		</table>		
	</form>

</body>
</html>