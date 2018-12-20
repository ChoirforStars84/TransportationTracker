<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Bridge City Transit Tracker</title>
	    <script type="text/javascript" src="js/jquery.min.js"></script>
	    <script type="text/javascript" src="js/jquery.validate.min.js"></script>
	    <script type="text/javascript" src="js/additional-methods.min.js "></script>
	    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.timeago/1.4.1/jquery.timeago.min.js"></script>
	    <script type="text/javascript" src="js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="css/bootstrap.min.css">
	    <link rel="stylesheet" href="css/site.css"></link>
		<link rel="icon" href="img/logo.jpg">
		
		<script type="text/javascript">
			$(document).ready(function() {
				$("time.timeago").timeago();
				
				$("#logoutLink").click(function(event){
					$("#logoutForm").submit();
				});
				
				var pathname = window.location.pathname;
				$("nav a[href='"+pathname+"']").parent().addClass("active");
				
			});
			
			
		</script>
		
	</head>
	<body >
		<header>
			<div id="bannerLogoContainer">
				<img id="bannerLogo" src="img/appLogo.jpg" alt="BCTT Logo">
			</div>
		<div id="banner">
			<%--<p id="siteTitle">Bridge City Transit Tracker</p>--%>
		</div>
		</header>
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<ul class="nav navbar-nav">
					<c:if test="${empty currentUser}">
						<c:url var="homePageHref" value="/" />
						<li><a href="${homePageHref}">Home</a></li>
						<c:url var="planNewRouteHref" value="/planRoute" />
						<li><a href="${planNewRouteHref}">Plan Your Route</a></li>
					</c:if>
					<c:if test="${not empty currentUser}">
						<c:url var="homePageHref" value="/" />
						<li><a href="${homePageHref}">My Home Page</a></li>
						<c:url var="planNewRouteHref" value="/planRoute" />
						<li><a href="${planNewRouteHref}">Plan a New Route</a></li>
						<c:url var="mySavedRoutesHref" value="/savedRoutes" />
						<li><a href="${mySavedRoutesHref}">My Saved Routes</a></li>
						<c:url var="notificationSetupHref" value="/notification" />
						<li><a href="${newMessageHref}">Notification Setup</a></li>
						<c:url var="changePasswordHref" value="/changePassword" />
						<li><a href="${changePasswordHref}">Change Password</a></li>
					</c:if>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${empty currentUser}">
							<c:url var="newUserHref" value="/newUser" />
							<li><a href="${newUserHref}">Sign Up</a></li>
							<c:url var="loginHref" value="/login" />
							<li><a href="${loginHref}">Log In</a></li>
						</c:when>
						<c:otherwise>
							<c:url var="logoutAction" value="/logout" />
							<form id="logoutForm" action="${logoutAction}" method="POST">
							<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
							</form>
							<li><a id="logoutLink" href="#">Log Out</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</nav>
		
		<%-- <c:url var="imgSrc" value="/img/appLogo.jpg" />--%>
		<c:url var="homePageHref" value="/" />
			<a href="${homePageHref}"><img src="${imgSrc}" style="height: auto; width: 20%;" /></a>
		
		</div>
		<c:if test="${not empty currentUser}">
			<p id="currentUser">Current User: ${currentUser.userName}</p>
		</c:if>		
		<div class="container">