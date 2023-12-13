<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client page</title>
</head>

<center><h1>Bill Print Page</h1> </center>

 
	<body>
	 <center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 <p>Here is your bill print</p>
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
            	<th>ClientID</th>
            	<th>Client Name</th>
                <th>Bill ID</th>
                <th>Issue Date</th>
                <th>Due Date</th>
                <th>Price</th>
                <th>Pay Status</th>
               	<th>Quote ID</th>
               	
            </tr>
                <tr style="text-align:center">
                	<td>${bill.clientID}</td>
                	<td>${user.firstName} ${user.lastName}</td>
                    <td>${bill.billID}</td>
                    <td>${bill.issueDate}</td>
                    <td>${bill.dueDate}</td>
                    <td>${bill.price}</td>
                    <td>${bill.payStatus}</td>
                    <td>
                    	<a href="quoteView?id=<c:out value='${bill.quoteID}' />">${bill.quoteID}</a>
                    </td>
        </table>
        
        <table border="1" cellpadding="6">
            <caption><h2>Bill Replies</h2></caption>
            <tr>
            	<th>ClientID</th>
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