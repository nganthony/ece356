package com.ece356.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ece356.domain.Patient;
import com.ece356.domain.User;
import com.ece356.domain.UserPatient;

public class UserPatientRowMapper implements RowMapper<UserPatient> {  

	@Override  
	public UserPatient mapRow(ResultSet resultSet, int line) throws SQLException {  
		UserPatient userPatient = new UserPatient();
		userPatient.setUser(new User());
		userPatient.setPatient(new Patient()); 

		try {
			userPatient.getUser().map(resultSet);
			userPatient.getPatient().map(resultSet);
		}
		catch (Exception e) {

		}

		return userPatient; 
	}  
} 
