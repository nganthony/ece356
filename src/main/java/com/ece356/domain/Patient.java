package com.ece356.domain;

public class Patient {

	String sin;
	String firstName;
	String lastName;
	String password;
	String healthCard;
	
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
}
