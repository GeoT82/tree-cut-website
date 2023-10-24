<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Activity page</title>
</head>

<center><h1>Welcome! You have been successfully logged in</h1> </center>

 
	<body>
	 <center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 <p> You can show all the orders and personal information here.</p>
	</center>
	
	<form action = "request">
		<input type = "submit" value = "Request Tree Cuts"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 
		 
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Requests</h2></caption>
            <tr>
                <th>Smith Note</th>
                <th>Client Note</th>
                <th>Tree Count</th>
               	<th>Request ID</th>
            </tr>
            <c:forEach var="requests" items="${listRequest}">
                <tr style="text-align:center">
                    <td><c:out value="${requests.smithNote}" /></td>
                    <td><c:out value="${requests.clientNote}" /></td>
                    <td><c:out value="${requests.treeCount}" /></td>
                    <td><c:out value="${requests.requestID}" /></td>
                    <td>
                    	<a href="treeView?id=<c:out value='${request.requestID}' />">View Trees</a>
                    </td>
            </c:forEach>
        </table>
	</div>
	</body>
</html>