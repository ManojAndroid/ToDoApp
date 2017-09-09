package com.bridgelabz.toDoApp.dao.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.toDoApp.dao.daoInterface.ToDoTaskDao;
import com.bridgelabz.toDoApp.model.Collaborator;
import com.bridgelabz.toDoApp.model.ToDo;
/**
 * @author bridgeit
 *
 */
@Component
public class ToDoTaskImpl implements ToDoTaskDao 
{

	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.bridgelabz.toDoApp.dao.daoInterface.ToDoTaskDao#toDoSaveTask(com.bridgelabz.toDoApp.model.ToDo)
	 */
	
	public void toDoSaveTask(ToDo toDo) 
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(toDo);
		transaction.commit();
		
	}

	/* (non-Javadoc)
	 * @see com.bridgelabz.toDoApp.dao.daoInterface.ToDoTaskDao#ToDoUpdateTask(com.bridgelabz.toDoApp.model.ToDo)
	 */
	
	public void ToDoUpdateTask(ToDo todo)
	{
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(todo);
		transaction.commit();
		
	}

	/* (non-Javadoc)
	 * @see com.bridgelabz.toDoApp.dao.daoInterface.ToDoTaskDao#ToDodeleteTask(int)
	 */
	
	public int ToDodeleteTask(int id)
	{
		System.out.println(id);
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete from ToDo where id = :id");
		query.setParameter("id", id);
		int result = query.executeUpdate();
		transaction.commit();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.bridgelabz.toDoApp.dao.daoInterface.ToDoTaskDao#gateAll(int)
	 */

	public List<ToDo> gateAll(int userid)
	{

			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from ToDo where userid = "+userid);
			List<ToDo> toDo= query.list();
			System.out.println("get all"+toDo);
			return toDo;
	}

	public ToDo gateSingleTask(int noteid) {
		System.out.println("inside dao impl");
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(ToDo.class);
		criteria.add(Restrictions.eq("id", noteid));
		ToDo toDo = (ToDo) criteria.uniqueResult();
		System.out.println(toDo);
		return toDo;
	}
	public void saveCollaborator(Collaborator collaborator) 
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(collaborator);
		transaction.commit();
		
	}
	public List getAllCollaList(int userid)
	{

			Session session = sessionFactory.openSession();
			Query query = session.createQuery(" select sharenoteid from Collaborator where sharewith_userid = "+userid);
			List collaborator= query.list();
			System.out.println("get all"+collaborator);
			return collaborator;
	}
}
