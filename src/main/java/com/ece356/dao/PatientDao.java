package com.ece356.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ece356.domain.Patient;
@Repository
public class PatientDao {

	@Autowired
	private DataSource dataSource;

	public void insert(Patient patient) {

		String sql = "INSERT INTO patient "
				+ "(`sin`,`first_name`, `last_name`, `password`, last_visit_date, health_card , defualt_doctor_id, current_health_id, deleted ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, patient.getSin());
			ps.setString(2, patient.getFirstName());
			ps.setString(3, patient.getLastName());
			ps.setString(4, patient.getPassword());
			ps.setTimestamp(5, patient.getLastVisitDate());
			ps.setString(6, patient.getHealthCard());
			ps.setInt(7, patient.getDefaultDoctorId());
			ps.setInt(8, patient.getCurrentHealthID());
			ps.setBoolean(9, patient.isDeleted());
			ps.executeUpdate();
			ps.close();

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

	public Patient findByHealthCard(String healthCard) {

		String sql = "SELECT * FROM patient WHERE health_card = ?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, healthCard);
			Patient patient = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				patient = new Patient();
				patient.setHealthCard(healthCard);
				patient.setFirstName(rs.getString("first_name"));
				patient.setLastName(rs.getString("last_name"));
				patient.setPassword(rs.getString("password"));
				patient.setSin(rs.getString("sin"));
				patient.setLastVisitDate(rs.getTimestamp("last_visit_date"));
				patient.setDefaultDoctorId(rs.getInt("defualt_doctor_id"));
				patient.setCurrentHealthID(rs.getInt("current_health_id"));
				patient.setDeleted(rs.getBoolean("deleted"));

			}
			rs.close();
			ps.close();
			return patient;
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
