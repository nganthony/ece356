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

import com.ece356.domain.Drug;
import com.ece356.domain.Patient;
import com.ece356.domain.User;
import com.ece356.domain.Visit;
import com.ece356.jdbc.DrugRowMapper;
import com.ece356.jdbc.PatientRowMapper;
import com.ece356.jdbc.VisitRowMapper;
import com.ece356.jdbc.VisitWithPatientRowMapper;

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
		final String sql = "INSERT INTO visit (diagnosis,surgery, treatment, comment, start, end , user_id, duration, health_card, drug_id ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		final Object[] params = new Object[] { visit.getDiagnosis(),
				visit.getSurgery(), visit.getTreatment(), visit.getComment(),
				visit.getStart(), visit.getEnd(), visit.getUser_id(),
				visit.getDuration(), visit.getHealth_card(), visit.getDrugId() };

		jdbcTemplate.update(sql, params);
		return jdbcTemplate.queryForObject( "select last_insert_id()", Integer.class);
	}

	public void update(Visit visit) {

		String sql = "UPDATE visit SET `diagnosis`= ?,`surgery`= ?, `treatment`= ?, `comment`= ?, start = ?, end = ?, user_id = ?, duration = ?, health_card = ? , drug_id= ? WHERE id = ? ";

		jdbcTemplate.update(
				sql,
				new Object[] { visit.getDiagnosis(), visit.getSurgery(),
						visit.getTreatment(), visit.getComment(),
						visit.getStart(), visit.getEnd(), visit.getUser_id(),
						visit.getDuration(), visit.getHealth_card(), visit.getDrugId(),
						visit.getId() });
	}
	
	public void updateForDoctors(Visit visit) {
		String sql = "UPDATE visit SET `diagnosis`= ?,`surgery`= ?, `treatment`= ?, `comment`= ?, `drug_id`= ? WHERE id = ? ";
		
		jdbcTemplate.update( sql,
				new Object[] { visit.getDiagnosis(), visit.getSurgery(),
						visit.getTreatment(), visit.getComment(),
						visit.getDrugId(),visit.getId(),  });
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

		String sql = "SELECT * FROM `visit`" +
					" INNER JOIN patient ON visit.health_card = patient.health_card" +
					" WHERE user_id = ? ORDER BY start";

		try {
			visits = jdbcTemplate.query(sql, new Object[] { id },
					new VisitWithPatientRowMapper());
			return visits;
		} catch (Exception e) {
			// No user was found with the specified id, return null
			return visits;
		}
	}
	
	public List<Visit> getDoctorSchedule(int id, String search) {
		List<Visit> visits = new ArrayList<Visit>();

		String sql = "SELECT * FROM (" +
					"SELECT visit.*, drug.name,  patient.first_name, patient.last_name,patient.sin ,patient.password, "
					+ "patient.last_visit_date,patient.default_doctor_id,patient.current_health_id,patient.deleted, "
					+ "patient.phone_number,patient.city,patient.province,patient.street,patient.postal_code, "
					+ "patient.number_of_visits FROM `visit`" +
					" INNER JOIN patient ON visit.health_card = patient.health_card" +
					" LEFT OUTER JOIN drug ON visit.drug_id = drug.id" +
					" WHERE user_id = ?) as visit" +
					" WHERE diagnosis LIKE ?" +
					" OR surgery LIKE ?" +
					" OR comment LIKE ?" +
					" OR health_card LIKE ?" +
					" OR first_name LIKE ?" +
					" OR last_name LIKE ?" +
					" OR name LIKE ?" +
					" OR treatment LIKE ?";

		visits = jdbcTemplate.query(sql, new Object[] {id, "%" + search + "%", "%" + search + "%", "%" + search + "%", search + "%", search + "%", search + "%", search + "%", "%" + search + "%"},
					new VisitWithPatientRowMapper());
		return visits;
	}
	
	public List<Visit> getDoctorSchedule(int id, Timestamp startTimestamp, Timestamp endTimestamp) {
		List<Visit> visits = new ArrayList<Visit>();

		String sql = "SELECT * FROM (" +
					"SELECT visit.*,  patient.first_name, patient.last_name,patient.sin ,patient.password, "
					+ "patient.last_visit_date,patient.default_doctor_id,patient.current_health_id,patient.deleted, "
					+ "patient.phone_number,patient.city,patient.province,patient.street,patient.postal_code, "
					+ "patient.number_of_visits FROM `visit`" +
					" INNER JOIN patient ON visit.health_card = patient.health_card" +
					" WHERE user_id = ?) as visit" +
					" WHERE visit.start >= ? AND visit.start <= ?";

		visits = jdbcTemplate.query(sql, new Object[] {id, startTimestamp, endTimestamp},
					new VisitWithPatientRowMapper());
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
			String sql = "SELECT * FROM visit WHERE user_id in ( :user_ids) ORDER BY health_card, visit.start";
			return namedParameterJdbcTemplate.query(sql, Collections.singletonMap("user_ids", doctorIds),new VisitRowMapper() );
		}
	}
	
	public List<Visit> staffGetFilteredVisits(int staffId, String search) {
		String sql = "SELECT * FROM visit INNER JOIN reports_to ON visit.user_id = reports_to.manages_id" +
						" INNER JOIN patient ON visit.health_card = patient.health_card WHERE reports_to.managed_id = ?" +
						" AND (diagnosis LIKE ?" +
						" OR surgery LIKE ?" +
						" OR comment LIKE ?" +
						" OR visit.health_card LIKE ? "
						+ " OR patient.first_name LIKE ?"
						+ " OR patient.last_name LIKE ?) ORDER BY visit.health_card, visit.start";
		
		List<Visit> visits = jdbcTemplate.query(sql, new Object[] {staffId, search + "%", search + "%", "%" + search + "%", search + "%", "%"+search + "%","%"+ search + "%"},
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
	
	public List<Visit> getPatientVisit(String healthCard, int userId) {
		List<Visit> visits = new ArrayList<Visit>();

		String sql = "SELECT * FROM `visit` WHERE health_card = ? and user_id = ?";

		try {
			visits = jdbcTemplate.query(sql, new Object[] { healthCard, userId },
					new VisitRowMapper());
			return visits;
		} catch (Exception e) {
			return visits;
		}
	}

	public List<User> getCountPerDoctor(Timestamp start, Timestamp end) {
		String sql = "SELECT  COUNT(user_id) AS COUNT, user.`id`, user.`role_id`"
				+ ",user.`first_name`, user.`last_name`,user.`password`, user.`deleted` "
				+ "FROM `visit` "
				+ "RIGHT JOIN (SELECT * FROM user WHERE role_id=1) `user` ON user.id=user_id AND visit.`start` BETWEEN ? AND ? GROUP BY user.id;";
		
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
	
	public List<Visit> getVisitForStaffInRange(Timestamp start, Timestamp end,
			int doctorId) {
		String sql = "SELECT `id`, `diagnosis`, `surgery`, `treatment`, `comment`, `drug_id` , `start`, `end`, `user_id`, `duration`, visit.`health_card`, `drug_id`, `count` "
				+ "FROM "
				+ "(SELECT * FROM visit "
				+ "WHERE user_id=? AND `start`"
				+ "BETWEEN ? AND ?) "
				+ "visit INNER JOIN "
				+ "(SELECT COUNT(health_card) AS `count`,health_card "
				+ "FROM visit WHERE user_id=? "
				+ "AND `start` BETWEEN ? AND ? GROUP BY health_card ) visit2 "
				+ "ON visit.health_card= visit2.health_card "
				+ "ORDER BY health_card;";
		Connection conn = null;
		try {			
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, doctorId);
			ps.setTimestamp(2, start);
			ps.setTimestamp(3, end);
			ps.setInt(4, doctorId);
			ps.setTimestamp(5, start);
			ps.setTimestamp(6, end);
			ResultSet rs = ps.executeQuery();
			List<Visit> visits = new ArrayList<Visit>();
			while (rs.next()) {
				Visit visit= new Visit();
				try {
					visit.map(rs);
					visit.setCount(rs.getLong("count"));
					visits.add(visit);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			rs.close();
			ps.close();
			return visits;
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
	
	public List<Patient> getVisitedPatients(int userId) {
		String sql = "SELECT patient.* FROM visit" +
					" INNER JOIN patient ON visit.health_card = patient.health_card" +
					" WHERE user_id = ?" +
					" GROUP BY patient.health_card";
		
		List<Patient> patients = jdbcTemplate.query(sql, new Object[] {userId}, new PatientRowMapper());
		
		return patients;
	}
	
	public List<Patient> getVisitedPatients(int userId, String search) {
		String sql = "SELECT * FROM (SELECT patient.* FROM visit" +
					" INNER JOIN patient ON visit.health_card = patient.health_card" +
					" WHERE user_id = ?" +
					" GROUP BY patient.health_card) as patient" +
					" WHERE first_name LIKE ?" +
					" OR last_name LIKE ?" +
					" OR sin LIKE ?" +
					" OR health_card LIKE ?";
		
		List<Patient> patients = jdbcTemplate.query(sql, new Object[] {userId, search + "%", search + "%", search + "%", search + "%"}, new PatientRowMapper());
		
		return patients;
	}
	
	public List<Patient> getVisitedPatients(int userId, Timestamp startTimestamp, Timestamp endTimestamp) {
		String sql = "SELECT * FROM (SELECT patient.* FROM visit" +
					" INNER JOIN patient ON visit.health_card = patient.health_card" +
					" WHERE user_id = ?" +
					" GROUP BY patient.health_card) as patient" +
					" WHERE (last_visit_date >= ? AND last_visit_date <= ?)";
		
		List<Patient> patients = jdbcTemplate.query(sql, new Object[] {userId, startTimestamp, endTimestamp}, new PatientRowMapper());
		
		return patients;
	}
	
	public List<Drug> getDrugs() {
		String sql = "SELECT * FROM drug;";
		List<Drug> drugs = jdbcTemplate.query(sql, new Object[] {},	new DrugRowMapper());
		return drugs;
	}
	
}
