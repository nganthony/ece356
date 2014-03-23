package com.ece356.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ece356.domain.User;
import com.ece356.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String showLoginPage() {
		
		return "userLogin";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView validateLogin(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		
		// Check if id is valid
		if(id.isEmpty()) {
			map.put("errorMessage", "Please enter an id number");
			return new ModelAndView("userLogin", map);
		}
		
		// Check if password is valid
		if(password.isEmpty()) {
			map.put("errorMessage", "Please enter a password");
			return new ModelAndView("userLogin", map);
		}
		
		// Get user
		User user = userService.getUser(id);
		
		// No user with specified id
		if(user == null) {
			map.put("errorMessage", "Incorrect credentials");
			return new ModelAndView("userLogin", map);
		}
		
		// Validate password
		if(!user.getPassword().equals(password)) {
			map.put("errorMessage", "Incorrect credentials");
			return new ModelAndView("userLogin", map);
		}
		
		// Check role to determine which page the user should be directed to
		map.put("errorMessage", "Logged in");
		return new ModelAndView("userLogin", map);
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String showCreatePage(Model model,
			@ModelAttribute("user") User user) {
		
		model.addAttribute("user", user);
		
		return "userCreate";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ModelAndView validateCreate(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return new ModelAndView("userCreate");
		
	}
}