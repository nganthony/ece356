package com.ece356.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ece356.dao.VisitDao;
import com.ece356.domain.Visit;

@Service
public class VisitServiceImpl  implements VisitService{

	@Autowired
	VisitDao visitDao;
	
	@Override
	public Visit getVisit(String id) {
		// TODO Auto-generated method stub
		return visitDao.getVisit(id);
	}

	@Override
	public Visit createVisit(Visit visit) {
		int id = visitDao.createVisit(visit);
		return getVisit(Integer.toString(id));
	}
	
}
