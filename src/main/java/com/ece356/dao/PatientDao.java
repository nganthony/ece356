package com.ece356.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ece356.domain.Patient;
import com.ece356.jdbc.PatientRowMapper;

@Repository
public class PatientDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insert(Patient patient) {

		String sql = "INSERT INTO patient "
				+ "(`sin`,`first_name`, `last_name`, `password`, last_visit_date, health_card , default_doctor_id, current_health_id, deleted ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(
				sql,
				new Object[] { patient.getSin(), patient.getFirstName(),
						patient.getLastName(), patient.getPassword(),
						patient.getLastVisitDate(), patient.getHealthCard(),
						patient.getDefaultDoctorId(),
						patient.getCurrentHealthID(), patient.isDeleted() });

	}

	public Patient findByHealthCard(String healthCard) {
		String sql = "SELECT * FROM patient WHERE health_card = ?";
		Patient patient;
		try {
			patient = jdbcTemplate.queryForObject(sql,
					new Object[] { healthCard }, new PatientRowMapper());
			return patient;
		} catch (Exception e) {
			// No user was found with the specified id, return null
			return null;
		}

	}

	/**
	 * This is only for testing
	 */
	@Deprecated
	public List<Patient> getAllPatients() {
		String sql = "SELECT * FROM patient";
		List<Patient> patients = new ArrayList<Patient>();
		try {
			patients = jdbcTemplate.query(sql, new PatientRowMapper());
			return patients;
		} catch (Exception e) {
			// No user was found with the specified id, return null
			return patients;
		}
	}

	public void update(Patient patient) {
		String sql = "UPDATE patient SET `sin`= ?,`first_name`= ?, `last_name`= ?, `password`= ?, default_doctor_id = ?, current_health_id = ?, deleted = ? WHERE health_card = ? ";
		jdbcTemplate.update(sql, patient.getSin(), patient.getFirstName(),
				patient.getLastName(), patient.getPassword(),
				patient.getDefaultDoctorId(), patient.getCurrentHealthID(),
				patient.isDeleted(), patient.getHealthCard());
	}

	public void delete(Patient patient) {
		String sql = "UPDATE patient SET deleted=? Where health_card=? ";
		jdbcTemplate.update(sql, true, patient.getHealthCard());
	}

}
