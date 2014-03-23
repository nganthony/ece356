package com.ece356.controller;

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
import com.ece356.domain.Patient;
import com.ece356.domain.User;

@Controller
@RequestMapping()
public class PatientController {

	@Autowired
	UserDao userDao;
	@Autowired
	CurrentHealthDao currentHealthDao;
	@Autowired
	PatientDao patientDao;

	@RequestMapping(value = "/patientCreate", method = RequestMethod.GET)
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
		return "patientCreate";

	}

	@RequestMapping(value = "/patientCreate", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("patient") Patient patient,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return getCreatePage(model, patient);
		} else {
			Date now = new Date();
			patient.setLastVisitDate(new Timestamp(now.getTime()));
			Patient existingPatient = patientDao.findByHealthCard(patient
					.getHealthCard());
			if (existingPatient == null) {
				patientDao.insert(patient);
				return ("welcome");
			} else {
				result.rejectValue("healthCard", "error.patient",
						"This Patient already exists");
				return getCreatePage(model, patient);
			}

		}
	}

	@RequestMapping(value = "/patientView", method = RequestMethod.GET)
	public String getView(Model model) {
		List<Patient> patients = patientDao.getAllPatients();
		model.addAttribute("patients", patients);
		return "patientView";
	}

	@RequestMapping(value = "/patientEdit/{healthCard}")
	public String editPatient(@PathVariable String healthCard, Model model) {
		Patient patient = patientDao.findByHealthCard(healthCard);

		return getCreatePage(model, patient);
	}

}
