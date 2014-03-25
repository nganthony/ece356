package com.ece356.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ece356.domain.User;
import com.ece356.service.VisitService;

@Controller
@RequestMapping("finance")
public class FinanceController {

	@Autowired
	VisitService visitService;

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {

		String role = (String) session.getAttribute("role");
		if (role != null && "finance".equals(role.toLowerCase())) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				model.addAttribute("user", user);
				return "financeHome";
			}
		}
		return "redirect:/user/login";

	}

	@RequestMapping(value = "home", method = RequestMethod.POST)
	public String getDoctors(Model model, HttpSession session,
			HttpServletRequest request) {

		String role = (String) session.getAttribute("role");
		if (role != null && "finance".equals(role.toLowerCase())) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				model.addAttribute("user", user);
				try {
					Timestamp startTimestamp = Timestamp.valueOf(request
							.getParameter("startTime"));
					Timestamp endTimestamp = Timestamp.valueOf(request
							.getParameter("endTime"));
					List<User> doctors = visitService.getCountPerDoctor(
							startTimestamp, endTimestamp);
					model.addAttribute("doctors", doctors);
				} catch (Exception e) {
					model.addAttribute("errorMessage", "Invalid Date");
					e.printStackTrace();
				}
				return "financeHome";
			}
		}
		return "redirect:/user/login";

	}
}
