package com.ece356.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	@Autowired
	private DataSource dataSource;

	public List<User> getAllDoctors() {

		String sql = "SELECT * FROM `user` where role_id=1;";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			List<User> doctors = new ArrayList<User>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				try {
					user.map(rs);
				} catch (Exception e) {
					e.printStackTrace();
				}
				doctors.add(user);
			}
			rs.close();
			ps.close();
			return doctors;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	/**
	 * Gets user by id
	 * @param id Id of user
	 * @return User with specified id
	 */
	public User getUser(String id) {
		User user = null;

		String sql = "SELECT * FROM user WHERE id = ?";

		try {
			user = jdbcTemplate.queryForObject(sql, new Object[] {id}, new UserRowMapper());
		}
		catch (Exception e) {
			// No user was found with the specified id, return null
			return null;
		}
		
		return user;
	}
}
