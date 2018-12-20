//Variable Declaration
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
var busCount = 1;


//Map initialization
function initMap() {

//Declaration of Stop Icon
	icons.Stop = {
			icon: iconBase + 'BusStop.jpg',
			scaledSize: new google.maps.Size(30, 30)
	};
	
//Declaration of Bus Icon
	icons.Bus = {
			icon: iconBase + 'Bus.png',
			scaledSize: new google.maps.Size(30, 30)			
	};
	
//Declaration of Map Location within the HTML.
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
};

$(document).ready(initMap);

//Making the routeSelect JavaScript drop-down object
let dropdown = $('#routeSelect');
dropdown.empty();
dropdown.append('<option selected="true" disabled>Choose Transit Route</option>');
dropdown.prop('selectedIndex', 0);

const url = 'http://localhost:8080/capstone/routes';

// Populate dropdown with list of routes
$.getJSON(url, function (data) {
	$.each(data, function (key, entry) {
		dropdown.append($('<option></option>').attr('value', entry.routeNumber).text(entry.routeNumber + ": " + entry.routeName));
	})
});


// Executes after the route is chosen
$("#routeSelect").on("change" , function(e) {

//Making the stopSelect JavaScript drop-down object
	let reactiveDropdown = $('#stopSelect');
	routeNum = $('#routeSelect').val();
	reactiveDropdown.empty();
	console.log(routeNum);

	reactiveDropdown.append('<option selected="true" disabled>Choose Transit Stop</option>');
	reactiveDropdown.prop('selectedIndex', 0);
	let urlStop = 'http://localhost:8080/capstone/routes/'+routeNum;

//Populate dropdown with list of stops on the chosen route
	$.getJSON(urlStop, function (data) {
		window.lastStopList = data;
		$.each(data, function (key, entry) {
			reactiveDropdown.append($('<option></option>').attr('value', entry.internalId).text(entry.name + " (" + entry.direction + ")"));
		})
	});
});

//Executes after the stop is chosen
$("#stopSelect").on("change" , function(e) {
	var selectedId = $("#stopSelect").val();
	console.log("Selected ID: " + selectedId);
	$.each(window.lastStopList, function (key, entry) {
		if (entry.internalId == selectedId) {
			stopLat = entry.latitude;
			stopLon = entry.longitude;
			stopExternalId = entry.externalId.toString();
			return false;
		}
	});
	

	features = [];
	var point = new google.maps.LatLng(stopLat,stopLon);
	map.setCenter(point);
	deleteMarkers();
	addStopMarker(point);

	let urlPred = 'http://localhost:8080/capstone/routes/'+ routeNum + '/' + stopExternalId + '/realtime';
	let busInfo = $('#ETA-List');
	busInfo.empty();
	vehicleId= "";
	busCount = 1;
	$("#PreETA-Text").append($('<b></b>').text("Bus ETA's:"));
	
	$.getJSON(urlPred, function (data) {
		$.each(data["bustime-response"].prd, function (key, entry) {
			vehicleId += entry.vid+",";
			busInfo.append($('<li></li>').text("Bus " + busCount + ": " + entry.prdtm));
			busCount++;
		});
		
		let vehicleLocationUrl = 'http://localhost:8080/capstone/vehicle/'+ vehicleId +'/realtime';

		
		$.getJSON(vehicleLocationUrl, function (data) {
			window.vehicleLocations = data["bustime-response"].vehicle;
			$.each(data["bustime-response"].vehicle, function (key, entry) {
				console.log(entry.lat + ", " + entry.lon);
				var point = new google.maps.LatLng(entry.lat,entry.lon);
				addBusMarker(point);

				
			});
			
			$.each(data["bustime-response"].error, function (key, entry) {
				console.log(entry.msg);
			});
		})
		.fail(function() {
			alert("No Busses Nearby");			
		});
	})
	.fail(function() {
		alert("No Busses Nearby");
	});
});


function addStopMarker(point) {
    var marker = new google.maps.Marker({
      position: point,
      icon: icons['Stop'].icon,
      map: map
    });
    markers.push(marker);
  };

function addBusMarker(point) {
    var marker = new google.maps.Marker({
      position: point,
      icon: icons['Bus'].icon,
      map: map
    });
    markers.push(marker);
  };

function setMapOnAll(map) {
    for (var i = 0; i < markers.length; i++) {
      markers[i].setMap(map);
    }
  };

function clearMarkers() {
    setMapOnAll(null);
  };

function showMarkers() {
    setMapOnAll(map);
  };

function deleteMarkers() {
    clearMarkers();
    markers = [];
  };

