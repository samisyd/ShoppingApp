<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.login.dao.DBUtils" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<style type="text/css">
body {background-color:lightgrey;}
h1	 {color:green;}
form {color:blue;} 
</style>
</head>
<body>	


<%!
	
	//LoginDao dao;
	public void jspInit() {
		//dao = new LoginDao();
		System.out.println("Inside LoginJSP Init");
	}
	
	public void jspDestroy() {
		
		System.out.println("Inside Loginjsp destroy");
		System.out.println("Deleting Database connection");
		DBUtils db = DBUtils.getInstance();
		try {
			db.finalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

%>
<h1> Welcome to Online shopping </h1>


<form name = "UserForm" action="Login" method="post">
		<table>
			<tbody>
			<tr>
				<td><label>Name :: </label></td>
				<td><input id="id_name" name="fullName" type="text"> </td>
			</tr>
			<tr>
				<td><label>Passwd :: </label></td>
				<td><input id="id_passwd" name="pass" type="password"> </td>
			</tr>						
			<tr align="center">
				<td colspan="2"><input type="submit" value="Login.."></td>	
			</tr>
			</tbody>			
		</table>		
	</form>

</body>
</html>