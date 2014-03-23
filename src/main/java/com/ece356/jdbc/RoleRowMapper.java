package com.ece356.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ece356.domain.Role;

/**
 * Maps row in user database to user object
 * @author Anthony
 *
 */
public class RoleRowMapper implements RowMapper<Role> {  

	@Override  
	public Role mapRow(ResultSet resultSet, int line) throws SQLException {  
		Role role = new Role(); 

		try {
			role.map(resultSet);
		}
		catch (Exception e) {

		}

		return role; 
	}  
} 
