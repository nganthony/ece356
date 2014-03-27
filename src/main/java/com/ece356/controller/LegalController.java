package com.ece356.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ece356.domain.VisitAudit;
import com.ece356.service.VisitAuditService;

@Controller
@RequestMapping("legal")
public class LegalController {

	@Autowired
	VisitAuditService visitAuditService;
	
	@RequestMapping(value = "{legalId}/view", method= RequestMethod.GET)
	public String showAppointmentsPage(@PathVariable("legalId") int legalId, Model model) {
		
		List<VisitAudit> visitAudits  = visitAuditService.getAllAuditVisits();
		model.addAttribute("visitAudits", visitAudits);
		return "legalView";
	}
}
