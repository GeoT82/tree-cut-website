<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
</head>
<body>

<div align = "center">
	
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 
	
	<form action = "seeStats" align="center">
			<input type = "submit" value = "See Customer Statistics"/>
	</form>

<h1>THIS IS ROOT VIEW</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Big Clients</h2></caption>
            <tr>
                <th>ClientID</th>
                <th>Tree Count</th>
            </tr>
            <c:forEach var="bigClient" items="${bigClient}">
                <tr style="text-align:center">
                    <td><c:out value="${bigClient.clientID}" /></td>
                    <td><c:out value="${bigClient.treeCount}" /></td>
            </c:forEach>
        </table>
        
        <br>
        
         <table border="1" cellpadding="6">
            <caption><h2>Easy Clients</h2></caption>
            <tr>
                <th>ClientID</th>
            </tr>
            <c:forEach var="easyClient" items="${easyClient}">
                <tr style="text-align:center">
                    <td><c:out value="${easyClient.clientID}" /></td>
            </c:forEach>
        </table>
        
        
         <table border="1" cellpadding="6">
            <caption><h2>One Tree Quotes</h2></caption>
            <tr>
                <th>Quote ID</th>
                <th>Request ID</th>
            </tr>
            <c:forEach var="oneTreeQuote" items="${oneTreeQuote}">
                <tr style="text-align:center">
                    <td><c:out value="${oneTreeQuote.quoteID}" /></td>
                    <td><c:out value="${oneTreeQuote.requestID}" /></td>
            </c:forEach>
        </table>
        
        <table border="1" cellpadding="6">
            <caption><h2>Prospective Clients</h2></caption>
            <tr>
                <th>ClientID</th>
            </tr>
            <c:forEach var="prospectiveClients" items="${prospectiveClients}">
                <tr style="text-align:center">
                    <td><c:out value="${prospectiveClients.clientID}" /></td>
            </c:forEach>
        </table>
        
        <table border="1" cellpadding="6">
            <caption><h2>Highest Trees</h2></caption>
            <tr>
                <th>Tree</th>
                <th>Height</th>
            </tr>
            <c:forEach var="highestTree" items="${highestTree}">
                <tr style="text-align:center">
                    <td><c:out value="${highestTree.treeID}" /></td>
                    <td><c:out value="${highestTree.height}" /></td>
            </c:forEach>
        </table>
        
        <table border="1" cellpadding="6">
            <caption><h2>Overdue Bills</h2></caption>
            <tr>
                <th>Bill ID</th>
            </tr>
            <c:forEach var="overdueBills" items="${overdueBills}">
                <tr style="text-align:center">
                    <td><c:out value="${overdueBills.billID}" /></td>
            </c:forEach>
        </table>
        
         <table border="1" cellpadding="6">
            <caption><h2>Bad Clients</h2></caption>
            <tr>
                <th>Client ID</th>
            </tr>
            <c:forEach var="badClients" items="${badClients}">
                <tr style="text-align:center">
                    <td><c:out value="${badClients.clientID}" /></td>
            </c:forEach>
        </table>
        
         <table border="1" cellpadding="6">
            <caption><h2>Good Clients</h2></caption>
            <tr>
                <th>Client ID</th>
            </tr>
            <c:forEach var="goodClients" items="${goodClients}">
                <tr style="text-align:center">
                    <td><c:out value="${goodClients.clientID}" /></td>
            </c:forEach>
        </table>
        
        
         <table border="1" cellpadding="6">
            <caption><h2>Client Tree Count</h2></caption>
            <tr>
                <th>Client ID</th>
                <th>Tree Count</th>
            </tr>
            <c:forEach var="treeCount" items="${treeCount}">
                <tr style="text-align:center">
                    <td><c:out value="${treeCount.clientID}" /></td>
                    <td><c:out value="${treeCount.treeCount}" /></td>
            </c:forEach>
        </table>
        
        <table border="1" cellpadding="6">
            <caption><h2>Client Total Bill Amounts</h2></caption>
            <tr>
                <th>Client ID</th>
                <th>Total Bills Sum</th>
            </tr>
            <c:forEach var="clientTotal" items="${clientTotal}">
                <tr style="text-align:center">
                    <td><c:out value="${clientTotal.clientID}" /></td>
                    <td><c:out value="${clientTotal.price}" /></td>
            </c:forEach>
        </table>
        
        
        <table border="1" cellpadding="6">
            <caption><h2>Client Total Bill Amounts PAID</h2></caption>
            <tr>
                <th>Client ID</th>
                <th>Total Bills PAID Sum</th>
            </tr>
            <c:forEach var="clientPaid" items="${clientPaid}">
                <tr style="text-align:center">
                    <td><c:out value="${clientPaid.clientID}" /></td>
                    <td><c:out value="${clientPaid.price}" /></td>
            </c:forEach>
        </table>
        
        
         <table border="1" cellpadding="6">
            <caption><h2>Client Tree Stats</h2></caption>
            <tr>
                <th>Client ID</th>
                <th>Request ID</th>
                <th>Tree ID</th>
                <th>Cut Date</th>
            </tr>
            <c:forEach var="treeDetails" items="${treeDetails}">
                <tr style="text-align:center">
                    <td><c:out value="${treeDetails.clientID}" /></td>
                    <td><c:out value="${treeDetails.requestID}" /></td>
                    <td><c:out value="${treeDetails.treeID}" /></td>
                    <td><c:out value="${treeDetails.date}" /></td>
            </c:forEach>
        </table>
        
	</div>
	</div>

</body>
</html>