<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="row">
	<div class="col-sm-4"></div>
	<div class="col-sm-4">
	<p>Please enter your five-digit verification code below. You will be logged-in and directed to a page where you can reset your password</p>
		<c:url var="formAction" value="/userHome" />
		<form method="POST" action="${formAction}">
		<input type="hidden" name="destination" value="${param.destination}"/>
		<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
			<div class="form-group">
				<input type="text" id="verificationCode" name="verificationCode" placeHolder="#####" class="form-control" />
			</div>
			<button type="submit" class="btn btn-default">Login</button>
		</form>
	</div>
	<div class="col-sm-4"></div>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp" />
