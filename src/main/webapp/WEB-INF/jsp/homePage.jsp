<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:import url="/WEB-INF/jsp/header.jsp" />
<c:url var="cssHref" value="/site.css" />
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
      var map;
      function initMap() {
        map = new google.maps.Map(document.getElementById('map-canvas'), {
          zoom: 15,
          center: new google.maps.LatLng(40.44, -80.00),
          mapTypeId: 'roadmap'
        });

        var iconBase = 'img/';
        var icons = {
          Stop: {
            icon: iconBase + 'BusStop.jpg',
            scaledSize: new google.maps.Size(64, 64)
          },
        };

        var features = [
          {
            position: new google.maps.LatLng(40.44, -80.000),
            type: 'Stop'
          }
        ];

        // Create markers.
        features.forEach(function(feature) {
          var marker = new google.maps.Marker({
            position: feature.position,
            icon: icons[feature.type].icon,
            map: map
          });
        });
      }
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC57pu1DIahWyfKlxandYUiCw6Kn-jx4ps&callback=initMap">
    </script>
</head>
<body>
<div class="bodyText">
<p>Welcome to the Bridge City Transit Tracker. Our goal is to help you navigate the beautiful city of Pittsburgh, making the most of our Port Authority Transit system. We've endeavored to make our website easy to navigate and use so that you can spend less time planning and more time travelling!</p>
<ul>
<li>If you already have an account with us, clicking <strong>Log In</strong> on the nav bar will open your account and allow you access to your saved routes and account settings.</li>
<li>If you don't have an account with us, clicking <strong>Sign Up</strong> will let you open an account with us, giving you access to features such as saving routes and automated text messaging</li>
<li>Whether you have an account or not, clicking <strong>Plan My Route</strong> will let you create a new route "on the fly" (although it can't be saved unless you login to your account!).</li>
<li>You can also stay on this page and browse bus and light rail routes using the route menu and map below:</li>
</ul>
</div>
<div>

	<select name="routeNumber">
		<c:forEach var="route" items="${routeList}">
			<option value="${route.routeNumber}" name="routeNumber">${route.routeNumber}: ${route.routeName}</option><br>
		</c:forEach>
	</select>
<select name="stopNumber">
		<c:forEach var="stop" items="${stopList}">
			<c:choose>
			<c:when test="${fn:containsIgnoreCase(stop.routes , routeNumber)}">
				<option value="${stop.externalId}" name="stopNumber">${stop.name}</option><br>
			</c:when>
			</c:choose>
		</c:forEach>
		
	</select>	
	<input type="submit" value="Submit" id="showMap" onclick="initMap()">
 
</div>
<div class="bodyContainer">
	<div class="routeInputForm">
	
	
	</div>

 <div id="map-canvas" style="height:600px; width:750px; margin: auto;  border: 3px solid black;"></div>
 </div>

</body>

    
    
<c:import url="/WEB-INF/jsp/footer.jsp" />
    