package com.ece356.controller;

import javax.servlet.http.HttpSession;

import com.ece356.domain.Patient;
import com.ece356.domain.User;

public class Util {

	public static boolean isValidFinaceUser(HttpSession session) {
		String role = (String) session.getAttribute("role");
		if (role != null && "finance".equals(role.toLowerCase())) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidPatient(HttpSession session) {
		String role = (String) session.getAttribute("role");
		if (role != null && "patient".equals(role.toLowerCase())) {
			Patient patient = (Patient) session.getAttribute("patient");
			if (patient != null) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidDoctor(HttpSession session) {
		String role = (String) session.getAttribute("role");
		if (role != null && "doctor".equals(role.toLowerCase())) {
			User patient = (User) session.getAttribute("user");
			if (patient != null) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidStaff(HttpSession session) {
		String role = (String) session.getAttribute("role");
		if (role != null && "staff".equals(role.toLowerCase())) {
			User patient = (User) session.getAttribute("user");
			if (patient != null) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidLegal(HttpSession session) {
		String role = (String) session.getAttribute("role");
		if (role != null && "legal".equals(role.toLowerCase())) {
			User patient = (User) session.getAttribute("user");
			if (patient != null) {
				return true;
			}
		}
		return false;
	}

}
