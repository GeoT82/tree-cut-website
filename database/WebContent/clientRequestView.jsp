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
		 <p>all the requests.</p>
	</center>
	
	<form action = "requestForm" align="center">
		<input type = "submit" value = "Start A Request"/>
	</form>
		 
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Requests</h2></caption>
            <tr>
            	<th>Client ID</th>
            	<th>Issue Date</th>
                <th>Smith Note</th>
                <th>Client Note</th>
               	<th>Request ID</th>
                <th>Quote ID</th>
                <th>Trees</th>
                <th>Post</th>
                <th>New Tree</th>
            </tr>
            <c:forEach var="requests" items="${listRequest}">
                <tr style="text-align:center">
                	<td><c:out value="${requests.clientID}" /></td>
                	<td><c:out value="${requests.date}" /></td>
                    <td><c:out value="${requests.smithNote}" /></td>
                    <td><c:out value="${requests.clientNote}" /></td>
                    <td><c:out value="${requests.requestID}" /></td>
                    <td><c:out value="${requests.quoteID}" /></td>
                    <td>
                    	<a href="treeView?id=<c:out value='${requests.requestID}' />">View Trees</a>
                    </td>
                    <td>
                    	<a href="createRequestNote?id=<c:out value='${requests.requestID}' />">Post Note</a>
                    </td>
                    <td>
                    	<a href="addTreeView?id=<c:out value='${requests.requestID}' />">Add Tree</a>
                    </td>
            </c:forEach>
        </table>
	</div>
	</body>
</html>