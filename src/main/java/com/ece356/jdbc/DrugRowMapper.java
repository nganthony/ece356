package com.ece356.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ece356.domain.Drug;

public class DrugRowMapper implements RowMapper<Drug> {

	@Override
	public Drug mapRow(ResultSet resultSet, int line) throws SQLException {
		Drug drug = new Drug();
		try {
			drug.map(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return drug;
	}

}
