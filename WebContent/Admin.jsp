<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Page</title>
</head>
<body>
	<table>
		<%
			//For fullfiling a list of bids
			int i = 0;
		%>

		<tr>
			<td><%=i%></td>
			<td><%=i++%></td>
			<td><%=i--%></td>
		</tr>

		<%
			//end of for
		%>

	</table>

	<tr>
		<td>Halt Auction with the following Id:</td>
		<td><input type="text" name="haltAuction" maxlength="20" />
		<td><input type="submit" name="haltButton" value="Halt"> 
		</td><br>
		
		<td>Remove Item with the following id:</td>
		<td><input type="text" name="removeItem" maxlength="20" />
		<td><input type="submit" name="removeItemButton" value="Remove item"> 
		</td><br>
		
		<td>Ban user with the following user name from the site:</td>
		<td><input type="text" name="removeUser" maxlength="20" />
		<td><input type="submit" name="removeUserButton" value="Remove user"> 
		</td>
	</tr>
</body>
</html>