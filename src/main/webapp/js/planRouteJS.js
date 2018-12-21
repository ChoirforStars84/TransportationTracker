$(document).ready(function () {
	
var toggleSaveRte = function(){
	var startField = $("#start");
	var endField = $("#end");
	var saveBtn = $("#saveRteBtn");
	if (startField.val() != null && startField.val() != "" && endField.val() != null && endField.val() != "") {
		saveBtn.removeAttr('disabled');
	} else {
		saveBtn.attr('disabled', 'test');
	}
	
};

$("#start,#end").on("change", toggleSaveRte);



});









//$("#saveRouteForm").validate({
//	debug : false,
//	rules : {
//		start : {
//			required : true
//		},
//		end : {
//			required : true
//		}
//	},
//	messages : {
//		start : {
//			required : "Starting address is required"
//		},
//		end : {
//			required : "Destination address is required"
//		}
//	},
//	errorClass : "error",
//	validClass : "valid"
//
//});


//
//	$("#findRteBtn").click(function(event) {
//		$("#saveRteBtn").removeAttr('disabled');
//	});
//	  

	
// INIT MAP
	
	
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
        onChangeHandler();
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