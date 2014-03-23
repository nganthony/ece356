package com.ece356.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ece356.dao.UserDao;
import com.ece356.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public User getUser(String id) {
		return userDao.getUser(id);
	}

	@Override
	public User createUser(User user) {
		int id = userDao.createUser(user);
		return getUser(Integer.toString(id));
	}

}
