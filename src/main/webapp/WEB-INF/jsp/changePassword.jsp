<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />


<div class="row">
	<div class="col-sm-4"></div>
	<div class="col-sm-4">
<p>We will send you a text with a verification code.</p>
<c:url var="formAction" value="/verification" />
		<form method="GET" action="${formAction}">

			<button type="submit" class="btn btn-default">Send Me My Verification Code</button>
		</form>
		</div>
	<div class="col-sm-4"></div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />
