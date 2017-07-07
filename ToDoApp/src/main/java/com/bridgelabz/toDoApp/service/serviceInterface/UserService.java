package com.bridgelabz.toDoApp.service.serviceInterface;

import com.bridgelabz.toDoApp.model.User;

public interface UserService 
{
	public boolean signUp(User user);

	public boolean signIn(String email, String password);

	public User getUserByEmail(String email);

}
