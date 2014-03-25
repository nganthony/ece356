package com.ece356.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ece356.dao.VisitDao;
import com.ece356.domain.Patient;
import com.ece356.domain.Visit;

@Service
public class VisitServiceImpl implements VisitService {

	@Autowired
	VisitDao visitDao;

	@Override
	public Visit getVisit(int id) {
		// TODO Auto-generated method stub
		return visitDao.getVisit(id);
	}

	@Override
	public void createVisit(Visit visit) {
		visitDao.createVisit(visit);
	}

	@Override
	public List<Visit> getPatientVisit(String healthCard) {
		return visitDao.getPatientVisit(healthCard);
	}
	
	@Override
	public List<Visit> getDoctorSchedule(int doctorId) {
		return visitDao.getDoctorSchedule(doctorId);
	}

	@Override
	public List<Visit> getDoctorSchedule(int doctorId, String search) {
		return visitDao.getDoctorSchedule(doctorId, search);
	}

	public void updateVisit(Visit visit) {
		// TODO Auto-generated method stub
		visitDao.update(visit);
	}
	@Override
	public List<Visit> staffGetAllVisits(int staffId) {
		return visitDao.staffGetAllVisits(staffId);
	}
}
