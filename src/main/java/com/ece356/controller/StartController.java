package com.ece356.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.cfg.context.ReturnValueConstraintMappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ece356.domain.Patient;
import com.ece356.domain.Role;
import com.ece356.domain.User;
import com.ece356.service.PatientService;
import com.ece356.service.UserService;

@Controller
@RequestMapping("/")
public class StartController {

	@Autowired
	UserService userService;

	@Autowired
	PatientService patientService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showLoginPage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		Patient patient = (Patient) session.getAttribute("patient");
		if (patient == null && user == null) {
			return new ModelAndView("userLogin");
		} else {
			if (user != null) {
				if(user.getRoleId() == 1) {
					return new ModelAndView("redirect:/doctor/" + user.getId() + "/patients");	
				}
				else if(user.getRoleId() == 2) {
					return new ModelAndView("redirect:/staff/" + user.getId() + "/create");
				} else if (user.getRoleId() == 3) {
					return new ModelAndView("redirect:/finance/home");
				} else if (user.getRoleId() == 4) {
					return new ModelAndView("redirect:/legal/" + user.getId() + "/view");
				} else if (user.getRoleId() == 5) {
					return new ModelAndView("redirect:/admin/" +user.getId() + "/view");
				}
			} else {
				return new ModelAndView("redirect:/patient/home");
			}
		}
		return new ModelAndView("userLogin");
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView validateLogin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);

		// Check if id is valid
		if (id.isEmpty()) {
			map.put("errorMessage", "Please enter an id number");
			return new ModelAndView("userLogin", map);
		}

		// Check if password is valid
		if (password.isEmpty()) {
			map.put("errorMessage", "Please enter a password");
			return new ModelAndView("userLogin", map);
		}

		// Get user
		User user = userService.getUser(id);
		Patient patient = patientService.findByHealthCard(id);

		// No user with specified id
		if (user == null && patient == null) {
			map.put("errorMessage", "Incorrect credentials");
			return new ModelAndView("userLogin", map);
		}

		// Validate password
		if ((user != null && !user.getPassword().equals(password))
				|| (patient != null && !patient.getPassword().equals(password))) {
			map.put("errorMessage", "Incorrect credentials");
			return new ModelAndView("userLogin", map);
		}

		if (user != null && user.getPassword().equals(password)) {
			map.put("errorMessage", "Logged in as user");
			Role role = userService.getRole(user);
			session.setAttribute("role", role.getName());
			session.setAttribute("user", user);
			// Check role to determine which page the user should be directed to
			if(user.getRoleId() == 1) {
				return new ModelAndView("redirect:/doctor/" + user.getId() + "/patients");
				
			}
			else if(user.getRoleId() == 2) {
				return new ModelAndView("redirect:/staff/" + user.getId() + "/create");
			} else if (user.getRoleId() == 3) {
				return new ModelAndView("redirect:/finance/home");
			} else if (user.getRoleId() == 4) {
				return new ModelAndView("redirect:/legal/" + user.getId() + "/view");
			} else if (user.getRoleId() == 5) {
				return new ModelAndView("redirect:/admin/" + user.getId() + "/home");
			}
		} else if (patient != null && patient.getPassword().equals(password)){
			map.put("errorMessage", "Logged in as patient");
			session.setAttribute("role", "patient");
			session.setAttribute("patient", patient);
			return new ModelAndView("redirect:/patient/home");
		}

		return new ModelAndView("userLogin", map);
	}
	
	
	@RequestMapping(value ="logout",method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		//return new ModelAndView("userLogin");
		return new ModelAndView("redirect:/");
	}
	
}
