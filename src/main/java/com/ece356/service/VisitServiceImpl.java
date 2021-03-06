package com.ece356.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ece356.dao.VisitDao;
import com.ece356.domain.Drug;
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

	@Override
	public List<Visit> getVisitForStaffInRange(Timestamp start, Timestamp end,
			int doctorId) {
		return visitDao.getVisitForStaffInRange(start, end, doctorId);
	}

	@Override
	public List<Patient> getVisitedPatients(int userId) {
		return visitDao.getVisitedPatients(userId);
	}
	
	@Override
	public void delete(int id) {
		visitDao.delete(id);
	}

	@Override
	public boolean verifyScheduleDates(Timestamp startTimestamp,
			Timestamp endTimestamp, int id, int user_id, String health_card) {
		// TODO Auto-generated method stub
		return visitDao.verifyScheduleDates(startTimestamp, endTimestamp, id, user_id, health_card);
	}

	@Override
	public List<Patient> getVisitedPatients(int userId, String search) {
		return visitDao.getVisitedPatients(userId, search);
	}

	@Override
	public List<Drug> getDrugs() {
		return visitDao.getDrugs();
	}

	@Override
	public List<Patient> getVisitedPatients(int userId,
			Timestamp startTimestamp, Timestamp endTimestamp) {
		return visitDao.getVisitedPatients(userId, startTimestamp, endTimestamp);
	}

	@Override
	public List<Visit> getDoctorSchedule(int doctorId,
			Timestamp startTimestamp, Timestamp endTimestamp) {
		return visitDao.getDoctorSchedule(doctorId, startTimestamp, endTimestamp);
	}
}
