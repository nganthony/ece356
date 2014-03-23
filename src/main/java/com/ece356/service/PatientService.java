package com.ece356.service;

import java.util.List;

import com.ece356.domain.Patient;

public interface PatientService {

	Patient findByHealthCard(String healthCard);
	void update(Patient patient);
	void delete(Patient patient);
	void insert(Patient patient);
	List<Patient> getAllPatients();
	List<Patient> getAllPatients(int defaultDoctorId);
	List<Patient> getAllPatients(int defaultDoctorId, String search);
	
}
