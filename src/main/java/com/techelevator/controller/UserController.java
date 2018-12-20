package com.techelevator.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Autowired
	public RouteDAO routeDao;

	@Autowired
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	
	@RequestMapping(path = { "/", "/homePage" })
	public String displayHome(HttpServletRequest request) {
		request.setAttribute("stopList", stopDao.getAllStops());
		request.setAttribute("routeList", routeDao.getAllRoutes());
		return "homePage";
	}

	@RequestMapping(path="/newUser", method=RequestMethod.GET)
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
			return "redirect:/newUser";
	}
		
		userDAO.saveUser(user.getUserName(), user.getPassword(), user.getPhoneNumber());
		return "redirect:/login";
	}
	
	@RequestMapping(path="/changePassword", method=RequestMethod.GET)
	public String displayChangePasswordPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
	 	User currentUser = (User) session.getAttribute("currentUser");
	 	String phoneNumber = currentUser.getPhoneNumber();
	 	request.setAttribute("phoneNumber", phoneNumber);
	 	
		return "changePassword";
	}
	
	@RequestMapping(path="/sendText", method=RequestMethod.GET)
	public String displaySendTextPage() {
		return "sendText";
	}
	
	
}
