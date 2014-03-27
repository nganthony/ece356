package com.ece356.service;

import java.util.List;

import com.ece356.domain.VisitAudit;

public interface VisitAuditService {
	
	public void insert(VisitAudit visitAudit);
	public List<VisitAudit> getAllAuditVisits();

}
