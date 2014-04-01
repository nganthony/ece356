package com.ece356.service;

import java.sql.Timestamp;
import java.util.List;

import com.ece356.domain.Drug;
import com.ece356.domain.Patient;
import com.ece356.domain.User;
import com.ece356.domain.Visit;

public interface VisitService {

	public Visit getVisit(int id);
	public int createVisit(Visit visit);
	public void updateVisit(Visit visit);
	public void updateForDoctor(Visit visit);
	public List<Visit> getPatientVisit(String healthCard);
	public List<Visit> getDoctorSchedule(int doctorId);
	public List<Visit> getDoctorSchedule(int doctorId, String search);
	public List<Visit> staffGetAllVisits(int staffId);
	public List<Visit> staffGetFilteredVisits(int staffId, String search);
	public List<Visit> getPatientVisit(String healthCard, int userId);
	public List<User> getCountPerDoctor(Timestamp start, Timestamp end);
	public List<Visit> getVisitForStaffInRange(Timestamp start, Timestamp end, int doctorId);
	public List<Patient> getVisitedPatients(int userId);
	public List<Patient> getVisitedPatients(int userId, String search);
	public void delete(int id);
	public boolean verifyScheduleDates(Timestamp startTimestamp,
			Timestamp endTimestamp, int id, int user_id, String health_card);
	public List<Drug> getDrugs();
	public List<Patient> getVisitedPatients(int userId, Timestamp startTimestamp, Timestamp endTimestamp); 
}
