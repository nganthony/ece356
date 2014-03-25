package com.ece356.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ece356.domain.VisitAudit;

public class VisitAuditRowMapper implements RowMapper<VisitAudit> {

	@Override
	public VisitAudit mapRow(ResultSet resultSet, int line) throws SQLException {
		VisitAudit visitAudit = new VisitAudit();
		try {
			visitAudit.map(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return visitAudit;
	}

}
