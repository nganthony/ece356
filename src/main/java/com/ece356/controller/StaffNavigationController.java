package com.ece356.controller;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
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
@RequestMapping("staff")
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

	@RequestMapping(value = "{staffId}/create", method = RequestMethod.GET)
	public String getCreatePage(@PathVariable("staffId") int staffId, Model model) {
		
		model.addAttribute("staffID", staffId);
		return "staffnavigation";

	}

	@RequestMapping(value = "{staffId}/doctor/view", method = RequestMethod.GET)
	public String getView(@PathVariable("staffId") int staffId, Model model) {
		List<User> doctors = userDao.getAllDoctors();
		model.addAttribute("staffId", staffId);
		model.addAttribute("users", doctors);
		return "doctorView";
	}

	@RequestMapping(value = "{staffId}/doctor/schedule/{id}", method = RequestMethod.GET)
	public String doctorSchedule1(@PathVariable("staffId") int staffId,@PathVariable("id") int id, Model model) {
		List<Visit> visits  = visitDao.getDoctorSchedule(id);
		model.addAttribute("staffId", staffId);
		model.addAttribute("visits", visits);
		model.addAttribute("id", id);
		return "doctorSchedule";
	}

	@RequestMapping(value = "{staffId}/create/appointment", method = RequestMethod.GET)
	public String getCreateAppointment(@PathVariable("staffId") int staffId, Model model,
			@ModelAttribute("visit") Visit visit) {
		model.addAttribute("visit", visit);
		model.addAttribute("staffId", staffId);
		return "createAppointment";

	}

	@RequestMapping(value = "{staffId}/create/appointment/{id}", method = RequestMethod.POST)
	public String submit(@PathVariable("staffId") int staffId, @PathVariable int id, @Valid @ModelAttribute("visit") Visit visit,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return doctorSchedule1(staffId, id , model);
		}
		model.addAttribute("staffId", staffId);
		visit.setHealth_card("124323432123");
		visit.setDuration(1);
		visit.setUser_id(id);
		Date now = new Date();
		visit.setStart(new Timestamp(now.getTime()));
		visit.setEnd(new Timestamp(now.getTime()+3600000));
		visit.setUser_id(id);
		
		visitService.createVisit(visit);
		return doctorSchedule1(staffId,visit.getId(), model);
	}

	@RequestMapping(value = "{staffId}/create/appointment/{id}", method = RequestMethod.GET)
	public String createDoctorAppointment(@PathVariable("staffId") int staffId, @PathVariable int id, Model model) {
		Visit visit = new Visit();
		visit.setHealth_card("124323432123");
		visit.setDuration(1);
		visit.setUser_id(id);
		model.addAttribute("staffId", staffId);
		model.addAttribute("visit", visit);
		return "createAppointment";
		//return getCreateAppointment(model, visit);
	}




	//	@RequestMapping(method = RequestMethod.GET)
	//	public String printWelcome1() {
	// 
	//		return "staffnavigation";
	//
	//	}

}
