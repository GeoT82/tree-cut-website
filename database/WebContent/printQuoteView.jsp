<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client page</title>
</head>

<center><h1>Quote Print Page</h1> </center>

 
	<body>
	 <center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 <p>Here is your Quote print</p>
	</center>

		 
    <div align="center">
    
    	<table border="1" cellpadding="6">
            <caption><h2>Owner Info</h2></caption>
            <tr>
            	<th>ID</th>
            	<th>Name</th>
            </tr>
                <tr style="text-align:center">
                	<td>110</td>
                	<td>David Smith</td>
        </table>
    
        <table border="1" cellpadding="6">
            <caption><h2>Client Info</h2></caption>
            <tr>
            	<th>Client ID</th>
            	<th>Client Name</th>
                <th>Quote ID</th>
                <th>Estimated Cut Date</th>
                <th>Issue Date</th>
                <th>Price</th>
               	<th>Request ID</th>
               	
            </tr>
                <tr style="text-align:center">
                	<td>${quote.clientID}</td>
                	<td>${user.firstName} ${user.lastName}</td>
                    <td>${quote.quoteID}</td>
                    <td>${quote.time}</td>
                    <td>${quote.date}</td>
                    <td>${quote.price}</td>
                    <td>
                    	<a href="requestView?id=<c:out value='${quote.requestID}' />">${quote.requestID}</a>
                    </td>
        </table>
        
        <table border="1" cellpadding="6">
            <caption><h2>Quote Replies</h2></caption>
            <tr>
            	<th>Client ID</th>
            	<th>Reply ID</th>
                <th>Note</th>
                <th>Time Posted</th>
               	
            </tr>
            <c:forEach var="responses" items="${listResponses}">
                <tr style="text-align:center">
                	<td><c:out value="${responses.clientID}" /></td>
                	<td><c:out value="${responses.replyID}" /></td>
                    <td><c:out value="${responses.reply}" /></td>
                    <td><c:out value="${responses.issueDate}" /></td>
                    
            </c:forEach>
            
        </table>
	</div>
	</body>
</html>