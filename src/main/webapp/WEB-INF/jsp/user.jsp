
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<c:url var="cssHref" value="/site.css" />
<link rel="stylesheet" type="text/css" href="${cssHref}">
    
<div id="userWelcome">
<p id="welcomeMessage">Welcome back, ${currentUser.userName}!</p>
<p id="noteToUser">This is your Bridge City Transit Tracker user page. Click on the links above to plan a new, save a new route to use later, manage how you receive route notifications, or manage your password.</p>
</div>