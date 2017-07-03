package com.bridgelabz.toDoApp.service.serviceImplem;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.toDoApp.dao.daoInterface.UserDao;
import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceInterface.UserService;

public class UserServiceImplem implements UserService {
	@Autowired
	private UserDao userDao;

	public boolean signUp(User user)
	{

		return userDao.signUp(user);
	}

	public User authUser(String email, String password)
	{

		return userDao.authUser(email, password);
	}

	public User getUserByEmail(String email) 
	{

		return userDao.getUserByEmail(email);
	}

}
