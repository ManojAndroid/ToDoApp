package com.bridgelabz.toDoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceInterface.UserService;

@RestController
public class LoginController 
{
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/signin")
	public ResponseEntity<Void> signIn(@RequestBody User user) 
	{
		User signInResult = userService.signIn(user.getEmail(), user.getPassword());
		System.out.println(signInResult);
		System.out.println(user.getFirstname());
		System.out.println(user.getPassword());
		if (signInResult != null) 
		{
			return new ResponseEntity<Void>(HttpStatus.FOUND);
		} 
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}
