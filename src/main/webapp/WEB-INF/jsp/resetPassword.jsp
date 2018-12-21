<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<script type="text/javascript">
	$(document).ready(function () {
		$.validator.addMethod('capitals', function(thing){
			return thing.match(/[A-Z]/);
		});
		$("form").validate({
			
			rules : {
				password : {
					required : true,
					minlength: 10,
					capitals: true,
				},
				confirmPassword : {
					required : true,		
					equalTo : "#password"  
				}
				
			},
			messages : {	
				password: {
					minlength: "Password too short, make it at least 15 characters",
					capitals: "Field must contain a capital letter",
				},
				confirmPassword : {
					equalTo : "Passwords do not match"
				}		
			},
			errorClass : "error"
		});
	});
</script>

<div class="row">
	<div class="col-sm-4"></div>
	<div class="col-sm-4">
	<p>Please enter and confirm a new password:</p>
		<c:url var="formAction" value="/" />
		<form method="POST" action="${formAction}">
			<div class="form-group">
				<label for="newPassword">New Password:</label>
				<input type="password" id="newPassword" name="newPassword" placeHolder="New Password" class="form-control" />
			</div>
			<div class="form-group">
				<label for="newPasswordConfirm">Confirm New Password:</label>
				<input type="password" id="newPasswordConfirm" name="newPasswordConfirm" placeHolder="Confirm New Password" class="form-control" />
			</div>
			<button type="submit" class="btn btn-default">Login</button>
		</form>
	</div>
	<div class="col-sm-4"></div>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp" />