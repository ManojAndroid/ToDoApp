package com.bridgelabz.toDoApp.service.serviceImplem;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.toDoApp.dao.daoInterface.UserDao;
import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceInterface.UserService;


public class UserServiceImplem implements UserService {
	
	@Autowired
	private UserDao userDaoImp;

	public boolean signUp(User user)
	{

		return userDaoImp.signUp(user);
	}

	public User signIn(String email, String password)
	{

		return userDaoImp.signIn(email, password);
	}

	public User getUserByEmail(String email) 
	{

		return userDaoImp.getUserByEmail(email);
	}

}
