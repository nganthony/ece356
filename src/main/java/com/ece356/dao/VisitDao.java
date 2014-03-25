package com.ece356.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ece356.domain.User;
import com.ece356.domain.Visit;
import com.ece356.jdbc.VisitRowMapper;

@Repository
public class VisitDao {

	@Autowired
	ReportsToDao reportsToDao;
	
	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public int createVisit(Visit visit) {
		final String sql = "INSERT INTO visit (diagnosis,surgery, treatment, comment, start, end , user_id, duration, health_card ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		final Object[] params = new Object[] { visit.getDiagnosis(),
				visit.getSurgery(), visit.getTreatment(), visit.getComment(),
				visit.getStart(), visit.getEnd(), visit.getUser_id(),
				visit.getDuration(), visit.getHealth_card() };

		jdbcTemplate.update(sql, params);
		return jdbcTemplate.queryForObject( "select last_insert_id()", Integer.class);
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

		String sql = "SELECT * FROM `visit` WHERE user_id = ? ORDER BY start";

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
	
	public List<Visit> staffGetAllVisits(int staffId) {
		List<Integer> doctorIds = reportsToDao.getManagesIds(staffId);
		if (doctorIds.size() == 0) {
			return null;
		} else {
			String sql = "SELECT * FROM visit WHERE user_id in ( :user_ids) ORDER BY health_card";
			return namedParameterJdbcTemplate.query(sql, Collections.singletonMap("user_ids", doctorIds),new VisitRowMapper() );
		}
	}
	
	public List<Visit> staffGetFilteredVisits(int staffId, String search) {
		String sql = "SELECT * FROM visit INNER JOIN reports_to ON visit.user_id = reports_to.manages_id WHERE reports_to.managed_id = ?" +
						" AND (diagnosis LIKE ?" +
						" OR surgery LIKE ?" +
						" OR comment LIKE ?" +
						" OR health_card LIKE ? )";
		
		List<Visit> visits = jdbcTemplate.query(sql, new Object[] {staffId, search + "%", search + "%", "%" + search + "%", search + "%"},
				new VisitRowMapper());
		return visits;
		
	}
	
	public boolean verifyScheduleDates(Timestamp start, Timestamp end, int visitId, int doctorId, String healthCard) {
		String sql = "SELECT * FROM visit WHERE (? > start AND ? < end AND id != ?) AND (user_id = ? OR health_card = ?)";
		List<Visit> visits = jdbcTemplate.query(sql, new Object[]{end, start, visitId, doctorId, healthCard}, new VisitRowMapper());
		if (visits.size() == 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public List<User> getCountPerDoctor(Timestamp start, Timestamp end) {
		String sql = "SELECT `user_id`, COUNT(user_id) AS count,user.`id`, user.`role_id`,"
				+ " user.`first_name`, user.`last_name`, user.`password`, user.`deleted` "
				+ "FROM `visit` "
				+ "INNER JOIN user ON user.id=user_id AND visit.`start` BETWEEN ? AND ? GROUP BY user_id;";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setTimestamp(1, start);
			ps.setTimestamp(2, end);
			ResultSet rs = ps.executeQuery();
			List<User> doctors = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();
				try {
					user.map(rs);
					user.setCount(rs.getLong("count"));
					doctors.add(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
}
