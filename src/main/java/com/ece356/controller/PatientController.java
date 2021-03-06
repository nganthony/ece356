package com.ece356.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ece356.dao.UserDao;
import com.ece356.domain.Patient;
import com.ece356.domain.User;
import com.ece356.domain.Visit;
import com.ece356.service.PatientService;
import com.ece356.service.VisitService;

@Controller
@RequestMapping()
public class PatientController {

	@Autowired
	UserDao userDao;
	@Autowired
	CurrentHealthDao currentHealthDao;
	@Autowired
	PatientService patientService;
	@Autowired
	VisitService vistService;

	@RequestMapping(value = "/patient/create", method = RequestMethod.GET)
	public String getCreatePage(Model model,
			@ModelAttribute("patient") Patient patient, HttpSession session) {
		if (Util.isValidStaff(session)) {
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

			return "patientCreate";
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/patient/create", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("patient") Patient patient,
			BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			return getCreatePage(model, patient, session);
		} else {
			if (patient.isEdit()) {
				patientService.update(patient);
				return getView(model);
			} else {
				Date now = new Date();
				patient.setLastVisitDate(new Timestamp(now.getTime()));
				Patient existingPatient = patientService
						.findByHealthCard(patient.getHealthCard());
				if (existingPatient == null) {
					patient.setNumberOfVisits(0);
					patientService.insert(patient);
					return getView(model);
				} else {
					result.rejectValue("healthCard", "error.patient",
							"This Patient already exists");
					return getCreatePage(model, patient, session);
				}
			}
		}
	}

	@RequestMapping(value = "/patient/view", method = RequestMethod.GET)
	public String getView(Model model) {
		List<Patient> patients = patientService.getAllPatients();
		model.addAttribute("patients", patients);
		return "patientView";
	}

	@RequestMapping(value = "/patient/edit/{healthCard}")
	public String editPatient(@PathVariable String healthCard, Model model,
			HttpSession session) {
		if (Util.isValidStaff(session)) {
			Patient patient = patientService.findByHealthCard(healthCard);
			patient.setEdit(true);
			return getCreatePage(model, patient, session);
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/patient/home", method = RequestMethod.GET)
	public String patientHome(Model model, HttpSession session) {
		if (Util.isValidPatient(session)) {
			Patient patient = (Patient) session.getAttribute("patient");
			User defaultDoctor = patientService.getDefaultDoctor(patient);
			List<Visit> patientVisit = vistService.getPatientVisit(patient
					.getHealthCard());
			model.addAttribute("defaultDoctor", defaultDoctor.getFirstName()
					+ " " + defaultDoctor.getLastName());
			model.addAttribute("patient", patient);
			model.addAttribute("patientVisit", patientVisit);
			return "patientHome";
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/patient/edit/self/{healthCard}", method = RequestMethod.GET)
	public String editPatientSelf(@PathVariable String healthCard, Model model,
			HttpSession session) {
		if (Util.isValidPatient(session)) {
			Patient patient = patientService.findByHealthCard(healthCard);
			patient.setEdit(true);
			model.addAttribute("patient", patient);

			return "patientEdit";
		}
		return "redirect:/";// go to login page
	}

	@RequestMapping(value = "/patient/edit/self", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("patient") Patient patient,
			BindingResult result, HttpSession session, Model model) {
		if (result.hasErrors()) {
			return "patientEdit";
		} else {
			patientService.update(patient);
			return patientHome(model, session);
		}
	}

}
