package com.ece356.domain;


public class User extends BaseEntity {
	private String firstName, lastName, password;
	private int roleId, id;
	private boolean deleted;

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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int role_id) {
		this.roleId = role_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int user_id) {
		this.id = user_id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
