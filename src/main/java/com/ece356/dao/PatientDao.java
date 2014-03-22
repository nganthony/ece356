package com.ece356.dao;

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
				+ "(`sin`,`first_name`, `last_name`, `password`, last_visit_date, health_card , defualt_doctor_id, current_health_id, deleted ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

}
