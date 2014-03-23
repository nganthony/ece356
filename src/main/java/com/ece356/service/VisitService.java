package com.ece356.service;

import com.ece356.domain.Visit;

public interface VisitService {

	public Visit getVisit(String id);
	public Visit createVisit(Visit visit);
	
}
