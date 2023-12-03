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
	
	<a href="login.jsp"target ="_self" > logout</a><br><br> 


    <div align="center">
    	<h1>THIS IS DAVID SMITHS VIEW</h1>
        <table border="1" cellpadding="6">
            <caption><h2>List of Quotes</h2></caption>
            <tr>
            	<th>ClientID</th>
            	<th>Issue Date</th>
                <th>time</th>
                <th>Smith Note</th>
                <th>Client Note</th>
                <th>Price</th>
               	<th>Quote ID</th>
               	<th>Bill ID</th>
               	<th>Request ID</th>
               	<th>Attach Note</th>
               	<th>Print</th>
               	
            </tr>
            <c:forEach var="quotes" items="${listQuotes}">
                <tr style="text-align:center">
                	<td><c:out value="${quotes.clientID}" /></td>
                	<td><c:out value="${quotes.date}" /></td>
                    <td><c:out value="${quotes.time}" /></td>
                    <td><c:out value="${quotes.smithNote}" /></td>
                    <td><c:out value="${quotes.clientNote}" /></td>
                    <td><c:out value="${quotes.price}" /></td>
                    <td><c:out value="${quotes.quoteID}" /></td>
                    <td><c:out value="${quotes.billID}" /></td>
                    <td>
                    	<a href="requestView?id=<c:out value='${quotes.requestID}' />"><c:out value='${quotes.requestID}' /></a>
                    </td>
                    <td>
                    	<a href="createQuoteNote?id=<c:out value='${quotes.quoteID}' />">Post Note</a>
                    </td>
                    <td>
                    	<a href="quotePrint?id=<c:out value='${quotes.quoteID}' />">Print</a>
                    </td>
            </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>