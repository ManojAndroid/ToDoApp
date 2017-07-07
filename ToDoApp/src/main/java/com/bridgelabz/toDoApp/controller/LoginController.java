package com.bridgelabz.toDoApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceInterface.UserService;
import com.bridgelabz.toDoApp.validator.UserValidation;

@RestController
public class LoginController
{

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidation userValidation;

	@RequestMapping(value = "/signin")
	public ResponseEntity<Void> signIn(@RequestBody User user )
	{
		
		/*System.out.println(email+" "+password);*/
		
			boolean signInResult = userService.signIn(user.getEmail(),user.getPassword());
			System.out.println(signInResult);
			if(signInResult==true)
			{
				
				    return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else
			 {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			 }
	}
	
}
