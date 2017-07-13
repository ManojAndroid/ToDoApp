package com.bridgelabz.toDoApp.service.serviceImplem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.toDoApp.dao.daoInterface.ToDoTaskDao;
import com.bridgelabz.toDoApp.model.ToDo;
@Component
public class ToDoTaskServices 
{
@Autowired
ToDoTaskDao  toDoTaskDao;

public void toDoSaveTask(ToDo todo) 
{
	 toDoTaskDao.toDoSaveTask(todo);
}

public void ToDoUpdateTask(ToDo todo)
{
	toDoTaskDao.ToDoUpdateTask(todo);
}

public int ToDodeleteTask(int id)
{
	 return toDoTaskDao.ToDodeleteTask(id);
}




}
