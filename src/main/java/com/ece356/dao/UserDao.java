package com.ece356.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ece356.domain.User;

@Repository
public class UserDao {

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
				user.setRole_id(1);
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setPassword(rs.getString("password"));
				user.setDeleted(rs.getBoolean("deleted"));
				user.setUser_id(rs.getInt("id"));
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

}
