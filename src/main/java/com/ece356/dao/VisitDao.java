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

import com.ece356.domain.Patient;
import com.ece356.domain.User;
import com.ece356.domain.Visit;
import com.ece356.jdbc.UserRowMapper;
import com.ece356.jdbc.VisitRowMapper;

@Repository
public class VisitDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void insert(Visit visit) {

		String sql = "INSERT INTO visit "
				+ "(`diagnosis`,`surgery`, `treatment`, `comment`, start, end , user_id, duration, health_card ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(
				sql,
				new Object[] { visit.getDiagnosis(), visit.getSurgery(),
						visit.getTreatment(), visit.getComment(),
						visit.getStart(), visit.getEnd(),
						visit.getUser_id(),
						visit.getDuration(), visit.getHealth_card() });

	}
	
	public void createVisit(Visit visit) {
		final String sql = "INSERT INTO visit (diagnosis,surgery, treatment, comment, start, end , user_id, duration, health_card ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		final Object[] params = new Object[] { visit.getDiagnosis(), visit.getSurgery(),
				visit.getTreatment(), visit.getComment(),
				visit.getStart(), visit.getEnd(),
				visit.getUser_id(),
				visit.getDuration(), visit.getHealth_card() };
		
		jdbcTemplate.update(sql, params);	
	}
	
	public void update(Visit visit) {

		String sql ="UPDATE visit SET `diagnosis`= ?,`surgery`= ?, `treatment`= ?, `comment`= ?, start = ?, end = ?, user_id = ?, duration = ?, health_card = ? WHERE health_card = ? ";

		jdbcTemplate.update(
				sql,
				new Object[] { visit.getDiagnosis(), visit.getSurgery(),
						visit.getTreatment(), visit.getComment(),
						visit.getStart(), visit.getEnd(),
						visit.getUser_id(),
						visit.getDuration(), visit.getHealth_card() });

	}
	
	
	public List<Visit> getAllVisits() {
		String sql = "SELECT * FROM `visit`";

		List<Visit> visits = new ArrayList<Visit>();
		try {
			visits = jdbcTemplate.query(sql, new VisitRowMapper());
			return visits;
		} catch (Exception e) {
			return visits;
		}
	}

	public List<Visit> getDoctorSchedule(int id) {
		List<Visit> visits = new ArrayList<Visit>();

		String sql = "SELECT * FROM `visit` WHERE user_id = ?";

		try {
			visits = jdbcTemplate.query(sql, new Object[]{id}, new VisitRowMapper());
			return visits;
		} catch (Exception e) {
			// No user was found with the specified id, return null
			return visits;
		}
	}
	
	public Visit getVisit(String id) {
		Visit visit = null;

		String sql = "SELECT * FROM visit WHERE id = ?";

		try {
			visit = jdbcTemplate.queryForObject(sql, new Object[] { id },
					new VisitRowMapper());
		} catch (Exception e) {
			// No user was found with the specified id, return null
			return null;
		}

		return visit;
	}

}
