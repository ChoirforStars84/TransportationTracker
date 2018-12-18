//Section 1
let stopLat = 40.44;
let stopLon = -80.00;
var map;
var iconBase = 'img/';
var icons = {

};
function initMap() {
	icons.Stop = {
			icon: iconBase + 'BusStop.jpg',
			scaledSize: new google.maps.Size(30, 30)
	};  
	map = new google.maps.Map(document.getElementById('map-canvas'), {
		zoom: 15,

		center: new google.maps.LatLng(stopLat, stopLon),
		mapTypeId: 'roadmap'
	});



	var features = [
//		{
//		position: new google.maps.LatLng(stopLat, stopLon),
//		type: 'Stop'
//		},
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
	let routeNum = $('#routeSelect').val();
	reactiveDropdown.empty();
	console.log(routeNum);

	reactiveDropdown.append('<option selected="true" disabled>Choose Transit Stop</option>');
	reactiveDropdown.prop('selectedIndex', 0);
	const urlStop = 'http://localhost:8080/capstone/routes/'+routeNum;

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
			return false;
		}
	})
	var point = new google.maps.LatLng(stopLat,stopLon);
	map.setCenter(point);
	var marker = new google.maps.Marker({
		position: point,
		icon: icons['Stop'].icon,
		map: map
	});
});

