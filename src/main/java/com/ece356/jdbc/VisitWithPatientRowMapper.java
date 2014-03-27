package com.ece356.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ece356.domain.Patient;
import com.ece356.domain.Visit;

public class VisitWithPatientRowMapper implements RowMapper<Visit> {

	@Override
	public Visit mapRow(ResultSet resultSet, int line) throws SQLException {
		Visit visit = new Visit();
		try {
			visit.map(resultSet);
			visit.setPatient(new Patient());
			visit.getPatient().map(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return visit;
	}

}
