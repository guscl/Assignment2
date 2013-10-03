<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item Registration for Auction</title>
</head>
<body>

<form name="userRegistration" action="controller" method="GET">
			<input type="hidden" name="action" value="search"/>
			<table>
			<tr>
				<td>Title</td>
				<td><input type="text" name="title" maxlength="20" /></td>
			</tr>
			<tr>
				<td>Category </td>
				<td><input type="text" name="category" maxlength="20" /></td>
			</tr>
			<tr>	
				<td>Picture Url</td>
				<td><input type="text" name="picture" maxlength="50" /></td>
			</tr>
			<tr>	
				<td>Description</td>
				<td><input type="text" name="description" maxlength="100" /></td>
			</tr>
			<tr>	
				<td>Postage Details</td>
				<td><input type="text" name="postage" maxlength="50" /></td>
			</tr>
			<tr>	
				<td>Reserve Price</td>
				<td><input type="text" name="reservePrice" maxlength="4" /></td>
			</tr>
			<tr>	
				<td>Bidding Starting Price</td>
				<td><input type="text" name="startingPrice" maxlength="100" /></td>
			</tr>
			<tr>	
				<td>Bidding Increments</td>
				<td><input type="text" name="biddingIncrements" maxlength="16" /></td>
			</tr>
			<tr>	
				<td>How many minutes should the auction last?(min 3 and max 60)</td>
				<td><input type="text" name="biddingHours" maxlength="2" /></td>
			</tr>

			<tr>
				<td><input type="submit" name="itemRegistration" value="Start Auction"></td>
			</tr>
			</table>
		</form>

</body>
</html>