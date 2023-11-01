<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Registration</title></head>
<body>
	<div align="center">
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<form action="quoted">
			<table border="1" cellpadding="5">
				<tr>
					<th>Price: </th>
					<td align="center" colspan="3">
						<input type="text" name="price" size="45"  value="$00.00" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Time: </th>
					<td align="center" colspan="3">
						<input type="text" name="time" size="45" value="1:00:00" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Note: </th>
					<td align="center" colspan="3">
						<input type="text" name="note" size="150" value="Pending" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Create"/>
					</td>
				</tr>
			</table>
			<a href="login.jsp" target="_self">Return to Login Page</a>
		</form>
	</div>
</body>