package com.ece356.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ece356.dao.CurrentHealthDao;
import com.ece356.domain.Patient;
import com.ece356.domain.User;
import com.ece356.domain.Visit;
import com.ece356.domain.VisitAudit;
import com.ece356.service.PatientService;
import com.ece356.service.UserService;
import com.ece356.service.VisitAuditService;
import com.ece356.service.VisitService;

@Controller
@RequestMapping("staff")
public class StaffController {
	@Autowired
	UserService userService;
	@Autowired
	CurrentHealthDao currentHealthDao;
	@Autowired
	PatientService patientService;
	@Autowired
	VisitService visitService;
	@Autowired
	VisitAuditService visitAuditService;

	@RequestMapping(value = "{staffId}/create", method = RequestMethod.GET)
	public String getCreatePage(@PathVariable("staffId") int staffId, Model model) {
		
		model.addAttribute("staffID", staffId);
		return "staffnavigation";

	}

	@RequestMapping(value = "{staffId}/doctor/view", method = RequestMethod.GET)
	public String getDoctorView(@PathVariable("staffId") int staffId, Model model) {
		List<User> doctors = userService.getAllDoctors();
		model.addAttribute("staffId", staffId);
		model.addAttribute("users", doctors);
		return "doctorView";
	}
	
	@RequestMapping(value = "{staffId}/appointment/view", method = RequestMethod.GET)
	public String getVisitView(@PathVariable("staffId") int staffId, Model model) {
		List<Map<String, Object>> patientVisit= new ArrayList<Map<String, Object>>();
		List<Visit> visits = visitService.staffGetAllVisits(staffId);
		for (Visit	visit : visits) {
			Map<String, Object> patientVisitMap = new HashMap<String, Object>();
			patientVisitMap.put("visit", visit);
			Patient patient = patientService.findByHealthCard(visit.getHealth_card());
			patientVisitMap.put("patient", patient);
			patientVisit.add(patientVisitMap);
		}
		

		model.addAttribute("staffId", staffId);
		model.addAttribute("patientVisits", patientVisit);
		return "staffAppointmentsView";
	}
	
	
	
