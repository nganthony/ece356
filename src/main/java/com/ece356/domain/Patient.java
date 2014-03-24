package com.ece356.domain;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Patient extends BaseEntity {
	@Size(max = 9, message = "Must be smaller than 9 characters")
	String sin;
	@NotNull(message = "Must enter a value")
	@Size(max = 30, min = 1, message = "Must be between 1 and 30 characters")
	String firstName;
	@NotNull
	@Size(max = 30, min = 1, message = "Must be between 1 and 30 characters")
	String lastName;
	@NotNull
	@Size(min = 6, max=255, message = "must be at least 6 characters")
	String password;
	@NotNull
	@Size(min = 1, max=12, message = "must be between 1 and 12 characters")
	String healthCard;
	@NotNull
	String phoneNumber;
	@NotNull
	String street;
	@NotNull
	String city;
	@NotNull
	String province;
	@NotNull
	String postalCode;
	private int currentHealthID;
	private int defaultDoctorId;
	private Timestamp lastVisitDate;
	private boolean deleted;
	private transient boolean edit;

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

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getProvince() {
		return province;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getPostalCode() {
		return this.postalCode;
	}
}
