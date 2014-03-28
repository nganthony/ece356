package com.ece356.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ece356.dao.RoleDao;
import com.ece356.dao.UserDao;
import com.ece356.domain.Role;
import com.ece356.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao;
	
	@Override
	public User getUser(String id) {
		return userDao.getUser(id);
	}

	@Override
	public User createUser(User user) {
		int id = userDao.createUser(user);
		return getUser(Integer.toString(id));
	}

	
	public Role getRole(User user){
		return roleDao.getRole(user.getRoleId());
	}

	@Override
	public List<User> getAllDoctors() {
		return userDao.getAllDoctors();
	}

	@Override
	public List<User> getDoctorsWithoutPermission(int ownerId, String healthCard) {
		return userDao.getDoctorsWithoutPermission(ownerId, healthCard);
	}

	@Override
	public List<User> getDoctorsWithPermission(int ownerId, String healthCard) {
		return userDao.getDoctorsWithPermission(ownerId, healthCard);
	}
}
