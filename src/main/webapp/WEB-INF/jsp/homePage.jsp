<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/header.jsp" />
<c:url var="cssHref" value="/site.css" />
<link rel="stylesheet" type="text/css" href="${cssHref}">

<script
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC57pu1DIahWyfKlxandYUiCw6Kn-jx4ps&v=3.exp&sensor=false"></script>
<script>
var map;
function initialize() {
  var mapOptions = {
    zoom: 14,
    center: new google.maps.LatLng(40.44, -80.00)
  };
  map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);
}

google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body>
 <div id="map-canvas" style="height:600px; width:750px; margin: auto;  border: 3px solid black;"></div>
</body>

    
    
<c:import url="/WEB-INF/jsp/footer.jsp" />
    