package com.ece356.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ece356.domain.Visit;

public class VisitRowMapper implements RowMapper<Visit> {

	@Override
	public Visit mapRow(ResultSet resultSet, int line) throws SQLException {
		Visit visit = new Visit();
		try {
			visit.map(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return visit;
	}

}
