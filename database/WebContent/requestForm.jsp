<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Registration</title></head>
<body>
	<div align="center">
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<h2>Enter Info For The First Tree</h2>
		<p>You can add more trees later</p>
		<form action="submitRequest">
			<table border="1" cellpadding="5">
				<tr>
					<th>Note: </th>
					<td align="center" colspan="3">
						<input type="text" name="note" size="150" value="Pending" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Image 1:</th>
					<td align="center" colspan="3">
						<input type="text" name="image1" size="150" value="Pending" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Image 2:</th>
					<td align="center" colspan="3">
						<input type="text" name="image2" size="150" value="Pending" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Image 3:</th>
					<td align="center" colspan="3">
						<input type="text" name="image3" size="150" value="Pending" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Address: </th>
					<td align="center" colspan="3">
						<input type="text" name="address" size="48" value="123 street, city, state" onfocus="this.value=''" required>
					</td>
	
				</tr>
				<tr>
					<th>distance (feet): </th>
					<td align="center" colspan="3">
						<input type="text" name="distance" size="11" value="ex. 12.5" onfocus="this.value=''" required>
					</td>
	
				</tr>
				<tr>
					<th>width (feet): </th>
					<td align="center" colspan="3"> 
						<input type="text" name="width" size="11" value="ex. 12.5" onfocus="this.value=''" required>
					</td>
				</tr>
				<tr>
					<th>height (feet): </th>
					<td align="center" colspan="3">
						<input type="text" name="height" size="11" value="ex. 12.5" onfocus="this.value=''" required>
					</td>
				
				</tr>
				<tr>
				
				</tr>
			</table>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Open A Request"/>
					</td>
				</tr>
			</table>
			<a href="login.jsp" target="_self">Return to Login Page</a>
		</form>
	</div>
</body>