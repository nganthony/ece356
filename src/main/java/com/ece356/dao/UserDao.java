package com.ece356.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ece356.domain.User;
import com.ece356.jdbc.UserRowMapper;

@Repository
public class UserDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	public List<User> getAllDoctors() {
		String sql = "SELECT * FROM `user` where role_id=1;";

		List<User> doctors = new ArrayList<User>();
		try {
			doctors = jdbcTemplate.query(sql, new UserRowMapper());
			return doctors;
		} catch (Exception e) {
			return doctors;
		}
	}

	/**
	 * Gets user by id
	 * 
	 * @param id
	 *            Id of user
	 * @return User with specified id
	 */
	public User getUser(String id) {
		User user = null;

		String sql = "SELECT * FROM user WHERE id = ?";

		try {
			user = jdbcTemplate.queryForObject(sql, new Object[] { id },
					new UserRowMapper());
		} catch (Exception e) {
			// No user was found with the specified id, return null
			return null;
		}

		return user;
	}
}
