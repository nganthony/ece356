package com.ece356.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CurrentHealthDao {

	@Autowired
	private DataSource dataSource;

	public Map<Integer, String> getCurrentHealths() {

		String sql = "SELECT * FROM `current_health`;";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			Map<Integer, String> currentHealth = new HashMap<Integer, String>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				currentHealth.put(rs.getInt("id"), rs.getString("status"));
			}
			rs.close();
			ps.close();
			return currentHealth;
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

	public String getCurrentHealth(int id) {
		String sql = "SELECT * FROM `current_health` WHERE id=?;";
		Connection conn = null;
		String status = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs != null && rs.next()) {
				status = rs.getString("status");
			}
			rs.close();
			ps.close();
			return status;
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
