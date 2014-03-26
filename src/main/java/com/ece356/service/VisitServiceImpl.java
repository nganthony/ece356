package com.ece356.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ece356.dao.VisitDao;
import com.ece356.domain.Patient;
import com.ece356.domain.User;
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
	public int createVisit(Visit visit) {
		return visitDao.createVisit(visit);
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
	public void updateForDoctor(Visit visit) {
		visitDao.updateForDoctors(visit);
	}

	@Override
	public List<Visit> staffGetAllVisits(int staffId) {
		return visitDao.staffGetAllVisits(staffId);
	}

	@Override
	public List<Visit> staffGetFilteredVisits(int staffId, String search) {
		return visitDao.staffGetFilteredVisits(staffId, search);
	}

	@Override
	public List<Visit> getPatientVisit(String healthCard, int userId) {
		return visitDao.getPatientVisit(healthCard, userId);
	}
	
	@Override
	public List<User> getCountPerDoctor(Timestamp start, Timestamp end) {
		return visitDao.getCountPerDoctor(start, end);

	}
}
