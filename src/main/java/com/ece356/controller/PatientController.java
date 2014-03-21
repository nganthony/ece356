package com.ece356.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ece356.dao.CurrentHealthDao;
import com.ece356.dao.PatientDao;
import com.ece356.dao.UserDao;
import com.ece356.domain.Patient;
import com.ece356.domain.User;

@Controller
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	UserDao userDao;
	@Autowired
	CurrentHealthDao currentHealthDao;
	@Autowired
	PatientDao patientDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getCreatePage(Model model) {
		List<User> doctors = userDao.getAllDoctors();
		Map<Integer, String> doctorsMap = new HashMap<Integer, String>();
		for (User user : doctors) {
			doctorsMap.put(user.getId(),
					user.getLastName() + ", " + user.getFirstName());
		}
		model.addAttribute("doctors", doctorsMap);
		model.addAttribute("currentHealthMap",
				currentHealthDao.getCurrentHealths());
		return new ModelAndView("patientCreate", "patient", new Patient());

	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(@ModelAttribute("Patient") Patient patient,
			BindingResult result) {
		Date now = new Date();
		patient.setLastVisitDate(new Timestamp(now.getTime()));
		patientDao.insert(patient);
		return "welcome";

	}

}
