<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>David Smith page</title>
</head>
<body>

<div align = "center">
	
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all users</h1>
    <div align="center">
    	<h1>THIS IS DAVID SMITHS VIEW</h1>
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>time</th>
                <th>Smith Note</th>
                <th>Client Note</th>
                <th>Price</th>
               	<th>Quote ID</th>
               	<th>Request ID</th>
            </tr>
            <c:forEach var="quotes" items="${listQuote}">
                <tr style="text-align:center">
                    <td><c:out value="${quotes.time}" /></td>
                    <td><c:out value="${quotes.smithNote}" /></td>
                    <td><c:out value="${quotes.clientNote}" /></td>
                    <td><c:out value="${quotes.price}" /></td>
                    <td><c:out value="${quotes.quoteID}" /></td>
                    <td>
                    	<a href="requestView?id=<c:out value='${quotes.requestID}' />"><c:out value='${quotes.requestID}' /></a>
                    </td>
            </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>