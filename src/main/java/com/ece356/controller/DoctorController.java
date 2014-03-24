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

import com.ece356.domain.Patient;
import com.ece356.domain.User;
import com.ece356.domain.Visit;
import com.ece356.service.PatientService;
import com.ece356.service.VisitService;

@Controller
@RequestMapping("doctor")
public class DoctorController {
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	VisitService visitService;
	
	@RequestMapping(value = "{doctorId}/patients", method= RequestMethod.GET)
	public String showPatientsPage(@PathVariable("doctorId") int doctorId, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws Exception {
		
		User user = (User)session.getAttribute("user");
		model.addAttribute(user);
		
		List<Patient> patients = patientService.getAllPatients(user.getId());
		model.addAttribute("patients", patients);
		
		return "doctorPatients";
	}
	
	@RequestMapping(value = "{doctorId}/patients", method= RequestMethod.POST)
	public String showFilteredPatientsPage(@PathVariable("doctorId") int doctorId, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws Exception {
		
		User user = (User)session.getAttribute("user");
		model.addAttribute(user);
		
		String search = request.getParameter("search");
		
		List<Patient> patients = patientService.getAllPatients(user.getId(), search);
		model.addAttribute("patients", patients);
		
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
		visitService.update(visit);
		return "redirect:/doctor/" + doctorId + "/appointments";
	}
}
