package com.bridgelabz.toDoApp.dao.daoimpl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.toDoApp.dao.daoInterface.ToDoTaskDao;
import com.bridgelabz.toDoApp.model.ToDo;
@Component
public class ToDoTaskImpl implements ToDoTaskDao 
{
	int id;
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void toDoSaveTask(ToDo toDo) 
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(toDo);
		transaction.commit();
		
	}

	@Override
	public void ToDoUpdateTask(ToDo todo)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(todo);
		transaction.commit();
		
	}

	@Override
	public int ToDodeleteTask(int id) {
		System.out.println(id);
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete from ToDo where id = :id");
		 query.setParameter("id", id);
		int result = query.executeUpdate();
		transaction.commit();
		return result;
	}

	@Override
	public ToDo gateAll(int id)
	{
		
		return null;
	}
}
