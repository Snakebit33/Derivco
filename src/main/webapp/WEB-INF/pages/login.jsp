<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page session="true"%>
<html>
<head>
	<title>Login</title>
</head>
<body onload="document.loginForm.username.focus();">

	<div id="login-box" class="container">
		<form name="loginForm" class="form-login"
			action="<c:url value="/j_spring_security_check" />" method="POST">
			<h2>Please sign in</h2>
			
			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="alert alert-info">${msg}</div>
			</c:if>
			
			<input type="text" class="form-control" placeholder="Username" name="username">
			<input type="password" class="form-control" placeholder="Password" name="password" />
			<button type="submit" class="btn btn-lg btn-primary btn-block" name="submit">Login</button>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>
	</div>

</body>
</html>