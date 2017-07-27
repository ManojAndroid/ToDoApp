package com.bridgelabz.toDoApp.dao.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
	public void tokenDelete(int tokenid) 
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete from Token where id = :id");
		query.setParameter("id", tokenid);
		int result = query.executeUpdate();
		transaction.commit();
	}
	public void tokenUpdate(Token token)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(token);
		transaction.commit();
		
	}
	public Token getToken(String accesstoken)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		System.out.println(accesstoken);
		
		Query query = session.createQuery(" from Token where accesstoken = :access");
		query.setParameter("access", accesstoken);
		Token token=(Token) query.uniqueResult();
		return token;
	}
	public Token getRefreshToken(String accesstoken)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(" from Token where accesstoken = :access");
		query.setParameter("access", accesstoken);
		Token token=(Token) query.uniqueResult();
		return token;
	}
}
