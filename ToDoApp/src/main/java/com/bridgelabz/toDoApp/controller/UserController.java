package com.bridgelabz.toDoApp.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceInterface.UserService;
import com.bridgelabz.toDoApp.validator.UserValidation;

@RestController
public class UserController {
	@Autowired
	private User user;

	@Autowired
	private UserService userService;
	@Autowired
	private UserValidation userValidation;
	
	

	@RequestMapping(value = "/SignUp",method = RequestMethod.POST ,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> signUp(@RequestBody User user, BindingResult bindingResult,
			HttpServletRequest request, HttpServletResponse response)
	{
               System.out.println("hello");
               userService.signUp(user);
               return new ResponseEntity<String>(HttpStatus.OK);
		/*userValidation.validate(user, bindingResult);

		if (bindingResult.hasErrors()) 
		{
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
		else 
		{
			userService.signUp(user);
			return new ResponseEntity<String>(HttpStatus.OK);

		}*/

	}
}
