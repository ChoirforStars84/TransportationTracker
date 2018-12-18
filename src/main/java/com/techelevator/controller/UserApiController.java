package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping(path="/routes", method=RequestMethod.GET)
	public List<Route> getAllRoutes() {
		return routeDao.getAllRoutes();
	}	
	
	@RequestMapping(path="/routes/{routeNumber}", method=RequestMethod.GET)
	public List<Stop> getStopsByRoute(@ModelAttribute String busLine) {
		return stopDao.getAllStopsOnRoute(busLine);
	}
}
