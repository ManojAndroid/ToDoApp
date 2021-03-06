package com.bridgelabz.toDoApp.service.serviceImplem;

import java.util.List;

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
public class ToDoTaskServices 
{
@Autowired
ToDoTaskDao  toDoTaskDao;

/**
 * @param todo
 */
public void toDoSaveTask(ToDo todo) 
{
	 toDoTaskDao.toDoSaveTask(todo);
}

/**
 * @param todo
 */

public void ToDoUpdateTask(ToDo todo)
{
	toDoTaskDao.ToDoUpdateTask(todo);
}

/**
 * @param id
 * @return
 */
public int ToDodeleteTask(int id)
{
	 return toDoTaskDao.ToDodeleteTask(id);
}
/**
 * @param id
 * @return
 */
public List<ToDo> getAllTaskList(int id)
{
	 return toDoTaskDao.gateAll(id);
}
public ToDo getSingleTask(int id)
{
	 return toDoTaskDao.gateSingleTask(id);
}
public void saveCollaborator(Collaborator collaborator) 
{
	 toDoTaskDao.saveCollaborator(collaborator);
}
public List getAllCollaList(int userid)
{
	 return toDoTaskDao.getAllCollaList(userid);
}

}
