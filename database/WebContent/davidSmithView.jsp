<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client page</title>
</head>

<center><h1>Welcome! You have been successfully logged in</h1> </center>

 
	<body>
	 <center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 <p>Select Your View</p>
	</center>
	
	
		 
    <div align="center">
        
		<form action = "seeQuotes" align="center">
		<input type = "submit" value = "See Your Quotes"/>
		</form>
		
		<br>
		<br>
		<br>
		
		<form action = "seeBills" align="center">
		<input type = "submit" value = "See Your Bills"/>
		</form>
	</div>
	</body>
</html>