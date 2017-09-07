package com.bridgelabz.toDoApp.dao.daoInterface;


import java.util.List;

import org.springframework.stereotype.Component;

import com.bridgelabz.toDoApp.model.Collaborator;
import com.bridgelabz.toDoApp.model.ToDo;
/**
 * @author bridgeit
 *
 */
@Component
public interface ToDoTaskDao 
{
 /**
 * @param todo
 */
public void toDoSaveTask(ToDo todo);
 /**
 * @param todo
 */
public void ToDoUpdateTask(ToDo todo);
 /**
 * @param id
 * @return
 */
public int ToDodeleteTask(int id);
 /**
 * @param id
 * @return
 */
public List<ToDo> gateAll(int id);
/**
 * @param id
 * @return
 */
public ToDo gateSingleTask(int id);

public void saveCollaborator(Collaborator collaborator);

 
}
