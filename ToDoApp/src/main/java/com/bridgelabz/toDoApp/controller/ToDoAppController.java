package com.bridgelabz.toDoApp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bridgelabz.toDoApp.JSONResponse.Response;
import com.bridgelabz.toDoApp.model.ToDo;
import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceImplem.ToDoTaskServices;

/**
 * 
 * @author bridgeit
 *
 */
@RestController
public class ToDoAppController {
	@Autowired
	ToDoTaskServices toDoTaskServices;
	
	/**
	 * @param toDo
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/todocreate")
	public ResponseEntity<Response> toDoSave(@RequestBody ToDo toDo, HttpServletRequest request) {
		
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("UserSession");
		System.out.println(user);
		toDo.setUser(user);
		try
		{

			toDoTaskServices.toDoSaveTask(toDo);
			return new ResponseEntity<Response>(HttpStatus.OK);
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return new ResponseEntity<Response>(HttpStatus.NOT_ACCEPTABLE);
		}

	}

	/**
	 * @param toDo
	 * @param taskid
	 * @return
	 */
	@PutMapping(value = "/todoupdate")
	public ResponseEntity<Response> toDoUpdate(@RequestBody ToDo toDo,@RequestParam ("taskid") int taskid)
	{
		try 
		{
			toDoTaskServices.ToDoUpdateTask(toDo);
			return new ResponseEntity<Response>(HttpStatus.OK);
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return new ResponseEntity<Response>(HttpStatus.NOT_ACCEPTABLE);
		}

	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/tododelete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> toDoDelete(@PathVariable("id") int id) {
		try
		{
			toDoTaskServices.ToDodeleteTask(id);
			return new ResponseEntity<Response>(HttpStatus.OK);
		} 
		catch (Exception exception) 
		{
			exception.printStackTrace();
			return new ResponseEntity<Response>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
		
		/**
		 * @param id
		 * @return
		 */
		@RequestMapping(value = "/getlist", method = RequestMethod.GET)
		public ResponseEntity<Response> toDoTaskList(HttpServletRequest request)
		{

			HttpSession httpSession = request.getSession();
			User user = (User) httpSession.getAttribute("UserSession");
			int uid=user.getId();
			try
			{
			List<ToDo>  toDoAllTask =	toDoTaskServices.getAllTaskList(uid);
		    System.out.println(toDoAllTask.toString());
		    return new ResponseEntity<Response>(HttpStatus.OK);
			}
			catch (Exception exception) 
			{
				exception.printStackTrace();
				return new ResponseEntity<Response>(HttpStatus.NOT_ACCEPTABLE);

			}
	}

}
