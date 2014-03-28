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
		if (Util.isValidDoctor(session)) {
			User user = (User)session.getAttribute("user");
			model.addAttribute(user);
			
			List<Patient> defaultPatients = patientService.getAllPatients(user.getId());
			List<Patient> visitedPatients = visitService.getVisitedPatients(user.getId());
			
			model.addAttribute("defaultPatients", defaultPatients);
			model.addAttribute("visitedPatients", visitedPatients);

			return "doctorPatients";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "{doctorId}/patients", method= RequestMethod.POST)
	public String showFilteredPatientsPage(@PathVariable("doctorId") int doctorId, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) throws Exception {
		if (Util.isValidDoctor(session)) {
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
		return "redirect:/";
	}
	
	@RequestMapping(value = "{doctorId}/appointments", method= RequestMethod.GET)
	public String showAppointmentsPage(@PathVariable("doctorId") int doctorId, Model model,HttpSession session) {
		if (Util.isValidDoctor(session)) {
			List<Visit> visits = visitService.getDoctorSchedule(doctorId);
			model.addAttribute("visits", visits);
			return "doctorAppointments";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "{doctorId}/appointments", method= RequestMethod.POST)
	public String showFilteredAppintmentsPage(@PathVariable("doctorId") int doctorId, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {
		if (Util.isValidDoctor(session)) {
			String search = request.getParameter("search");

			List<Visit> visits = visitService.getDoctorSchedule(doctorId, search);
			model.addAttribute("visits", visits);
			model.addAttribute("search", search);
			model.addAttribute("doctorId", doctorId);

			return "doctorAppointments";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "{doctorId}/update_appointment/{visitId}", method = RequestMethod.GET)
	public String showUpdateAppointmentPage(@PathVariable int visitId, Model model, HttpSession session) {
		if (Util.isValidDoctor(session)) {
			Visit visit = visitService.getVisit(visitId);
			model.addAttribute("visit", visit);
			return "doctorUpdateAppointment";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "{doctorId}/update_appointment/{visitId}", method = RequestMethod.POST)
	public String updateAppointment(@PathVariable int doctorId, @PathVariable int visitId, @ModelAttribute Visit visit, Model model, HttpSession session) {
		if (Util.isValidDoctor(session)) {
			visit.setUser_id(doctorId);
			visit.setId(visitId);
			visitService.updateForDoctor(visit);

			return "redirect:/doctor/" + doctorId + "/appointments";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "{doctorId}/patient/{healthCard}/records", method = RequestMethod.GET)
	public String getPatientRecord(@PathVariable("doctorId") int doctorId, @PathVariable("healthCard") String healthCard, Model model, HttpSession session) {
		if (Util.isValidDoctor(session)) {
			Patient patient = patientService.findByHealthCard(healthCard);
			List<Visit> visits = visitService.getPatientVisit(healthCard, doctorId);
			User defaultDoctor = patientService.getDefaultDoctor(patient);
			
			model.addAttribute("defaultDoctor", defaultDoctor.getFirstName()
					+ " " + defaultDoctor.getLastName());
			model.addAttribute("patient", patient);
			model.addAttribute("visits", visits);

			return "doctorPatientRecordView";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "{doctorId}/patient/{healthCard}/permissions", method = RequestMethod.GET)
	public String getPatientPermissions(@PathVariable("doctorId") int doctorId, @PathVariable("healthCard") String healthCard, Model model , HttpSession session) {
		if (Util.isValidDoctor(session)) {
			Patient patient = patientService.findByHealthCard(healthCard);
			model.addAttribute("patient", patient);

			return "doctorPatientPermissionView";
		}
		return "redirect:/";
	}
	
}
