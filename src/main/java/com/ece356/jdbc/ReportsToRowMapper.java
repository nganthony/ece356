package com.ece356.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ece356.domain.ReportsTo;
import com.ece356.domain.Role;

public class ReportsToRowMapper implements RowMapper<ReportsTo> {  

	@Override  
	public ReportsTo mapRow(ResultSet resultSet, int line) throws SQLException {  
		ReportsTo reportsTo = new ReportsTo(); 

		try {
			reportsTo.map(resultSet);
		}
		catch (Exception e) {

		}

		return reportsTo; 
	}  
} 
