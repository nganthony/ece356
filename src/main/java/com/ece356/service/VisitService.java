package com.ece356.service;

import java.util.List;

import com.ece356.domain.Patient;
import com.ece356.domain.Visit;

public interface VisitService {

	public Visit getVisit(int id);
	public void createVisit(Visit visit);
	public void updateVisit(Visit visit);
	List<Visit> getPatientVisit(String healthCard);
	public List<Visit> getDoctorSchedule(int doctorId);
	public List<Visit> getDoctorSchedule(int doctorId, String search);
	public List<Visit> staffGetAllVisits(int staffId);
}
