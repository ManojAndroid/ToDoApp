package com.bridgelabz.toDoApp.dao.daoimpl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.toDoApp.dao.daoInterface.UserDao;
import com.bridgelabz.toDoApp.model.User;

@Repository
public class UserDaoImp implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public boolean signUp(User user)

	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			session.saveOrUpdate(user);
			transaction.commit();
			return true;

		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}

	}

	public User signIn(String email, String password) {

			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.conjunction().add(Restrictions.eq("email", email)).add(Restrictions.eq("password", password)));
			return (User) criteria.uniqueResult();
	}

	public User getUserByEmail(String email)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
