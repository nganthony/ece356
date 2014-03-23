package com.ece356.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("doctor")
public class DoctorController {
	
	@RequestMapping(value = "{doctorId}/patients", method= RequestMethod.GET)
	public String showPatientsPage(@PathVariable("doctorId") int doctorId) {
		
		return "doctorPatients";
	}
	
	@RequestMapping(value = "{doctorId}/appointments", method= RequestMethod.GET)
	public String showAppintmentsPage(@PathVariable("doctorId") int doctorId) {
		
		return "doctorAppointments";
	}
}