	@RequestMapping(value = "{staffId}/appointment/view", method = RequestMethod.POST)
	public String getVisitFilteredView(@PathVariable("staffId") int staffId, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {
		List<Map<String, Object>> patientVisit= new ArrayList<Map<String, Object>>();
		String search = request.getParameter("search");
		List<Visit> visits = visitService.staffGetFilteredVisits(staffId, search);
		for (Visit	visit : visits) {
			Map<String, Object> patientVisitMap = new HashMap<String, Object>();
			patientVisitMap.put("visit", visit);
			Patient patient = patientService.findByHealthCard(visit.getHealth_card());
			patientVisitMap.put("patient", patient);
			patientVisit.add(patientVisitMap);
		}
		model.addAttribute("staffId", staffId);
		model.addAttribute("search", search);
		model.addAttribute("patientVisits", patientVisit);
		return "staffAppointmentsView";
	}
	
	@RequestMapping(value = "{staffId}/doctor/schedule/{id}", method = RequestMethod.GET)
	public String doctorSchedule1(@PathVariable("staffId") int staffId,@PathVariable("id") int id, Model model) {
		List<Visit> visits  = visitService.getDoctorSchedule(id);
		model.addAttribute("staffId", staffId);
		model.addAttribute("visits", visits);
		model.addAttribute("id", id);
		return "staffDoctorAppointments";
	}
	
	@RequestMapping(value = "{staffId}/doctor/schedule/{user_id}/delete/{id}", method = RequestMethod.GET)
	public ModelAndView doctorScheduleDelete(@PathVariable("staffId") int staffId,@PathVariable("user_id") int user_id, 
			@PathVariable("id") int id,Model model) {
		List<Visit> visits  = visitService.getDoctorSchedule(user_id);
		Visit visit = visitService.getVisit(id);
		visitService.delete(id);
		
		Date date = new Date();
		Timestamp now = new Timestamp(date.getTime());
		if (visit.getStart().after(now)) {
			Patient patient = patientService.findByHealthCard(visit.getHealth_card());
			int numberOfVisits = patient.getNumberOfVisits();
			patient.setNumberOfVisits(numberOfVisits-1);
			patientService.update(patient);
		}
		
		insertIntoAuditTable(visit, staffId, "delete", visit.getId());
		model.addAttribute("staffId", staffId);
		model.addAttribute("visits", visits);
		model.addAttribute("user_id", user_id);
		model.addAttribute("id", id);
		return new ModelAndView(new RedirectView("/1.0.0-BUILD-SNAPSHOT/staff/" + String.valueOf(staffId) + "/doctor/schedule/" + String.valueOf(user_id)));
	}
	
	@RequestMapping(value = "{staffId}/doctor/schedule/{user_id}/{id}", method = RequestMethod.GET)
	public String rescheduleAppointment(@PathVariable("staffId") int staffId,@PathVariable("user_id") int user_id, 
			@PathVariable("id") int id,Model model) {
		Visit visit = visitService.getVisit(id);
		visit.setEdit(true);
		model.addAttribute("staffId", staffId);
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
	public ModelAndView submit(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, @PathVariable("staffId") int staffId, 
			@PathVariable("user_id") int user_id, 
			@PathVariable("id") int id,@Valid @ModelAttribute("visit") Visit visit,
			BindingResult result, Model model) {
		String start = request.getParameter("startTime");
		String end = request.getParameter("endTime");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("staffId", staffId);
		map.put("user_id", user_id);
		map.put("id", id);
		if (result.hasErrors()) {
			map.put("id", user_id);
			map.put("errorMessage", "There is an error in one of the forms");
			return new ModelAndView("createAppointment", map);
		}
		try {
			Timestamp startTimestamp = Timestamp.valueOf(start);
			Timestamp endTimestamp = Timestamp.valueOf(end);
			visit.setStart(startTimestamp);
			visit.setEnd(endTimestamp);
			if (!startTimestamp.before(endTimestamp)) {
				map.put("errorMessage", "The start of the appointment should come before it ends");
				return new ModelAndView("createAppointment", map);
			}
			if (!visitService.verifyScheduleDates(startTimestamp, endTimestamp, visit.getId(),visit.getUser_id(), visit.getHealth_card())) {
				map.put("errorMessage", "There is a conflict with the dates. Please choose another time");
				return new ModelAndView("createAppointment", map);
			}
		} catch (Exception e) {
			map.put("errorMessage", "Please enter times for the start and end of the appointment");
			return new ModelAndView("createAppointment", map);
		}
		
		Patient patient = patientService.findByHealthCard(visit.getHealth_card());
		if (patient == null) {
			map.put("errorMessage", "Health Card does not match with a patient");
			return new ModelAndView("createAppointment", map);
		}
		model.addAttribute("staffId", staffId);
		model.addAttribute("id", user_id);
		model.addAttribute("visit", visit);
		visit.setUser_id(user_id);
		
		if (visitService.getVisit(id) != null) {
			visitService.updateVisit(visit);
			insertIntoAuditTable(visit, staffId, "update", visit.getId());
		} else {
			int visitId = visitService.createVisit(visit);
			int numberOfVisits = patient.getNumberOfVisits();
			patient.setNumberOfVisits(numberOfVisits+1);
			patientService.update(patient);
			insertIntoAuditTable(visit, staffId, "insert", visitId);
		}
		ModelAndView modelAndView = new ModelAndView(new RedirectView("/1.0.0-BUILD-SNAPSHOT/staff/" + String.valueOf(staffId) + "/doctor/schedule/" + String.valueOf(user_id)));
		return modelAndView;
		
	}

	@RequestMapping(value = "{staffId}/create/appointment/{user_id}/{id}", method = RequestMethod.GET)
	public String createDoctorAppointment(@PathVariable("staffId") int staffId, @PathVariable("user_id") int user_id, @PathVariable("id") int id,Model model) {
		Visit getVisit = visitService.getVisit(id);
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
	}
	
	private void insertIntoAuditTable(Visit visit, int user_id, String type, int visitId) {
		VisitAudit visitAudit = new VisitAudit();
		visitAudit.setVisitId(visitId);
		visitAudit.setComment(visit.getComment());
		visitAudit.setDiagnosis(visit.getDiagnosis());
		visitAudit.setDuration(visit.getDuration());
		visitAudit.setEnd(visit.getEnd());
		visitAudit.setHealth_card(visit.getHealth_card());
		visitAudit.setModifiedById(user_id);
		visitAudit.setModifyType(type);
		visitAudit.setStart(visit.getStart());
		visitAudit.setSurgery(visit.getSurgery());
		visitAudit.setUser_id(visit.getUser_id());
		visitAudit.setModifiedOn(new Timestamp((new Date()).getTime()));
		visitAuditService.insert(visitAudit);
	}
}
