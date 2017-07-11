package com.bridgelabz.toDoApp.service.serviceInterface;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.toDoApp.model.User;

public interface UserService 
{
	public boolean signUp(User user);

	public User signIn(String email, String password,HttpServletRequest request);

	public User getUserByEmail(String email);

}
