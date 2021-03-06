package com.ece356.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
	
	/**
	 * Creates a user
	 * @param int Id of user created
	 */
	public int createUser(User user) {
		final String sql = "INSERT INTO user (first_name, last_name, password, role_id) VALUES (?, ?, ?, ?)";
		
		final Object[] params = new Object[] {user.getFirstName(), user.getLastName(), user.getPassword(), user.getRoleId()}; 
		
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(
				new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement statement = connection.prepareStatement(sql, new String[] {"id"});
						int index = 1;
						for(Object object: params) {
							statement.setObject(index, object);
							index++;
						}
						return statement;
					}
				},
				keyHolder);

		return keyHolder.getKey().intValue();	
	}
	
	public List<User> getDoctorsWithoutPermission(int ownerId, String healthCard) {
		String sql = "SELECT * FROM user WHERE id NOT IN "+ 
					" (SELECT user_id FROM user_patient WHERE owner_id = ? AND health_card = ?)" +
					" AND role_id = 1" +
					" AND id != ?";
		
		List<User> doctors = jdbcTemplate.query(sql, new Object[]{ownerId, healthCard, ownerId}, new UserRowMapper());
		return doctors;
	}
	
	public List<User> getDoctorsWithPermission(int ownerId, String healthCard) {
		String sql = "SELECT user.* FROM user_patient" +
					" INNER JOIN user ON user.id = user_patient.user_id" +
					" WHERE owner_id = ?" +
					" AND health_card = ?";
		
		List<User> doctors = jdbcTemplate.query(sql, new Object[]{ownerId, healthCard}, new UserRowMapper());
		return doctors;
	}
}
