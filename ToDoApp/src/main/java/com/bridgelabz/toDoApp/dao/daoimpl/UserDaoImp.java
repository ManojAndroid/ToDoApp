package com.bridgelabz.toDoApp.dao.daoimpl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.toDoApp.dao.daoInterface.UserDao;
import com.bridgelabz.toDoApp.model.User;

public class UserDaoImp implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session = null;

	public boolean signUp(User user)

	{
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
			return true;

		} 
		catch (Exception exception) 
		{
			exception.printStackTrace();
			return false;
		} 
		finally 
		{
			if (session != null) 
			{
				session.close();
			}
		}
	}

	public User authUser(String email, String password) 
	{
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(User.class);
			User user = (User) criteria.add(Restrictions.conjunction().add(Restrictions.eq("email", email))
					.add(Restrictions.eq("password", password)));
			Transaction transaction = session.beginTransaction();
			transaction.commit();
			return user;
		}

		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
	}

	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
