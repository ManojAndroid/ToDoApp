package com.bridgelabz.toDoApp.dao.daoInterface;


import org.springframework.stereotype.Component;

import com.bridgelabz.toDoApp.model.ToDo;
@Component
public interface ToDoTaskDao 
{
 public void toDoSaveTask(ToDo todo);
 public void ToDoUpdateTask(ToDo todo);
 public int ToDodeleteTask(int id);
 public ToDo gateAll(int id);
 
}
