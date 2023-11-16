<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Request List</title>
</head>

 
	<body>
	 <center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
	</center>
		 
    <div align="center">
    
        <table border="1" cellpadding="6">
            <caption><h2>List of Requests</h2></caption>
            <tr>
            	<th>clientID</th>
            	<th>Issue Date</th>
                <th>Smith Note</th>
                <th>Client Note</th>
               	<th>Request ID</th>
               	<th>Quote ID</th>
               	<th>Generate Quote</th>
               	<th>Attach Note</th>
            </tr>
            <c:forEach var="requests" items="${listPendRequest}">
                <tr style="text-align:center">
                	<td><c:out value="${requests.clientID}" /></td>
                	<td><c:out value="${requests.date}" /></td>
                    <td><c:out value="${requests.smithNote}" /></td>
                    <td><c:out value="${requests.clientNote}" /></td>
                    <td><c:out value="${requests.requestID}" /></td>
                    <td><c:out value="${requests.quoteID}" /></td>
                    <td>
                    	<a href="createQuote?id=<c:out value='${requests.requestID}' />">Quote</a>
                    </td>
                    <td>
                    	<a href="createRequestNote?id=<c:out value='${requests.requestID}' />">Post Note</a>
                    </td>
            </c:forEach>
        </table>
	</div>
	</body>
</html>