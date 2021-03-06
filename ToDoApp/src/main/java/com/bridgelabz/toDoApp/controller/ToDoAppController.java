package com.bridgelabz.toDoApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.bridgelabz.toDoApp.model.Collaborator;
import com.bridgelabz.toDoApp.model.WebScrap;
import com.bridgelabz.toDoApp.service.serviceImplem.ToDoTaskServices;
import com.bridgelabz.toDoApp.service.serviceImplem.UserServiceImplem;
import com.bridgelabz.toDoApp.util.WebScraping;

/**
 * 
 * @author bridgeit
 *
 */
@RestController
public class ToDoAppController {
	@Autowired
	private UserServiceImplem userService;
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
		toDo.setWebscripingtitle(scrap.getTitle());
		toDo.setWebscripingimage(scrap.getImage());
		toDo.setWebscripinghost(scrap.getHost());
		toDo.setUser(user);
		try {
			toDoTaskServices.toDoSaveTask(toDo);
			userResponse.setStatus(1);
			userResponse.setMessage("ToDo Created Sucessfully");
			userResponse.setUser(null);
			return new ResponseEntity<Response>(userResponse, HttpStatus.OK);
		} catch (Exception exception) {
			exception.printStackTrace();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("  Internal server error....");
			return new ResponseEntity<Response>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
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
	public ResponseEntity<Response> toDoUpdate(@RequestBody ToDo toDo, HttpServletRequest request) {

		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("UserSession");
		System.out.println("inside todoupdate");
		try {
			toDoTaskServices.ToDoUpdateTask(toDo);
			userResponse.setStatus(1);
			userResponse.setMessage("ToDo Updated Sucessfully");
			userResponse.setUser(null);
			return new ResponseEntity<Response>(userResponse, HttpStatus.OK);
		} catch (Exception exception) {
			exception.printStackTrace();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("  Internal server error....");
			return new ResponseEntity<Response>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
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
	public ResponseEntity<Response> toDoDelete(@PathVariable("id") int id) {
		try {
			toDoTaskServices.ToDodeleteTask(id);
			userResponse.setStatus(1);
			userResponse.setMessage("ToDo Deleted Sucessfully");
			return new ResponseEntity<Response>(userResponse, HttpStatus.OK);
		} catch (Exception exception) {
			exception.printStackTrace();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("  Internal server error....");
			return new ResponseEntity<Response>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
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
	public ResponseEntity<List> toDoTaskList(HttpServletRequest request) {

		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("UserSession");
		
		
		
		int uid = user.getId();
		
		System.out.println("inside getallnote controller------"+uid);
		try {
			List<ToDo> toDoAllTask = toDoTaskServices.getAllTaskList(uid);
			List collaboratorTask=toDoTaskServices.getAllCollaList(uid);
			List allNotes=new ArrayList();
			allNotes.addAll(toDoAllTask);
			allNotes.addAll(collaboratorTask);

			if (allNotes != null) {
				System.out.println(allNotes.toString());
				return new ResponseEntity<List>(allNotes, HttpStatus.OK);
			}
			System.out.println(allNotes.toString());
			return new ResponseEntity<List>(HttpStatus.UNAUTHORIZED);

		} catch (Exception exception) {
			exception.printStackTrace();
			return new ResponseEntity<List>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	/**
	 * getSingleTask,This method is used to get user single task from database
	 * 
	 * @param toDo
	 *            {@link ToDo}
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(value = "/rest/collaborate", method = RequestMethod.POST)
	public ResponseEntity<Response> Collaborator( @RequestBody Map< String,Object> colldata) {
	System.out.println("inside coll controller" + colldata.get("sharenoteid"));
		int notid = (Integer) colldata.get("sharenoteid");
		String shareEmail = (String) colldata.get("shareEmail");
		try {
		ToDo toDo =  toDoTaskServices.getSingleTask(notid);
		User owneruser= toDo.getUser();
		User shareuser= userService.getUserByEmail(shareEmail);
		
		Collaborator collaborator=new Collaborator();
		collaborator.setOwner(owneruser);
		collaborator.setSharewith(shareuser);
		collaborator.setSharenoteid(toDo);
		toDoTaskServices.saveCollaborator(collaborator);
		 return new ResponseEntity<Response>(HttpStatus.OK); 
		}
	
		 catch (Exception exception)
		{ 
		exception.printStackTrace();
		return new ResponseEntity<Response>(HttpStatus.INTERNAL_SERVER_ERROR);
		  
		  }

	}
}
