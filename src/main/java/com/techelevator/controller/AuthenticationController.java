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
	public String sendTextAndGoToVerificationPage(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		String phoneNumber = currentUser.getPhoneNumber();
		String verificationCode = SmsSender.generateTLRNumber();
		session.setAttribute("verificationCode", verificationCode);
		SmsSender.sendVerificationCode(phoneNumber, verificationCode);
		return "verification";
	}
	
	@RequestMapping(path="/externalVerify", method=RequestMethod.GET)
	public String sendTextAndGoToExternalVerifyPage(@RequestParam String phoneNumber, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		boolean userExists = userDAO.verifyNumber(phoneNumber);
		if(userExists == false) {
			return "/noUserRecord";
		} else {
			String verificationCode = SmsSender.generateTLRNumber();
			session.setAttribute("verificationCode", verificationCode);
			session.setAttribute("phoneNumber", phoneNumber);
			SmsSender.sendVerificationCode(phoneNumber, verificationCode);
		}
			return "externalVerify";
	}
	
	
	
	@RequestMapping(path="/changePassword", method=RequestMethod.POST)
	public String getChangePasswordFromTextVerification(@RequestParam String verificationCode, HttpServletRequest request) {
		HttpSession session = request.getSession();
	 	User currentUser = (User) session.getAttribute("currentUser");
	 	String phoneNumber = currentUser.getPhoneNumber();
	 	request.setAttribute("phoneNumber", phoneNumber);
		String actualVerificationCode = (String) session.getAttribute("verificationCode");
		if(verificationCode.equals(actualVerificationCode)) {
			return "/changePassword";
		} else {
			return "redirect:/noUserRecord";
		}
	}
	
	@RequestMapping(path="/newPassword", method=RequestMethod.POST)
	public String getNewPasswordFromTextVerification(@RequestParam String verificationCode, HttpSession session) {
		String actualVerificationCode = (String) session.getAttribute("verificationCode");
		if(!verificationCode.equals(actualVerificationCode)) {
			return "cannotVerify";
		} else {
			String phoneNumber = (String) session.getAttribute("phoneNumber");
			User currentUser = userDAO.getUserByPhoneNumber(phoneNumber);
			session.setAttribute("currentUser", currentUser);
			return "newPassword";
		}
	}
	
	@RequestMapping(path="/resetPassword", method=RequestMethod.POST)
	public String getResetPasswordFromTextVerification(@RequestParam String verificationCode, HttpSession session) {
		String actualVerificationCode = (String) session.getAttribute("verificationCode");
		if(!verificationCode.equals(actualVerificationCode)) {
			return "cannotVerify";
		} else {
			return "resetPassword";
		}
	}
		
	
	@RequestMapping("/noUserRecord")
	public String noUserRecord() {
		return "noUserRecord";
	}
}
