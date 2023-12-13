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
            	<th>ClientID</th>
            	<th>Issue Date</th>
                <th>time</th>
                <th>Smith Note</th>
                <th>Client Note</th>
                <th>Price</th>
               	<th>Quote ID</th>
               	<th>Request ID</th>
               	<th>Attach Note</th>
               	<th>Agree</th>
               	<th>Quit</th>
            </tr>
            <c:forEach var="quotes" items="${listQuote}">
                <tr style="text-align:center">
                	<td><c:out value="${quotes.clientID}" /></td>
                	<td><c:out value="${quotes.date}" /></td>
                    <td><c:out value="${quotes.time}" /></td>
                    <td><c:out value="${quotes.smithNote}" /></td>
                    <td><c:out value="${quotes.clientNote}" /></td>
                    <td><c:out value="${quotes.price}" /></td>
                    <td><c:out value="${quotes.quoteID}" /></td>
                    <td>
                    	<a href="requestView?id=<c:out value='${quotes.requestID}' />"><c:out value='${quotes.requestID}' /></a>
                    </td>
                    <td>
                    	<a href="createQuoteNote?id=<c:out value='${quotes.quoteID}' />">Post Note</a>
                    </td>
                    <td>
                    	<a href="quoteAgree?id=<c:out value='${quotes.quoteID}' />">Agree</a>
                    </td>
                    <td>
                    	<a href="quoteQuit?id=<c:out value='${quotes.quoteID}' />">Quit</a>
                    </td>
            </c:forEach>
        </table>
	</div>
	</body>
</html>