package com.ece356.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ece356.domain.User;
import com.ece356.domain.Visit;
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
					Timestamp startTimestamp = Timestamp.valueOf(request.getParameter("startTime") +" 00:00:00");
					Timestamp endTimestamp = Timestamp.valueOf(request.getParameter("endTime")+" 00:00:00");
					List<User> doctors = visitService.getCountPerDoctor(
							startTimestamp, endTimestamp);
					
					model.addAttribute("startTime", request.getParameter("startTime"));
					model.addAttribute("endTime", request.getParameter("endTime"));
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

	@RequestMapping(value = "detail/{id}/{start}/{end}", method = RequestMethod.GET)
	public String getDetails(Model model, HttpSession session,
			@PathVariable("id") String id, @PathVariable("start") String start,
			@PathVariable("end") String end) {
		try {
			Timestamp startTimestamp = Timestamp.valueOf(start + " 00:00:00");
			Timestamp endTimestamp = Timestamp.valueOf(end + " 00:00:00");
			int userId = Integer.parseInt(id);
			List<Visit> visits = visitService.getVisitForStaffInRange(
					startTimestamp, endTimestamp, userId);
			model.addAttribute("visits", visits);
			return "financeDetail";
		} catch (Exception e) {
			return "financeHome";
		}
	}

}
