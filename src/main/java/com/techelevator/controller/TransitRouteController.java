package com.techelevator.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String displayPlanNewRoutePage() {
		return "planRoute";
	}
	
	@RequestMapping(path = "/planRoute", method=RequestMethod.POST)
	public String saveRoute(HttpServletRequest request) {
		HttpSession session = request.getSession();
	 	User currentUser = (User) session.getAttribute("currentUser");
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
