package com.techelevator.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techelevator.model.SavedRoute;
import com.techelevator.model.SavedRouteDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Controller
public class TransitRouteController {
	
	private UserDAO userDAO;
	private SavedRouteDAO savedRouteDAO;

	@Autowired
	public TransitRouteController(UserDAO userDAO, SavedRouteDAO savedRouteDAO) {
		this.userDAO = userDAO;
		this.savedRouteDAO = savedRouteDAO;
	}
	
	@RequestMapping(path = "/planRoute", method=RequestMethod.GET)
	public String displayPlanNewRoutePage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		//get rte out of param, set as string
		
		// if not null, do db stuff and set req attr
		if (request.getParameter("route_id") != null) {
			Long routeId = Long.parseLong(request.getParameter("route_id"));
			SavedRoute wantedRoute = savedRouteDAO.getSavedRouteById(routeId);
			if (wantedRoute != null) {
				request.setAttribute("startPt", wantedRoute.startPt);
				request.setAttribute("endPt", wantedRoute.endPt);
			}
		}
	 	User currentUser = (User) session.getAttribute("currentUser");
	 	// in jsp if rte attr set, default rte
		return "planRoute";
	}
	
	@RequestMapping(path = "/planRoute", method=RequestMethod.POST)
	public String saveRoute(HttpServletRequest request) {
		HttpSession session = request.getSession();
	 	User currentUser = (User) session.getAttribute("currentUser");
	 	String startPt = request.getParameter("startingAddress");
	 	String endPt = request.getParameter("destinationAddress");
	 	savedRouteDAO.saveRoute(currentUser, startPt, endPt);
		return "redirect:/savedRoutes";
	 	
	}

	
	@RequestMapping("/savedRoutes")
	public String displaySavedRoutesPage(HttpServletRequest request) {
	 	HttpSession session = request.getSession();
	 	User currentUser = (User) session.getAttribute("currentUser");
		request.setAttribute("savedRoutesList", savedRouteDAO.getAllSavedRoutesByUser(currentUser));
		return "savedRoutes";
	}
}
