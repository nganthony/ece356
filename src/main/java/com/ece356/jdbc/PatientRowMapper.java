package com.ece356.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ece356.domain.Patient;

public class PatientRowMapper implements RowMapper<Patient> {

	@Override
	public Patient mapRow(ResultSet resultSet, int line) throws SQLException {
		Patient patient = new Patient();
		try {
			patient.map(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return patient;
	}

}
