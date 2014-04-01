package com.ece356.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ece356.dao.CurrentHealthDao;
import com.ece356.dao.PatientDao;
import com.ece356.dao.UserDao;
import com.ece356.domain.Patient;
import com.ece356.domain.User;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientDao patientDao;
	@Autowired
	CurrentHealthDao currentHealthDao;
	@Autowired
	UserDao userDao;

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

	@Override
	public String getCurrentHealth(Patient patient) {
		return currentHealthDao.getCurrentHealth(patient.getCurrentHealthID());
	}

	@Override
	public User getDefaultDoctor(Patient patient) {
		return userDao.getUser(""+patient.getDefaultDoctorId());
	}

	@Override
	public List<Patient> getAllPatients(int defaultDoctorId,
			Timestamp startTimestamp, Timestamp endTimestamp) {
		return patientDao.getAllPatients(defaultDoctorId, startTimestamp, endTimestamp);
	}

}
