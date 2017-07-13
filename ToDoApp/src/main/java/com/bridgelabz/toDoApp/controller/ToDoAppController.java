package com.bridgelabz.toDoApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.toDoApp.JSONResponse.Response;
import com.bridgelabz.toDoApp.model.ToDo;
import com.bridgelabz.toDoApp.service.serviceImplem.ToDoTaskServices;
@RestController
public class ToDoAppController
{
@Autowired
ToDoTaskServices toDoTaskServices;

	@PostMapping(value = "/todocreate")
	public ResponseEntity<Response> toDoSave(@RequestBody ToDo toDo) 
	{
		
		toDoTaskServices.toDoSaveTask(toDo);
		return new ResponseEntity<Response>(HttpStatus.OK);
		
	}
	@PostMapping(value = "/todoupdate")
	public ResponseEntity<Response> toDoUpdate(@RequestBody ToDo toDo) 
	{
		
		toDoTaskServices.ToDoUpdateTask(toDo);
		return new ResponseEntity<Response>(HttpStatus.OK);
		
	}
	@RequestMapping(value = "/tododelete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Response> toDoDelete(@PathVariable("id") int id) 
	{
		
		toDoTaskServices.ToDodeleteTask(id);
		return new ResponseEntity<Response>(HttpStatus.OK);
		
	}

}
