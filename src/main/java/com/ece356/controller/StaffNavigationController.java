package com.ece356.controller;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ece356.dao.CurrentHealthDao;
import com.ece356.dao.PatientDao;
import com.ece356.dao.UserDao;
import com.ece356.dao.VisitDao;
import com.ece356.domain.Patient;
import com.ece356.domain.User;
import com.ece356.domain.Visit;
import com.ece356.service.VisitService;

@Controller
@RequestMapping("staffnavigation")
public class StaffNavigationController {
	@Autowired
	UserDao userDao;
	@Autowired
	CurrentHealthDao currentHealthDao;
	@Autowired
	PatientDao patientDao;
	@Autowired
	VisitService visitService;
	@Autowired
	VisitDao visitDao;

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String getCreatePage(Model model,
			@ModelAttribute("patient") Patient patient) {
		List<User> doctors = userDao.getAllDoctors();
		Map<Integer, String> doctorsMap = new HashMap<Integer, String>();
		for (User user : doctors) {
			doctorsMap.put(user.getId(),
					user.getLastName() + ", " + user.getFirstName());
		}
		model.addAttribute("doctors", doctorsMap);
		model.addAttribute("currentHealthMap",
				currentHealthDao.getCurrentHealths());
		model.addAttribute("patient", patient);
		return "staffnavigation";

	}

	@RequestMapping(value = "doctor/view", method = RequestMethod.GET)
	public String getView(Model model) {
		List<User> doctors = userDao.getAllDoctors();
		model.addAttribute("users", doctors);
		return "doctorView";
	}

	@RequestMapping(value = "doctor/schedule/{id}", method = RequestMethod.GET)
	public String doctorSchedule1(@PathVariable("id") int id, Model model) {
		List<Visit> visits  = visitDao.getDoctorSchedule(id);
		model.addAttribute("visits", visits);
		return "doctorSchedule";
	}

	@RequestMapping(value = "create/appointment", method = RequestMethod.GET)
	public String getCreateAppointment(Model model,
			@ModelAttribute("visit") Visit visit) {
		model.addAttribute("visit", visit);

		return "createAppointment";

	}

	@RequestMapping(value = "create/appointment", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("visit") Visit visit,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return doctorSchedule1(1, model);
		}
		Visit newVisit = visitService.createVisit(visit);
		return doctorSchedule1(newVisit.getUser_id(), model);
	}

	@RequestMapping(value = "create/appointment/{id}", method = RequestMethod.GET)
	public String createDoctorAppointment(@PathVariable int id, Model model) {
		Visit visit = new Visit();
		visit.setHealth_card("123454321");
		visit.setDuration(1);
		Date now = new Date();
		visit.setStart(new Timestamp(now.getTime()));
		visit.setEnd(new Timestamp(now.getTime()+3600000));
		visit.setUser_id(id);
		model.addAttribute("visit", visit);
		return getCreateAppointment(model, visit);
	}




	//	@RequestMapping(method = RequestMethod.GET)
	//	public String printWelcome1() {
	// 
	//		return "staffnavigation";
	//
	//	}

}
