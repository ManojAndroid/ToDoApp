package com.bridgelabz.toDoApp.dao.daoInterface;

import javax.servlet.http.HttpServletRequest;
import com.bridgelabz.toDoApp.model.User;
public interface UserDao 
{
	public boolean signUp(User user);

	public User signIn(String email, String password,HttpServletRequest request);

	public User getUserByEmail(String email);
	public boolean	uploadeProfile( int userid, String profile);
	public boolean	resetPassword( int userid, String password);
}
