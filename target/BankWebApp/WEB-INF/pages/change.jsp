<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Login</title>
</head>
<body onload="document.loginForm.username.focus();">

	

	<div id="password-change-box" class="container">
		
		
		<form name="passwordChangeForm" class="password-change-form"
			action="changePsw" method="POST" onSubmit="return validateForm(this)">
			<h2>Change password</h2>
			<div class="alert alert-warning">Password change is needed on first login!</div>
			<div id="pswError" class="alert alert-danger hide"></div>
			<input type="password" class="form-control" id="password" name="password" placeholder="New Password" />
			<input type="password" class="form-control" name="confirmPassword" placeholder="Retype New Password"/>
			<button class="btn btn-lg btn-primary btn-block" name="change" type="submit">Change</button>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>
	</div>
	
	<script>
		function validateForm(form)
		{
			
			if(form.password.value=="")
		    { 
			  $("#pswError").removeClass("hide");
			  $("#pswError")[0].innerText = "Password should not be left blank!";
		      form.password.focus();
		      return false;
		    }
			if(form.confirmPassword.value=="")
		    {
			  $("#pswError").removeClass("hide");
			  $("#pswError")[0].innerText = "Retype password!";
		      form.confirmPassword.focus();
		      return false;
		    }
			if(form.password.value!=form.confirmPassword.value)
		    {
			  $("#pswError").removeClass("hide");
			  $("#pswError")[0].innerText = "Passwords do not match!";
		      form.confirmPassword.focus();
		      return false;
		    }
		}
	</script>

</body>
</html>