package com.ece356.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ece356.domain.Role;
import com.ece356.jdbc.RoleRowMapper;

@Repository
public class RoleDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Role> getAllRoles() {
		String sql = "SELECT * FROM role";
		
		List<Role> roles = jdbcTemplate.query(sql, new RoleRowMapper());
		
		return roles;
	}
}
