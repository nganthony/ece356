package com.ece356.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ece356.dao.RoleDao;
import com.ece356.domain.Patient;
import com.ece356.domain.Role;
import com.ece356.domain.User;
import com.ece356.service.PatientService;
import com.ece356.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	PatientService patientService;

	@Autowired
	RoleDao roleDao;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String showLoginPage() {

		return "userLogin";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
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
			}
		} else if (patient != null && patient.getPassword().equals(password)){
			map.put("errorMessage", "Logged in as patient");
			session.setAttribute("role", "patient");
			session.setAttribute("patient", patient);
			return new ModelAndView("redirect:/patient/home");
		}

		return new ModelAndView("userLogin", map);
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String showCreatePage(Model model, @ModelAttribute("user") User user) {

		model.addAttribute("user", user);

		List<Role> roles = roleDao.getAllRoles();

		Map<Integer, String> rolesMap = new HashMap<Integer, String>();
		for (Role role : roles) {
			rolesMap.put(role.getId(), role.getName());
		}

		model.addAttribute("roles", rolesMap);

		return "userCreate";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String validateCreate(@Valid @ModelAttribute("user") User user,
			BindingResult result, Model model) throws Exception {
		if (result.hasErrors()) {
			return showCreatePage(model, user);
		}

		User createdUser = userService.createUser(user);

		// TODO: Navigate to specific role page
		return "userLogin";
	}
}
