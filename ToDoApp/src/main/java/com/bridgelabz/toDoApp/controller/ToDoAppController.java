package com.bridgelabz.toDoApp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bridgelabz.toDoApp.JSONResponse.ErrorResponse;
import com.bridgelabz.toDoApp.JSONResponse.Response;
import com.bridgelabz.toDoApp.JSONResponse.UserResponse;
import com.bridgelabz.toDoApp.model.ToDo;
import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.model.WebScrap;
import com.bridgelabz.toDoApp.service.serviceImplem.ToDoTaskServices;
import com.bridgelabz.toDoApp.util.WebScraping;

/**
 * 
 * @author bridgeit
 *
 */
@RestController
public class ToDoAppController {
	@Autowired
	ToDoTaskServices toDoTaskServices;
	@Autowired
	private UserResponse userResponse;
	@Autowired
	 private ErrorResponse errorResponse;
	@Autowired
	WebScrap scrap;
	@Autowired
	WebScraping scraping;
	/**
	 * toDoSave, method is used to save the user task in database
	 * 
	 * @param toDo
	 *            {@link ToDo}
	 * @param request
	 *            {@link HttpServletRequest}
	 * @return {@link ResponseEntity}
	 */
	@PostMapping(value = "/rest/todocreate")
	public ResponseEntity<Response> toDoSave(@RequestBody ToDo toDo, HttpServletRequest request) {

		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("UserSession");
		
		scraping.webScraping(toDo.getDescription());
		
        System.out.println("webscraping in controller  "+scrap.getTitle());
		toDo.setWebscripingtitle(scrap.getTitle());
		toDo.setUser(user);
		try 
		{
			toDoTaskServices.toDoSaveTask(toDo);
			userResponse.setStatus(1);
			userResponse.setMessage("ToDo Created Sucessfully");
			userResponse.setUser(null);
			return new ResponseEntity<Response>(userResponse,HttpStatus.OK);
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("  Internal server error....");
			return new ResponseEntity<Response>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * toDoUpdate, This method is used to update user task and save in database
	 * 
	 * @param toDo
	 *            {@link ToDo}
	 * @param taskid
	 *            it is user task id in integer form,
	 * @return {@link ResponseEntity}
	 */
	@PutMapping(value = "/rest/todoupdate")
	public ResponseEntity<Response> toDoUpdate(@RequestBody ToDo toDo,HttpServletRequest request) {
		
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("UserSession");
		System.out.println("inside todoupdate");
		try {
			toDoTaskServices.ToDoUpdateTask(toDo);
			userResponse.setStatus(1);
			userResponse.setMessage("ToDo Updated Sucessfully");
			userResponse.setUser(null);
			return new ResponseEntity<Response>(userResponse,HttpStatus.OK);
		} 
		catch (Exception exception) 
		{
			exception.printStackTrace();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("  Internal server error....");
			return new ResponseEntity<Response>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * toDoDelete, This method is used to delete user individual task from
	 * database,
	 * 
	 * @param id
	 *            it is user task id in integer form,
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(value = "/rest/tododelete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> toDoDelete(@PathVariable("id") int id) 
	{
		try {
			toDoTaskServices.ToDodeleteTask(id);
			userResponse.setStatus(1);
			userResponse.setMessage("ToDo Deleted Sucessfully");
			return new ResponseEntity<Response>(userResponse,HttpStatus.OK);
		} catch (Exception exception) {
			exception.printStackTrace();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("  Internal server error....");
			return new ResponseEntity<Response>( errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * toDoTaskList, this method is used to getAllUsER task from database,
	 * 
	 * @param request
	 *            {@link HttpServletRequest}
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(value = "/rest/getlist", method = RequestMethod.GET)
	public ResponseEntity<List<ToDo>> toDoTaskList(HttpServletRequest request) {

		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("UserSession");
		int uid = user.getId();
		try {
			List<ToDo> toDoAllTask = toDoTaskServices.getAllTaskList(uid);
			
			if(toDoAllTask!=null)
			{
				System.out.println(toDoAllTask.toString());
				return new ResponseEntity<List<ToDo>>(toDoAllTask,HttpStatus.OK);
			}
			System.out.println(toDoAllTask.toString());
			return new ResponseEntity<List<ToDo>>(HttpStatus.UNAUTHORIZED);
			
		} catch (Exception exception)
		{
			exception.printStackTrace();
			return new ResponseEntity<List<ToDo>>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	/**
	 * getSingleTask,This method is used to get user single task from database
	 * 
	 * @param toDo
	 *            {@link ToDo}
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(value = "/rest/getsingletask/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<ToDo>> getSingleTask(@PathVariable("id") int id) {

		try {
			List<ToDo> toDosingleTask = toDoTaskServices.getSingleTask(id);
			if (toDosingleTask != null) {
				System.out.println(toDosingleTask.toString());
				return new ResponseEntity<List<ToDo>>(HttpStatus.OK);
			}

			return new ResponseEntity<List<ToDo>>(HttpStatus.UNAUTHORIZED);
		} catch (Exception exception) {
			exception.printStackTrace();
			return new ResponseEntity<List<ToDo>>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
