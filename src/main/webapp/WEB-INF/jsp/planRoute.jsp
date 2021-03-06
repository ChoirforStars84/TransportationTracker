<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Directions Service</title>
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
      #floating-panel {
        position: absolute;
        top: 10px;
        left: 25%;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
        text-align: center;
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        padding-left: 10px;
      }
    </style>
  </head>
  <body>
  		<form action="planRoute" method="POST" id="saveRouteForm">
			<div class="form-group">

			<c:choose>
 			  <c:when test="${not empty startPt}">
 			     <c:set var="startVar" value="${startPt} " />
 			  </c:when>
 			  <c:otherwise>
    				  <c:set var="startVar" value="" />  
 			  </c:otherwise>
			</c:choose>
			
			
			<c:choose>
 			  <c:when test="${not empty endPt}">
 			     <c:set var="endVar" value="${endPt} " />
 			  </c:when>
 			  <c:otherwise>
    				  <c:set var="endVar" value="" />  
 			  </c:otherwise>
			</c:choose>
			
			
				<label for="startingAddress">Starting Address: </label>
				<input type="text" id="start" name="startingAddress" placeHolder="123 Main St., Pittsburgh, PA, 15202" class="form-control" value="${startVar }" />
			</div>
			<div class="form-group">
				<label for="destinationAddress">Destination Address: </label>
				<input type="text" id="end" name="destinationAddress" placeHolder="987 Other St., New Place, PA, 15999" class="form-control" value="${endVar }"/>
			</div>
<!-- 			<button type="submit" class="btn btn-default" id="findRteBtn">Find Route</button> -->
			<c:if test="${not empty currentUser}">
			<button type="submit" class="btn btn-default" disabled id="saveRteBtn" value="submit"> Save Route</button>
			</c:if>
		</form>
			<br><br>

    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC57pu1DIahWyfKlxandYUiCw6Kn-jx4ps&callback=initMap">
    </script>
<div id="map-canvas" style="height:600px; width:750px; margin: auto;  border: 3px solid black;"></div>

<script src = "js/planRouteJS.js"></script>
<c:import url="/WEB-INF/jsp/footer.jsp" />
