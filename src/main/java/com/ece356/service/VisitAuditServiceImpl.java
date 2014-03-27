package com.ece356.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ece356.dao.VisitAuditDao;
import com.ece356.domain.Visit;
import com.ece356.domain.VisitAudit;

@Service
public class VisitAuditServiceImpl implements VisitAuditService {

	@Autowired
	VisitAuditDao visitAuditDao;
	
	@Override
	public void insert(VisitAudit visitAudit) {
		visitAuditDao.insert(visitAudit);
	}

	@Override
	public List<VisitAudit> getAllAuditVisits() {
		return visitAuditDao.getAllAuditVisits();
	}
	
}
