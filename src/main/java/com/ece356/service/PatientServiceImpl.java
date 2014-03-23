package com.ece356.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ece356.dao.PatientDao;
import com.ece356.domain.Patient;
@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientDao patientDao;

	@Override
	public Patient findByHealthCard(String healthCard) {
		return patientDao.findByHealthCard(healthCard);
	}

	@Override
	public void update(Patient patient) {
		patientDao.update(patient);

	}

	@Override
	public void delete(Patient patient) {
		patientDao.delete(patient);

	}

	@Override
	public void insert(Patient patient) {
		patientDao.insert(patient);

	}

	@Override
	public List<Patient> getAllPatients() {
		return patientDao.getAllPatients();
	}
	
	

	@Override
	public List<Patient> getAllPatients(int defaultDoctorId, String search) {
		return patientDao.getAllPatients(defaultDoctorId, search);
	}

	@Override
	public List<Patient> getAllPatients(int defaultDoctorId) {
		return patientDao.getAllPatients(defaultDoctorId);
	}

}
