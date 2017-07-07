package com.bridgelabz.toDoApp.dao.daoInterface;

import com.bridgelabz.toDoApp.model.User;

public interface UserDao 
{
	public boolean signUp(User user);

	public boolean signIn(String email, String password);

	public User getUserByEmail(String email);
}
