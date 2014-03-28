package com.ece356.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ece356.dao.UserPatientDao;
import com.ece356.domain.Patient;
import com.ece356.domain.User;
import com.ece356.domain.Visit;
import com.ece356.service.PatientService;
import com.ece356.service.UserService;
import com.ece356.service.VisitService;

@Controller
@RequestMapping("doctor")
public class DoctorController {
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	VisitService visitService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserPatientDao userPatientDao;
	
	@RequestMapping(value = "{doctorId}/patients", method= RequestMethod.GET)
	public String showPatientsPage(@PathVariable("doctorId") int doctorId, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws Exception {
		
		User user = (User)session.getAttribute("user");
		model.addAttribute(user);
		
		List<Patient> defaultPatients = patientService.getAllPatients(user.getId());
		List<Patient> visitedPatients = visitService.getVisitedPatients(user.getId());
		
		model.addAttribute("defaultPatients", defaultPatients);
		model.addAttribute("visitedPatients", visitedPatients);
		
		return "doctorPatients";
	}
	
	@RequestMapping(value = "{doctorId}/patients", method= RequestMethod.POST)
	public String showFilteredPatientsPage(@PathVariable("doctorId") int doctorId, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws Exception {
		
		User user = (User)session.getAttribute("user");
		model.addAttribute(user);
		
		String search = request.getParameter("search");
		
		List<Patient> defaultPatients = patientService.getAllPatients(user.getId(), search);
		List<Patient> visitedPatients = visitService.getVisitedPatients(user.getId(), search);
		model.addAttribute("defaultPatients", defaultPatients);
		model.addAttribute("visitedPatients", visitedPatients);
		
		model.addAttribute("search", search);
		
		return "doctorPatients";
	}
	
	@RequestMapping(value = "{doctorId}/appointments", method= RequestMethod.GET)
	public String showAppointmentsPage(@PathVariable("doctorId") int doctorId, Model model) {
		
		List<Visit> visits  = visitService.getDoctorSchedule(doctorId);
		model.addAttribute("visits", visits);
		return "doctorAppointments";
	}
	
	@RequestMapping(value = "{doctorId}/appointments", method= RequestMethod.POST)
	public String showFilteredAppintmentsPage(@PathVariable("doctorId") int doctorId, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {
		
		String search = request.getParameter("search");
		
		List<Visit> visits  = visitService.getDoctorSchedule(doctorId, search);
		model.addAttribute("visits", visits);
		model.addAttribute("search", search);
		model.addAttribute("doctorId", doctorId);
		
		return "doctorAppointments";
	}
	
	@RequestMapping(value = "{doctorId}/update_appointment/{visitId}", method = RequestMethod.GET)
	public String showUpdateAppointmentPage(@PathVariable int visitId, Model model) {
		Visit visit = visitService.getVisit(visitId);
		model.addAttribute("visit", visit);
		return "doctorUpdateAppointment";
	}
	
	@RequestMapping(value = "{doctorId}/update_appointment/{visitId}", method = RequestMethod.POST)
	public String updateAppointment(@PathVariable int doctorId, @PathVariable int visitId, @ModelAttribute Visit visit, Model model) {
		visit.setUser_id(doctorId);
		visit.setId(visitId);
		visitService.updateForDoctor(visit);
		
		return "redirect:/doctor/" + doctorId + "/appointments";
	}
	
	@RequestMapping(value = "{doctorId}/patient/{healthCard}/records", method = RequestMethod.GET)
	public String getPatientRecord(@PathVariable("doctorId") int doctorId, @PathVariable("healthCard") String healthCard, Model model) {
		
		Patient patient = patientService.findByHealthCard(healthCard);
		List<Visit> visits = visitService.getPatientVisit(healthCard, doctorId);
		
 		model.addAttribute("patient", patient);
		model.addAttribute("visits", visits);
		
		return "doctorPatientRecordView";
	}
	
	@RequestMapping(value = "{doctorId}/patient/{healthCard}/permissions", method = RequestMethod.GET)
	public String getPatientPermissions(@PathVariable("doctorId") int doctorId, @PathVariable("healthCard") String healthCard, Model model) {
		
		Patient patient = patientService.findByHealthCard(healthCard);	
 		model.addAttribute("patient", patient);

 		List<User> doctorsWithPermission = userService.getDoctorsWithPermission(doctorId, healthCard);
 		model.addAttribute("doctorsWithPermission", doctorsWithPermission);
 		
 		List<User> doctorsWithoutPermission = userService.getDoctorsWithoutPermission(doctorId, healthCard);
 		model.addAttribute("doctorsWithoutPermission", doctorsWithoutPermission);
 		
		return "doctorPatientPermissionView";
	}
	
	@RequestMapping(value = "{doctorId}/patient/{healthCard}/back", method = RequestMethod.GET)
	public String back(@PathVariable("doctorId") int doctorId, @PathVariable("healthCard") String healthCard, Model model) {
		
		return "redirect:/doctor/" + doctorId + "/patients";
	}
	
	@RequestMapping(value = "{doctorId}/patient/{healthCard}/grant_permission/{userId}", method = RequestMethod.POST)
	public String grantPermissions(@PathVariable("doctorId") int doctorId, @PathVariable("healthCard") String healthCard, @PathVariable("userId") int userId, Model model) {
 		
		userPatientDao.insert(doctorId, userId, healthCard);
 		
		return "redirect:/doctor/" + doctorId + "/patient/" + healthCard + "/permissions";
	}
	
	@RequestMapping(value = "{doctorId}/patient/{healthCard}/revoke_permission/{userId}", method = RequestMethod.POST)
	public String revokePermissions(@PathVariable("doctorId") int doctorId, @PathVariable("healthCard") String healthCard, @PathVariable("userId") int userId, Model model) {
 		
		userPatientDao.delete(doctorId, userId, healthCard);
 		
		return "redirect:/doctor/" + doctorId + "/patient/" + healthCard + "/permissions";
	}
}
