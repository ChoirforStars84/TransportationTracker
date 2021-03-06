<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:import url="/WEB-INF/jsp/header.jsp" />
<c:url var="cssHref" value="css/site.css" />
<link rel="stylesheet" type="text/css" href="${cssHref}">

    <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 100%;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
   
    <script>
// Section 1 JS async defer
    </script>
    
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC57pu1DIahWyfKlxandYUiCw6Kn-jx4ps" async defer>
    </script>
    <h1 style = "text-Align: center;">Bridge City Transit Tracker</h1>
</head>
<body>

<div class="bodyText">
<p>Welcome to the <strong>Bridge City Transit Tracker</strong>. Our goal is to help you navigate the beautiful city of Pittsburgh, making the most of our Port Authority Transit system. We've endeavored to make our website easy to navigate and use so that you can spend less time planning and more time travelling!</p>
<ul>
<li>If you already have an account with us, clicking <strong>Log In</strong> on the nav bar will open your account and allow you access to your saved routes and account settings.</li>
<li>If you don't have an account with us, clicking <strong>Sign Up</strong> will let you open an account with us, giving you access to features such as saving routes and automated text messaging</li>
<li>Whether you have an account or not, clicking <strong>Plan My Route</strong> will let you create a new route "on the fly" (although it can't be saved unless you login to your account!).</li>
<li>You can also stay on this page and browse bus and light rail routes using the route menu and map below:</li>
</ul>
</div>
<div>
	<form>
		<select name="routeNumber" id="routeSelect"></select>
		<select name="stopNumber" id="stopSelect">
			<option selected="true" disabled>Please Select Transit Route First</option>
		</select>
		<br><br>
<script>
//Section 2 JS
</script>
<br>
</form>
</div>
<div id = "map-canvas" style = "float: center; height:600px; width:750px; margin: auto;  border: 3px solid black;"><br><br><h2>  Please Enter a Bus Route and Stop to Display Map!</h2></div>
<div id = "busInfo-ETA">
<span id="PreETA-Text"></span>
<ul id="ETA-List">

</ul>

</div>
<div id = "busInfo-Error"></div>

</body>

    
<script src = "js/homePageJS.js"> </script>    
<c:import url="/WEB-INF/jsp/footer.jsp" />
    