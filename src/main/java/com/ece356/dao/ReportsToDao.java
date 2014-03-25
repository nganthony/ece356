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

import com.ece356.domain.ReportsTo;
import com.ece356.domain.Role;
import com.ece356.jdbc.ReportsToRowMapper;

@Repository
public class ReportsToDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<ReportsTo> getAllReportsTos() {
		String sql = "SELECT * FROM reports_to";

		List<ReportsTo> reportsTo = jdbcTemplate.query(sql, new ReportsToRowMapper());

		return reportsTo;
	}

	public void insert(int managedId, int managesId) {
		final String sql = "INSERT INTO reports_to (managed_id, manages_id) VALUES (?, ?)";

		jdbcTemplate.update(
				sql,
				new Object[] { managedId, managesId});
	}
	
	public List<Integer> getManagesIds(int staffId) {
		String sql = "SELECT manages_id FROM reports_to WHERE managed_id = ?";
		return jdbcTemplate.queryForList(sql, new Object[]{staffId}, Integer.class);
	}
}
