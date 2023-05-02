<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PurchaseHistory</title>
<style type="text/css">
body {background-color:lightgrey;}
h1	 {color:green;}
form {color:blue;} 
</style>
</head>
<body>
	<h1>Shows last Order history</h1>
	
	<%
	
		if (session.getAttribute("username")==null)
		{
			response.sendRedirect("Login.jsp");
		}
	%>
	
	<form name = "UserForm" action="PurchaseHistory">
		<table>
			<tbody>
			<tr>
				<td><label>Number of records :: </label></td>
				<td><input id="id_purchaseHistory" name="noOfRec" type="text"> </td>
			</tr>									
			<tr align="center">
				<td colspan="2"><input type="submit" value="OrderHistory"></td>	
			</tr>
			</tbody>			
		</table>		
	</form>
</body>
</html>