<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<script type="text/javascript">
		$("form").validate({
			
			rules : {
				verificationCode : {
					minlength: 5,
					maxlength: 5,
				}
			},
			messages : {	
				verificationCode: {
					minlength: "Code is too short, must be exactly 5 characters.",
					maxlength: "Code is too long, must be exactly 5 characters.",
				}	
			},
			errorClass : "error"
		});
	});
</script>

<div class="row">
	<div class="col-sm-4"></div>
	<div class="col-sm-4">
	<p>Please enter your five-digit verification code below. You will be logged-in and directed to a page where you can reset your password</p>
		<c:url var="formAction" value="/resetPassword" />
		<form method="POST" action="${formAction}">
			<div class="form-group">
				<input type="text" id="verificationCode" name="verificationCode" placeHolder="#####" class="form-control" />
			</div>
			<button type="submit" class="btn btn-default">Login</button>
		</form>
	</div>
	<div class="col-sm-4"></div>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp" />
