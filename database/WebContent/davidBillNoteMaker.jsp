<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Post Note</title></head>
<body>
	<div align="center">
		<p> ${errStr} </p>
		<form action="postDavidBillNote">
			<table border="1" cellpadding="5">
				<tr>
					<th>Note: </th>
					<td align="center" colspan="3">
						<input type="text" name="note" size="150" value="Pending" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Discount: </th>
					<td align="center" colspan="3">
						<input type="text" name="discount" size="10" value="Enter Percentage" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Post"/>
					</td>
				</tr>
			</table>
			<a href="login.jsp" target="_self">Return to Login Page</a>
		</form>
	</div>
</body>