package com.ece356.service;

import com.ece356.domain.User;

public interface UserService {
	
	public User getUser(String id);
	public User createUser(User user);

}
