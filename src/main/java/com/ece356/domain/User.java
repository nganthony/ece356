package com.ece356.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User extends BaseEntity {

	@NotNull(message = "Must enter a value")
	@Size(max = 30, min = 1, message = "Must be between 1 and 30 characters")
	private String firstName;

	@NotNull(message = "Must enter a value")
	@Size(max = 30, min = 1, message = "Must be between 1 and 30 characters")
	private String lastName;

	@NotNull
	@Size(min = 6, max = 255, message = "Must be at least 6 characters")
	private String password;

	private int roleId, id;

	private boolean deleted;
	private transient long count;

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

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
