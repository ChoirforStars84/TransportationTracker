<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:import url="/WEB-INF/jsp/header.jsp" />
<c:url var="cssHref" value="/site.css" />
<link rel="stylesheet" type="text/css" href="${cssHref}">

<script type="text/javascript">
	$(document).ready(function () {
		$.validator.addMethod('capitals', function(thing){
			return thing.match(/[A-Z]/);
		});
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
					maxlength: "Number too long, it must be ten digits long",
					digits: "Field can only contain numerical characters (0-9)",
				}
			}
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>

</body>
</html>