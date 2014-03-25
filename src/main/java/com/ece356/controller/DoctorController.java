package com.ece356.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ece356.domain.Patient;
import com.ece356.domain.User;
import com.ece356.service.PatientService;

@Controller
@RequestMapping("doctor")
public class DoctorController {
	
	@Autowired
	PatientService patientService;
	
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
	public String showAppintmentsPage(@PathVariable("doctorId") int doctorId) {
		
		return "doctorAppointments";
	}
}
