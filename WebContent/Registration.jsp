<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Registration Page</title>
</head>
<body>

<form name="userRegistration" action="controller" method="GET">
			<input type="hidden" name="action" value="search"/>
			<table>
			<tr>
				<td>Username</td>
				<td><input type="text" name="username" maxlength="20" /></td>
			</tr>
			<tr>
				<td>Nickname </td>
				<td><input type="text" name="nickname" maxlength="20" /></td>
			</tr>
			<tr>	
				<td>First Name</td>
				<td><input type="text" name="firstname" maxlength="50" /></td>
			</tr>
			<tr>	
				<td>Last Name</td>
				<td><input type="text" name="lastname" maxlength="50" /></td>
			</tr>
			<tr>	
				<td>Email</td>
				<td><input type="text" name="email" maxlength="50" /></td>
			</tr>
			<tr>	
				<td>Year of Birth</td>
				<td><input type="text" name="birthyear" maxlength="4" /></td>
			</tr>
			<tr>	
				<td>Full Address</td>
				<td><input type="text" name="address" maxlength="100" /></td>
			</tr>
			<tr>	
				<td>Credit Card Number</td>
				<td><input type="text" name="creditcard" maxlength="16" /></td>
			</tr>

			<tr>
				<td><input type="submit" name="signIn" value="Sign In"></td>
			</tr>
			</table>
		</form></body>
</html>