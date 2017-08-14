package com.bridgelabz.toDoApp.dao.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.toDoApp.dao.daoInterface.ToDoTaskDao;
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
	public List<ToDo> gateSingleTask(int noteid) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from ToDo where taskid = "+noteid);
		List<ToDo> toDo= query.list();
		return toDo;
	}
}
