package com.bridgelabz.toDoApp.dao.daoimpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.toDoApp.dao.daoInterface.UserDao;
import com.bridgelabz.toDoApp.model.User;

/**
 * @author bridgeit
 *
 */
@Repository
public class UserDaoImp implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.bridgelabz.toDoApp.dao.daoInterface.UserDao#signUp(com.bridgelabz.toDoApp.model.User)
	 */
	public boolean signUp(User user)

	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			session.save(user);
			transaction.commit();
			return true;

		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}

	}

	public User signIn(String email, String password, HttpServletRequest request) {

		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.conjunction().add(Restrictions.eq("email", email))
				.add(Restrictions.eq("password", password)));
		User signinresult = (User) criteria.uniqueResult();
		
		if (signinresult != null) {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("UserSession", signinresult);
			//System.out.println("session");
			return signinresult;
		}
		return null;
	}

	public User getUserByEmail(String email) 
	{

		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		User userresult = (User) criteria.uniqueResult();
	    return userresult;
	
	}

	@SuppressWarnings("deprecation")
	public boolean uploadeProfile(int userid,String profile) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql=null;
		try {
			 hql = "update User set profile = :profile " + " where id = :userid";
		
		Query query = session.createQuery(hql);
		query.setParameter("profile", profile);
		query.setParameter("userid", userid);
		int result = query.executeUpdate();
		transaction.commit();
			return true;

		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}
	
	
	public boolean resetPassword(int userid,String password) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql=null;
		try {
			 hql = "update User set password = :password " + " where id = :userid";
		
		Query query = session.createQuery(hql);
		query.setParameter("password", password);
		query.setParameter("userid", userid);
		int result = query.executeUpdate();
		transaction.commit();
			return true;

		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
}
