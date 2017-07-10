package com.bridgelabz.toDoApp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceInterface.UserService;
import com.bridgelabz.toDoApp.util.CryptoUtil;

@RestController
public class LoginController 
{
	private static final Logger logger=Logger.getLogger(LoginController.class);
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/signin")
	public ResponseEntity<Void> signIn(@RequestBody User user) throws Exception 
	{
		user.setPassword(CryptoUtil.getDigest(user.getPassword()));
		User signInResult = userService.signIn(user.getEmail(), user.getPassword());
		if (signInResult != null) 
		{
			logger.error("Logging successful!");
			return new ResponseEntity<Void>(HttpStatus.FOUND);
		} 
		logger.error("Logging Unsuccessful!.... try Again");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}
