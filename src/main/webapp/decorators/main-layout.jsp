<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html lang="en">
<head>
    
 
    <meta charset="utf-8"/>
 
    <title><decorator:title></decorator:title></title>
 
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/bootstrap/3.1.1/css/bootstrap.min.css" media="all"/>
 	<link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/style.css" media="all"/>
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="https://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    < ![endif]-->
 	<meta name="viewport" content="width=device-width, initial-scale=1">
 	
 	
    <decorator:head></decorator:head>
</head>
<body>
 
<header class="navbar navbar-static-top bs-docs-nav">
	 <div class="container">
	 	<div class="navbar-header">
	 		<a class="navbar-brand" href="#"><span class="glyphicon glyphicon-briefcase"></span> SimpleBank</a>
	 	</div>
	 	<c:if test="${pageContext.request.userPrincipal.name != null && pageContext.request.requestURI != '/BankWebApp/change' }">
           	<c:url value="/j_spring_security_logout" var="logoutUrl" />
			<form action="${logoutUrl}" method="post" id="logout-form">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<p class="navbar-text navbar-right">
				Welcome ${pageContext.request.userPrincipal.name}!
				<a href="javascript:formSubmit()"> Logout</a>
			</p>
			<script>
				function formSubmit() {
					document.getElementById("logout-form").submit();
				}
			</script> 
	    </c:if>
	 </div>
</header>
 
<div class="container">
 
    <c:if test="${page_error != null }">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if>
 
 
    <decorator:body></decorator:body>
 
    
</div>
<footer class="footer" >
    <div class="text-center">
		<p class="text-muted">info@simplebank.com</p>
    </div>
</footer>
 
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/jquery/1.9.0/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</body>
</html>