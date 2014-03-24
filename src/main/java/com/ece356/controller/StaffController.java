package com.ece356.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.ece356.dao.CurrentHealthDao;
import com.ece356.dao.PatientDao;
import com.ece356.dao.UserDao;
import com.ece356.dao.VisitDao;
import com.ece356.domain.User;
import com.ece356.domain.Visit;
import com.ece356.service.VisitService;

@Controller
@RequestMapping("staff")
public class StaffController {
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
		return "staffDoctorAppointments";
	}
	
	@RequestMapping(value = "{staffId}/doctor/schedule/{user_id}/delete/{id}", method = RequestMethod.GET)
	public String doctorScheduleDelete(@PathVariable("staffId") int staffId,@PathVariable("user_id") int user_id, 
			@PathVariable("id") int id,Model model) {
		List<Visit> visits  = visitDao.getDoctorSchedule(user_id);
		visitDao.delete(id);
		model.addAttribute("staffId", staffId);
		model.addAttribute("visits", visits);
		model.addAttribute("user_id", user_id);
		model.addAttribute("id", id);
		return doctorSchedule1(staffId, user_id, model);
	}
	
	@RequestMapping(value = "{staffId}/doctor/schedule/{user_id}/{id}", method = RequestMethod.GET)
	public String rescheduleAppointment(@PathVariable("staffId") int staffId,@PathVariable("user_id") int user_id, 
			@PathVariable("id") int id,Model model) {
		Visit visit = visitDao.getVisit(id);
//		List<Visit> visits  = visitDao.getDoctorSchedule(user_id);
//		visitDao.delete(id);
		model.addAttribute("staffId", staffId);
//		model.addAttribute("visits", visits);
		model.addAttribute("user_id", user_id);
		model.addAttribute("visit", visit);
		model.addAttribute("id", id);
		return "createAppointment";
	}

	@RequestMapping(value = "{staffId}/create/appointment", method = RequestMethod.GET)
	public String getCreateAppointment(@PathVariable("staffId") int staffId, Model model,
			@ModelAttribute("visit") Visit visit) {
		model.addAttribute("visit", visit);
		model.addAttribute("staffId", staffId);
		return "createAppointment";

	}

	@RequestMapping(value = "{staffId}/create/appointment/{user_id}/{id}", method = RequestMethod.POST)
	public String submit(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, @PathVariable("staffId") int staffId, 
			@PathVariable("user_id") int user_id, 
			@PathVariable("id") int id,@Valid @ModelAttribute("visit") Visit visit,
			BindingResult result, Model model) {
		String start = request.getParameter("start");
		String end = request.getParameter("end");
//		if (result.hasErrors()) {
//			return doctorSchedule1(staffId, id , model);
//		}
		
		Timestamp startTimestamp = Timestamp.valueOf(start);
		Timestamp endTimestamp = Timestamp.valueOf(end);
		model.addAttribute("staffId", staffId);
		visit.setHealth_card("124323432123");
		visit.setDuration(1);
		visit.setUser_id(user_id);
		Date now = new Date();
		visit.setStart(startTimestamp);
		visit.setEnd(endTimestamp);
		if (visit.getEdit() == true) {
			visitService.updateVisit(visit);
		} else {
			visitService.createVisit(visit);
		}
		visit.setEdit(true);
		//return new ModelAndView("redirect:/staff/" +staffId+ "/doctor/schedule"+ visit.getId());
		return doctorSchedule1(staffId, user_id, model);
	}

	@RequestMapping(value = "{staffId}/create/appointment/{user_id}/{id}", method = RequestMethod.GET)
	public String createDoctorAppointment(@PathVariable("staffId") int staffId, @PathVariable("user_id") int user_id, @PathVariable("id") int id,Model model) {
		Visit getVisit = visitDao.getVisit(id);
		Visit visit = new Visit();
		visit.setId(0);
		if (getVisit != null) {
			visit.setStart(getVisit.getStart());
			visit.setEnd(getVisit.getEnd());
			visit.setId(id);
		}
		visit.setHealth_card("124323432123");
		visit.setDuration(1);
		visit.setUser_id(user_id);
		model.addAttribute("staffId", staffId);
		model.addAttribute("id", id);
		model.addAttribute("visit", visit);
		return "createAppointment";
		//return getCreateAppointment(model, visit);
	}
}
