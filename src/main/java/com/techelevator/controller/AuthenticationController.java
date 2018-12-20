package com.techelevator.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.model.SmsSender;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Controller
public class AuthenticationController {

	private UserDAO userDAO;

	@Autowired
	public AuthenticationController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@RequestMapping(path="/login", method=RequestMethod.GET)
	public String displayLoginForm() {
		return "login";
	}
	
	@RequestMapping(path="/userHome", method=RequestMethod.POST)
	public String login(@RequestParam String userName, 
						@RequestParam String password, 
						//@RequestParam(required=false) String destination,
						HttpSession session) {
		if(userDAO.searchForUsernameAndPassword(userName, password)) {
			session.setAttribute("currentUser", userDAO.getUserByUserName(userName));
			
			/*if(destination != null && ! destination.isEmpty()) {
				return "redirect:" + destination;
			} else {
				return "redirect:/users/"+userName;
			}*/
			
			return "redirect:/homePage";		
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public String logout(ModelMap model, HttpSession session) {
		model.remove("currentUser");
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(path="/verification", method=RequestMethod.GET)
	public String sendTextAndGoToVerificationPage(@RequestParam String phoneNumber, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		boolean userExists = userDAO.verifyNumber(phoneNumber);
		if(userExists == false) {
			return "/noUserRecord";
		} else {
			int randomInt = SmsSender.generateTLRNumber();
			String verificationCode = SmsSender.randomNumToString(randomInt);
			session.setAttribute("verificationCode", verificationCode);
			SmsSender.sendVerificationCode(phoneNumber, verificationCode);
		}
			return "verification";
	}
	
	@RequestMapping(path="/changePassword", method=RequestMethod.POST)
	public String getChangePasswordFromTextVerification(@RequestParam String userVerificationCode, HttpServletRequest request) {
		HttpSession session = request.getSession();
	 	User currentUser = (User) session.getAttribute("currentUser");
	 	String phoneNumber = currentUser.getPhoneNumber();
	 	request.setAttribute("phoneNumber", phoneNumber);
		String actualVerificationCode = (String) session.getAttribute("verificationCode");
		if(userVerificationCode.equals(actualVerificationCode)) {
			return "/changePassword";
		} else {
			return "redirect:/noUserRecord";
		}
	}
	
	@RequestMapping("/noUserRecord")
	public String noUserRecord() {
		return "noUserRecord";
	}
}
