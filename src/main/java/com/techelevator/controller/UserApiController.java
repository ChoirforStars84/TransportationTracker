package com.techelevator.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.techelevator.model.Route;
import com.techelevator.model.RouteDAO;
import com.techelevator.model.Stop;
import com.techelevator.model.StopDAO;



@CrossOrigin(origins = "*")
@RestController
public class UserApiController {
	
	@Autowired
	private StopDAO stopDao;
	@Autowired
	private RouteDAO routeDao;

	@RequestMapping(path="/routes/{routeNumber}/{busStop}/realtime", method=RequestMethod.GET)
	public String getArrivalTime(@PathVariable String routeNumber, @PathVariable String busStop, HttpServletResponse response) {
		String dataFeed = "Port Authority Bus";
		if(routeNumber.equals("RED") || routeNumber.equals("BLLB") || routeNumber.equals("BLSV")) {
			dataFeed = "Light Rail";
		}
		
		try {
			// build up a url with the appropriate parameters
			String urlBase = 
					UriComponentsBuilder.fromHttpUrl("http://realtime.portauthority.org/bustime/api/v3/getpredictions")
					.queryParam("rt", routeNumber)
					.queryParam("stpid", busStop)
					.queryParam("rtpidatafeed", dataFeed)
					.queryParam("format", "json")
					.queryParam("key", "vVhXGttG43fhKD4zAat6d7Vv7")
					.toUriString();
			
			// create a real URL object for the java networking
			URL url = new URL(urlBase);
			
			// use the URL to open an HTTP connection to the api server
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			//5 seconds in ms
			int timeoutInMs = 5000;
			con.setConnectTimeout(timeoutInMs);
			con.setReadTimeout(timeoutInMs);
			
			// get the response code from their API
			int responseCode = con.getResponseCode();
			
			// if we don't get a 200 (success) back from them, mirror that to our caller
			if(responseCode != 200) {
				response.setStatus(responseCode);
				return "{}";
			}
			
			// read the json from their API into a string
			StringBuffer content = new StringBuffer();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			con.disconnect();
			
			//return the json to our caller 
			return content.toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setStatus(500);
			return "{}";
		}
		//?key=vVhXGttG43fhKD4zAat6d7Vv7&rt=51&stpid=2551&rtpidatafeed=Port%20Authority%20Bus&format=json"
		
		/*$.get("http://localhost:8080/capstone/routes/54/2551/realtime",function(data){
 busResponse = JSON.parse(data);
 busResponse["bustime-response"];})
.fail(function(){alert("we got an error")});*/
	}
	
	@RequestMapping(path="/vehicle/{vehicleID}/realtime", method=RequestMethod.GET)
	public String getBusInfo(@PathVariable String vehicleID, HttpServletResponse response) {
		try {
			// build up a url with the appropriate parameters
			String urlBase = 
					UriComponentsBuilder.fromHttpUrl("http://realtime.portauthority.org/bustime/api/v3/getvehicles")
					.queryParam("vid", vehicleID)
					.queryParam("format", "json")
					.queryParam("key", "vVhXGttG43fhKD4zAat6d7Vv7")
					.toUriString();
			
			// create a real URL object for the java networking
			URL url = new URL(urlBase);
			
			// use the URL to open an HTTP connection to the api server
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			//5 seconds in ms
			int timeoutInMs = 5000;
			con.setConnectTimeout(timeoutInMs);
			con.setReadTimeout(timeoutInMs);
			
			// get the response code from their API
			int responseCode = con.getResponseCode();
			
			// if we don't get a 200 (success) back from them, mirror that to our caller
			if(responseCode != 200) {
				response.setStatus(responseCode);
				return "{}";
			}
			
			// read the json from their API into a string
			StringBuffer content = new StringBuffer();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			con.disconnect();
			
			//return the json to our caller 
			return content.toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setStatus(500);
			return "{}";
		}

	}
	
	@RequestMapping(path="/routes", method=RequestMethod.GET)
	public List<Route> getAllRoutes() {
		return routeDao.getAllRoutes();
	}	
	
	@RequestMapping(path="/routes/{routeNumber}", method=RequestMethod.GET)
	public List<Stop> getStopsByRoute(@PathVariable String routeNumber) {
		return stopDao.getAllStopsOnRoute(routeNumber);
	}
}
