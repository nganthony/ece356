package com.ece356.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ece356.domain.Visit;
import com.ece356.jdbc.VisitRowMapper;

@Repository
public class VisitDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void createVisit(Visit visit) {
		final String sql = "INSERT INTO visit (diagnosis,surgery, treatment, comment, start, end , user_id, duration, health_card ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		final Object[] params = new Object[] { visit.getDiagnosis(),
				visit.getSurgery(), visit.getTreatment(), visit.getComment(),
				visit.getStart(), visit.getEnd(), visit.getUser_id(),
				visit.getDuration(), visit.getHealth_card() };

		jdbcTemplate.update(sql, params);
	}

	public void update(Visit visit) {

		String sql = "UPDATE visit SET `diagnosis`= ?,`surgery`= ?, `treatment`= ?, `comment`= ?, start = ?, end = ?, user_id = ?, duration = ?, health_card = ? WHERE id = ? ";

		jdbcTemplate.update(
				sql,
				new Object[] { visit.getDiagnosis(), visit.getSurgery(),
						visit.getTreatment(), visit.getComment(),
						visit.getStart(), visit.getEnd(), visit.getUser_id(),
						visit.getDuration(), visit.getHealth_card(), visit.getId() });

	}
	
	public void updateForDoctors(Visit visit) {
		String sql = "UPDATE visit SET `diagnosis`= ?,`surgery`= ?, `treatment`= ?, `comment`= ? WHERE id = ? ";
		
		jdbcTemplate.update(
				sql,
				new Object[] { visit.getDiagnosis(), visit.getSurgery(),
						visit.getTreatment(), visit.getComment(), visit.getId() });
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
			visits = jdbcTemplate.query(sql, new Object[] { id },
					new VisitRowMapper());
			return visits;
		} catch (Exception e) {
			// No user was found with the specified id, return null
			return visits;
		}
	}
	
	public List<Visit> getDoctorSchedule(int id, String search) {
		List<Visit> visits = new ArrayList<Visit>();

		String sql = "SELECT * FROM (" +
					"SELECT * FROM `visit` WHERE user_id = ?) as visit" +
					" WHERE diagnosis LIKE ?" +
					" OR surgery LIKE ?" +
					" OR comment LIKE ?" +
					" OR health_card LIKE ?";

		visits = jdbcTemplate.query(sql, new Object[] {id, search + "%", search + "%", "%" + search + "%", search + "%"},
					new VisitRowMapper());
		return visits;
	}

	public Visit getVisit(int id) {
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

	public List<Visit> getPatientVisit(String healthCard) {
		List<Visit> visits = new ArrayList<Visit>();

		String sql = "SELECT * FROM `visit` WHERE health_card = ?";

		try {
			visits = jdbcTemplate.query(sql, new Object[] { healthCard },
					new VisitRowMapper());
			return visits;
		} catch (Exception e) {
			return visits;
		}
	}
	
	public void delete(int id) {
		String sql = "DELETE FROM visit WHERE id = ?";
		try {
			jdbcTemplate.update(sql, new Object[] {id} );
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
