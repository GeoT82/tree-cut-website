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
		 <p>all the bills.</p>
	</center>

		 
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Bills</h2></caption>
            <tr>
            	<th>ClientID</th>
                <th>Bill ID</th>
                <th>Issue Date</th>
                <th>Due Date</th>
                <th>Smith Note</th>
                <th>Client Note</th>
                <th>Price</th>
                <th>Pay Status</th>
               	<th>Quote ID</th>
               	<th>Attach Note</th>
               	<th>Print Log</th>
               	
            </tr>
            <c:forEach var="bills" items="${listBills}">
                <tr style="text-align:center">
                	<td><c:out value="${bills.clientID}" /></td>
                    <td><c:out value="${bills.billID}" /></td>
                    <td><c:out value="${bills.issueDate}" /></td>
                    <td><c:out value="${bills.dueDate}" /></td>
                    <td><c:out value="${bills.smithNote}" /></td>
                    <td><c:out value="${bills.clientNote}" /></td>
                    <td><c:out value="${bills.price}" /></td>
                    <td><c:out value="${bills.payStatus}" /></td>
                    <td>
                    	<a href="quoteView?id=<c:out value='${bills.quoteID}' />"><c:out value='${bills.quoteID}' /></a>
                    </td>
                    <td>
                    	<a href="createBillNote?id=<c:out value='${bills.billID}' />">Post Note</a>
                    </td>
                    <td>
                    	<a href="printLog?id=<c:out value='${bills.billID}' />">Post Note</a>
                    </td>
            </c:forEach>
        </table>
	</div>
	</body>
</html>