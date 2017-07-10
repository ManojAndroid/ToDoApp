package com.bridgelabz.toDoApp.JSONResponse;

import org.springframework.stereotype.Component;

import com.bridgelabz.toDoApp.model.User;

@Component
public class UserResponse extends Response
{
	User user;

	public User getUser() 
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

}
