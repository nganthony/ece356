package com.ece356.service;

import java.sql.Timestamp;
import java.util.List;

import com.ece356.domain.Patient;
import com.ece356.domain.User;

public interface PatientService {

	Patient findByHealthCard(String healthCard);
	void update(Patient patient);
	void delete(Patient patient);
	void insert(Patient patient);
	List<Patient> getAllPatients();
	List<Patient> getAllPatients(int defaultDoctorId);
	List<Patient> getAllPatients(int defaultDoctorId, String search);
	public String getCurrentHealth(Patient patient);
	public User getDefaultDoctor(Patient patient);
	List<Patient> getAllPatients(int defaultDoctorId, Timestamp startTimestamp, Timestamp endTimestamp);
	
}
