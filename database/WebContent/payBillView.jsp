<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Pay Bill</title></head>
<body>
	<div align="center">
		<p> ${cardStr} </p>
		<form action="payBill">
			<table border="1" cellpadding="5">
				<tr>
					<td align="center" colspan="3">
						<h2>BillID: '${bID}'</h2>
					</td>
				</tr>
				
				<tr>
					<td align="center" colspan="3">
						<h2>Price: '${price}'</h2>
					</td>
				</tr>
				
				<tr>
					<td align="center" colspan="3">
						<input type="text" name="cardNum" maxsize="16" value="16-Digit Card Number (NO SPACES)" onfocus="this.value=''" maxlength="16">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Pay"/>
					</td>
				</tr>
			</table>
			<a href="login.jsp" target="_self">Return to Login Page</a>
		</form>
	</div>
</body>