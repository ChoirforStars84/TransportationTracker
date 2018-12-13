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
			<div class="form-group">
				<label for="startingAddress">Starting Address: </label>
				<input type="text" id="start" name="startingAddress" placeHolder="123 Main St., Pittsburgh, PA, 15202" class="form-control" />
			</div>
			<div class="form-group">
				<label for="destinationAddress">Destination Address: </label>
				<input type="text" id="end" name="destinationAddress" placeHolder="987 Other St., New Place, PA, 15999" class="form-control" />
			</div>
			<button type="submit" class="btn btn-default">Find Route</button>
    <div id="map"></div>
    <script>
      function initMap() {
        var directionsService = new google.maps.DirectionsService;
        var directionsDisplay = new google.maps.DirectionsRenderer;
        var map = new google.maps.Map(document.getElementById('map-canvas'), {
          zoom: 14,
          center: {lat: 40.44, lng: -80.00}
        });
        directionsDisplay.setMap(map);

        var onChangeHandler = function() {
          calculateAndDisplayRoute(directionsService, directionsDisplay);
        };
        document.getElementById('start').addEventListener('change', onChangeHandler);
        document.getElementById('end').addEventListener('change', onChangeHandler);
      }

      function calculateAndDisplayRoute(directionsService, directionsDisplay) {
        directionsService.route({
          origin: document.getElementById('start').value,
          destination: document.getElementById('end').value,
          travelMode: 'TRANSIT'
        }, function(response, status) {
          if (status === 'OK') {
            directionsDisplay.setDirections(response);
          }
        });
      }
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC57pu1DIahWyfKlxandYUiCw6Kn-jx4ps&callback=initMap">
    </script>
<div id="map-canvas" style="height:600px; width:750px; margin: auto;  border: 3px solid black;"></div>
<c:import url="/WEB-INF/jsp/footer.jsp" />
