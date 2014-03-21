package com.ece356.domain;

import java.sql.Timestamp;

import com.ece356.dao.BaseEntity;

public class Patient extends BaseEntity {

	String sin;
	String firstName;
	String lastName;
	String password;
	String healthCard;
	private int currentHealthID;
	private int defaultDoctorId;
	private Timestamp lastVisitDate;
	private boolean deleted;

	public String getSin() {
		return sin;
	}

	public void setSin(String sin) {
		this.sin = sin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHealthCard() {
		return healthCard;
	}

	public void setHealthCard(String healthCard) {
		this.healthCard = healthCard;
	}

	public int getCurrentHealthID() {
		return currentHealthID;
	}

	public void setCurrentHealthID(int currentHealthID) {
		this.currentHealthID = currentHealthID;
	}

	public int getDefaultDoctorId() {
		return defaultDoctorId;
	}

	public void setDefaultDoctorId(int defaultDoctorId) {
		this.defaultDoctorId = defaultDoctorId;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Timestamp getLastVisitDate() {
		return lastVisitDate;
	}

	public void setLastVisitDate(Timestamp lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}
}
