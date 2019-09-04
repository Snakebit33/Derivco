<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
	<h1 class="page-header">Accounts</h1>
	<c:if test="${not empty accounts}">
	    <table class="table table-striped">
	        <thead>
	        	<tr>
	            	<th>Account number</th>
	            	<th>Balance</th>
	            </tr>
	        </thead>
	        <tbody>
		        <c:forEach var="a" items="${accounts}">
		            <tr>
		                <td># ${a.nr}</td>
		                <td>${a.balance} EUR</td>
		            </tr>
		        </c:forEach>
	        </tbody>
	    </table>
	</c:if>

</body>
</html>