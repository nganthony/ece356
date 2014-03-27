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
