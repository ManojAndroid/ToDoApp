package com.bridgelabz.toDoApp.dao.daoInterface;

import com.bridgelabz.toDoApp.model.Token;

public interface TokenInterface 
{
	public void tokenSave(Token token);
	public void tokenDelete(int id);
	public void tokenUpdate(Token token);
	public Token getToken(String accesstoken);
}
