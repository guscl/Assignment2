<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>
		<%
			//For fullfiling a list of bids
			int i = 0;
		%>

		<tr>
			<td><%=i %></td>
			<td><%=i++ %></td>
			<td><%=i-- %></td>
		</tr>

		<%
			//end of for
		%>

	</table>
	<br><a href="ItemRegistration.jsp">Place an item for auction</a><br>
	 
</body>
</html>