<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />


<div class="row">
	<div class="col-sm-4"></div>
	<div class="col-sm-4">
<p>TESTING. The code you entered did not match the one sent to the phone number entered. Click below to return to the SendText page and enter your phone number again</p>
<c:url var="formAction" value="/sendText" />
		<form method="GET" action="${formAction}">

			<button type="submit" class="btn btn-default">Try My Phone Number Again</button>
		</form>
		</div>
	<div class="col-sm-4"></div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />