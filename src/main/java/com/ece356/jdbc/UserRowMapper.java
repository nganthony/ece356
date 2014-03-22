package com.ece356.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ece356.domain.User;

/**
 * Maps row in user database to user object
 * @author Anthony
 *
 */
public class UserRowMapper implements RowMapper<User> {  

	@Override  
	public User mapRow(ResultSet resultSet, int line) throws SQLException {  
		User user = new User();  

		try {
			user.map(resultSet);
		}
		catch (Exception e) {

		}

		return user; 
	}  
}  