<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<c:url var="formAction" value="/users" />
<form method="POST" action="${formAction}">
<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>

<p>Please enter addresses in the form of: Street number & name, city, state, and zip.<br></p>
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<div class="form-group">
				<label for="startingAddress">Starting Address: </label>
				<input type="text" id="startingAddress" name="startingAddress" placeHolder="123 Main St., Pittsburgh, PA, 15202" class="form-control" />
			</div>
			<div class="form-group">
				<label for="destinationAddress">Destination Address: </label>
				<input type="text" id="destinationAddress" name="destinationAddress" placeHolder="987 Other St., New Place, PA, 15999" class="form-control" />
			</div>
			<div class="form-group">
				<label for="deptTime">Time of Departure: </label>
				<input type="text" id="deptTime" name="deptTime" placeHolder="9:30 am" class="form-control" />	
			</div>
			<button type="submit" class="btn btn-default">Find Route</button>
		</div>
		<div class="col-sm-4"></div>
	</div>
</form>