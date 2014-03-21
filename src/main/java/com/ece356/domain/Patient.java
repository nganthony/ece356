package com.ece356.domain;

import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ece356.dao.BaseEntity;

public class Patient extends BaseEntity {
	@Max(9)
	String sin;
	@NotNull(message="Must enter a value")
	@Max(30)
	String firstName;
	@NotNull
	@Max(30)
	String lastName;
	@NotNull
	@Size(min=6, message="must be at least 6 characters")
	String password;
	@NotNull
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
