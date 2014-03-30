package com.ece356.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ece356.domain.UserPatient;
import com.ece356.jdbc.UserPatientRowMapper;

@Repository
public class UserPatientDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<UserPatient> getAllPatients(int userId) {
		String sql = "SELECT * FROM user_patient" +
					" INNER JOIN patient ON user_patient.health_card = patient.health_card" +
					" INNER JOIN user ON user_patient.owner_id = user.id" +
					" WHERE user_patient.user_id = ?";
		
		List<UserPatient> userPatients = jdbcTemplate.query(sql, new Object[]{userId}, new UserPatientRowMapper());
		
		return userPatients;
	}
	
	public void insert(int ownerId, int userId, String healthCard) {
		String sql = "INSERT INTO user_patient (owner_id, user_id, health_card) VALUES (?, ?, ?)";
		
		jdbcTemplate.update(sql, new Object[]{ownerId, userId, healthCard});
	}
	
	public void delete(int ownerId, int userId, String healthCard) {
		String sql = "DELETE FROM user_patient WHERE owner_id = ? AND user_id = ? AND health_card = ?";
		
		jdbcTemplate.update(sql, new Object[]{ownerId, userId, healthCard});
	}
	
}
