package com.bridgelabz.toDoApp.service.serviceImplem;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.toDoApp.dao.daoInterface.UserDao;
import com.bridgelabz.toDoApp.model.User;

@Service
public class UserServiceImplem{
	
	@Autowired
	private UserDao userDaoImp;

	public boolean signUp(User user)
	{

		return userDaoImp.signUp(user);
	}

	public User signIn(String email, String password, HttpServletRequest request)
	{

		return userDaoImp.signIn(email, password, request );
	}

	public User getUserByEmail(String email) 
	{

		return userDaoImp.getUserByEmail(email);
	}

	public boolean uploadeProfile(int userid,String profile)
	{
		return userDaoImp.uploadeProfile(userid,profile);
		
	}
	public boolean resetPassword(int userid,String password) 
	{

		return userDaoImp.resetPassword(userid,password);
	}

	public boolean userEmailAuthtokenValidation(int userid, String useremailtoken) 
	{
		return userDaoImp.userEmailAuthtokenValidation(userid,useremailtoken);
		
	}

	public void userStatusSave(int userid, boolean status)
	{
		 userDaoImp.userStatusSave(userid,status);
		
	}

}
