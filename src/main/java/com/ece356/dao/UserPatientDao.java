package com.ece356.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ece356.domain.Patient;
import com.ece356.jdbc.PatientRowMapper;

@Repository
public class UserPatientDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Patient> getAllPatients(int userId) {
		String sql = "SELECT * FROM user_patient" +
					" INNER JOIN patient ON user_patient.health_card = patient.health_card" +
					" WHERE user_patient.user_id = ?";
		
		List<Patient> patients = jdbcTemplate.query(sql, new PatientRowMapper());
		
		return patients;
	}
	
}
