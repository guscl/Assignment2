<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="edu.unsw.comp9321.assign2.*,java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Page</title>
</head>
<body>
	<table border=1>
		<tr>
			<td>Title</td>
			<td>Description</td>
			<td>Reserve Price</td>
			<td>Lasting Minutes</td>
			<td>Id</td>

		</tr>
		<%
			if (request.getAttribute("SessionTracker") == null) {
				System.out.println("session is null");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			else {
				SessionBean sb = (SessionBean) request.getAttribute("SessionTracker");
				if (sb.getRole().equals("member")) {
					System.out.println("user: " + sb.getUser() + " is a " + sb.getRole());
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				}
			}
		%>
		<%
			
			AuctionReader auctionReader = new AuctionReader();
			ResultSet rs = auctionReader.getAuctions();
			AuctionBean auctionBean = new AuctionBean();
			while(rs.next()){
				auctionBean.setId(rs.getInt("id"));
				auctionBean.setUsername(rs.getString("username"));
				auctionBean.setStartTime(rs.getTime("starttime").toString());
				auctionBean.setAuctionLength(rs.getInt("auctionlength"));
				auctionBean.setStatus(rs.getString("status"));
				auctionBean.setTitle(rs.getString("title"));
				auctionBean.setCategory(rs.getString("category"));
				auctionBean.setPictureUrl(rs.getString("picture"));
				auctionBean.setDescription(rs.getString("description"));
				auctionBean.setPostageDetails(rs.getString("postagedetails"));
				auctionBean.setStartingPrice(rs.getInt("startingprice"));
				auctionBean.setReservePrice(rs.getInt("reserveprice"));
				auctionBean.setBiddingIncrement(rs.getInt("bidincrement"));
		%>

		<tr>
			<td><%=auctionBean.getTitle()%></td>
			<td><%=auctionBean.getDescription()%></td>
			<td><%=auctionBean.getReservePrice()%></td>
			<td>????</td>
			<td><%=auctionBean.getId()%></td>
		</tr>

		<%
			}
		%>

	</table><br>
	
	<table border=1>
		<tr>
			<td>Username</td>
			<td>Email</td>
			<td>Locked</td>

		</tr>
		<%
			UserDBManager userReader = new UserDBManager();
			ResultSet rs1 = userReader.getAllMembers();
			UserBean userBean = new UserBean();
			while(rs1.next()){
				userBean.setUserName(rs1.getString("username"));
				userBean.setNicknane(rs1.getString("nickname"));
				userBean.setFirstName(rs1.getString("firstname"));
				userBean.setLastName(rs1.getString("lastname"));
				userBean.setPassword(rs1.getString("password"));
				userBean.setEmail(rs1.getString("email"));
				userBean.setBirthYear(rs1.getInt("birthyear"));
				userBean.setAddress(rs1.getString("address"));
				userBean.setCreditCard(rs1.getString("creditcard"));
				userBean.setRole(rs1.getString("role"));
				userBean.setLocked(rs1.getBoolean("locked"));
		%>

		<tr>
			<td><%=userBean.getUserName()%></td>
			<td><%=userBean.getEmail()%></td>
			<td><%=userBean.isLocked()%></td>
			
		</tr>

		<%
			}
		%>

	</table><br>
	
	
	
	<form action="ControlServlet?action=admin" method ="post" >
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
	</form>
	<form action="ControlServlet?action=logout" method="post" >
		<input type="submit" name="logout" value="logout"> 
	</form>
</body>
</html>
