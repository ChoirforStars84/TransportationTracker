package com.techelevator.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.Route;
import com.techelevator.model.RouteDAO;
import com.techelevator.model.Stop;
import com.techelevator.model.StopDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;


@Controller
public class UserController {

	
	
	private UserDAO userDAO;
	
	@Autowired
	private StopDAO stopDao;
	
	
	public RouteDAO routeDao;

	@Autowired
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	
	@RequestMapping(path = { "/", "/homePage" })
	public String displayHome(HttpServletRequest request) {
 /*       String busLine = request.getParameter("busLine");
		for(Stop s : stopDao.getAllStopsOnRoute(busLine)) {
			if(s.getRoutes().equals(busLine)) {
				request.setAttribute("stop", s);
				List<Stop> stopList = stopDao.getAllStopsOnRoute(busLine);
				request.setAttribute("stopList", stopList);

			} 
		} */
		request.setAttribute("stopList", stopDao.getAllStops());
		return "homePage";
	}

	@RequestMapping(path="/users/new", method=RequestMethod.GET)
	public String displayNewUserForm(ModelMap modelHolder) {
		if( ! modelHolder.containsAttribute("user")) {
			modelHolder.addAttribute("user", new User());
		}
		return "newUser";
	}
	
	@RequestMapping(path="/users", method=RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			flash.addFlashAttribute("user", user);
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
			return "redirect:/users/new";
		}
		
		userDAO.saveUser(user.getUserName(), user.getPassword());
		return "redirect:/login";
	}
	
	
}
