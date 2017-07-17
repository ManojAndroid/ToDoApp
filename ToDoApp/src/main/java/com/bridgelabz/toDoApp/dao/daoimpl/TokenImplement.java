package com.bridgelabz.toDoApp.dao.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.toDoApp.dao.daoInterface.TokenInterface;
import com.bridgelabz.toDoApp.model.Token;
@Component
public class TokenImplement implements TokenInterface
{
	@Autowired
	private SessionFactory sessionFactory;
	public void tokenSave(Token token)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(token);
		transaction.commit();
	}
}
