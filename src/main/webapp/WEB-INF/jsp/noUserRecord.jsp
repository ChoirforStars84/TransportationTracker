<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="row">
	<div class="col-sm-4"></div>
	<div class="col-sm-4">
	<p>We do not have your phone number on record in our database. Click the button below to go to sign up for an account with us!</p>
		<c:url var="formAction" value="/newUser" />
		<form method="GET" action="${formAction}">
			<button type="submit" class="btn btn-default">Sign Up</button>
		</form>
	</div>
	<div class="col-sm-4"></div>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp" />
