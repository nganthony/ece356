package com.ece356.service;

import java.util.List;

import com.ece356.domain.Visit;

public interface VisitService {

	public Visit getVisit(String id);
	public void createVisit(Visit visit);
	List<Visit> getPatientVisit(String healthCard);
	public List<Visit> getDoctorSchedule(int doctorId);
	public List<Visit> getDoctorSchedule(int doctorId, String search);
}
