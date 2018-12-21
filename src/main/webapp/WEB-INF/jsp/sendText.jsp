<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<script type="text/javascript">
$(document).ready(function () {

	$("form").validate({
		
		rules : {
			phoneNumber : {
				required : false,
				minlength: 10,
				maxlength: 10,
				digits: true,
			}
		},
		messages : {			
			phoneNumber : {
				minlength: "Number too short, it must be ten digits long",
				maxlength: "Number too long, it should be only ten digits long",
				digits: "Field should only contain numerical characters 0-9",
			}
		},
		errorClass : "error"
	});
});
</script>

<div class="row">
	<div class="col-sm-4"></div>
	<div class="col-sm-4">
<p>Please enter your mobile phone number here. We will send you a text with a verification code.</p>
<c:url var="formAction" value="/externalVerify" />
		<form method="GET" action="${formAction}">
			<div class="form-group">
				<input type="text" id="phoneNumber" name="phoneNumber" placeHolder="Phone Number" class="form-control"/>
			</div>
			<button type="submit" class="btn btn-default">Send Me My Verification Code</button>
		</form>
		</div>
	<div class="col-sm-4"></div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />
