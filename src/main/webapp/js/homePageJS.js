//Section 1
let stopLat = 40.44;
let stopLon = -80.00;
let routeNum = null;
let stopExternalId = null;
var vehicleId = null;
var map;
var iconBase = 'img/';
var icons = {};
var features =[];
var markers = [];

function initMap() {
	icons.Stop = {
			icon: iconBase + 'BusStop.jpg',
			scaledSize: new google.maps.Size(30, 30)
	}; 
	icons.Bus = {
			icon: iconBase + 'Bus.png',
			scaledSize: new google.maps.Size(30, 30)			
	};
	map = new google.maps.Map(document.getElementById('map-canvas'), {
		zoom: 15,

		center: new google.maps.LatLng(stopLat, stopLon),
		mapTypeId: 'roadmap'
	});



	features = [];

	// Create markers.
	features.forEach(function(feature) {
		var marker = new google.maps.Marker({
			position: feature.position,
			icon: icons[feature.type].icon,
			map: map
		});
	});
}

//Section 2

let dropdown = $('#routeSelect');

dropdown.empty();

dropdown.append('<option selected="true" disabled>Choose Transit Route</option>');
dropdown.prop('selectedIndex', 0);

const url = 'http://localhost:8080/capstone/routes';

// Populate dropdown with list of provinces
$.getJSON(url, function (data) {
	$.each(data, function (key, entry) {
		dropdown.append($('<option></option>').attr('value', entry.routeNumber).text(entry.routeNumber + ": " + entry.routeName));
	})
});


$("#routeSelect").on("change" , function(e) {
	let reactiveDropdown = $('#stopSelect');
	routeNum = $('#routeSelect').val();
	reactiveDropdown.empty();
	console.log(routeNum);

	reactiveDropdown.append('<option selected="true" disabled>Choose Transit Stop</option>');
	reactiveDropdown.prop('selectedIndex', 0);
	let urlStop = 'http://localhost:8080/capstone/routes/'+routeNum;

	$.getJSON(urlStop, function (data) {
		window.lastStopList = data;
		$.each(data, function (key, entry) {
			reactiveDropdown.append($('<option></option>').attr('value', entry.internalId).text(entry.name + " (" + entry.direction + ")"));
		})
	});
});

$("#stopSelect").on("change" , function(e) {
	var selectedId = $("#stopSelect").val();
	console.log("Selected ID: " + selectedId);
	$.each(window.lastStopList, function (key, entry) {
		if (entry.internalId == selectedId) {
			console.log("Found It");
			stopLat = entry.latitude;
			stopLon = entry.longitude;
			stopExternalId = entry.externalId.toString();
			return false;
		}
	})
	features = [];
	var point = new google.maps.LatLng(stopLat,stopLon);
	map.setCenter(point);
	deleteMarkers();
	addStopMarker(point);

	let urlPred = 'http://localhost:8080/capstone/routes/'+ routeNum + '/' + stopExternalId + '/realtime';

	$.getJSON(urlPred, function (data) {
		$.each(data, function (key, entry) {
			vehicleId = entry.vid;
			console.log("The Data is: " + entry.stpnm);
		})
	});
	
	let vehicleLocationUr = 'http://localhost:8080/capstone/vehicle/'+ vehicleId +'/realtime';
	
	$.getJSON(vehicleLocationUr, function (data) {
		$.each(data, function (key, entry) {
			console.log(entry.lat + ", " + entry.lon);
			var point = new google.maps.LatLng(entry.lat,entry.lon);
			addBusMarker(point);
		
		})
	});
	
	
	
});


function addStopMarker(point) {
    var marker = new google.maps.Marker({
      position: point,
      icon: icons['Stop'].icon,
      map: map
    });
    markers.push(marker);
  }

function addBusMarker(point) {
    var marker = new google.maps.Marker({
      position: point,
      icon: icons['Bus'].icon,
      map: map
    });
    markers.push(marker);
  }

function setMapOnAll(map) {
    for (var i = 0; i < markers.length; i++) {
      markers[i].setMap(map);
    }
  }

function clearMarkers() {
    setMapOnAll(null);
  }

function showMarkers() {
    setMapOnAll(map);
  }

function deleteMarkers() {
    clearMarkers();
    markers = [];
  }

