package com.bridgelabz.toDoApp.dao.daoInterface;

import com.bridgelabz.toDoApp.model.User;

public interface UserDao 
{
	public boolean signUp(User user);

	public User authUser(String email, String password);

	public User getUserByEmail(String email);
}
