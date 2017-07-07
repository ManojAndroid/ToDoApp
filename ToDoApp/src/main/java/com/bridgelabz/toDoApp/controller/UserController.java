package com.bridgelabz.toDoApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
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
	private UserService userService;

	@Autowired
	private UserValidation userValidation;

	@PostMapping(value = "/signup", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> signUp(@RequestBody User user, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) 
	{
		
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		userValidation.validate(user, result);
		/*System.out.println(user);
		System.out.println(result);*/
		if (result.hasErrors()) 
		{
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);

		}
		else 
		{
			userService.signUp(user);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

	}

}
