package com.bridgelabz.toDoApp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.toDoApp.JSONResponse.ErrorResponse;
import com.bridgelabz.toDoApp.JSONResponse.Response;
import com.bridgelabz.toDoApp.JSONResponse.UserResponse;
import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceInterface.UserService;
import com.bridgelabz.toDoApp.util.CryptoUtil;
import com.bridgelabz.toDoApp.validator.UserValidation;

@RestController
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private UserValidation userValidation;
	@Autowired
	private UserResponse userResponse;
	@Autowired
	 private ErrorResponse errorResponse;

	@PostMapping(value = "/signup", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Response> signUp(@RequestBody User user, BindingResult result) throws Exception {

		userValidation.validate(user, result);
		
		if (result.hasErrors())
		{
			logger.error("Registration Failed.... try Again");
			List<FieldError> list=result.getFieldErrors();
			errorResponse.setList(list);
			errorResponse.setStatus(-1);
			errorResponse.setMessage("binding result error");
			
			return new ResponseEntity<Response>(errorResponse, HttpStatus.NOT_ACCEPTABLE);

		}
		try 
		{
			user.setPassword(CryptoUtil.getDigest(user.getPassword()));
			userService.signUp(user);
			logger.info("Registered successfully!");
			userResponse.setStatus(1);
			userResponse.setMessage("Sucessfully Registered");
			userResponse.setUser(user);
			
			return new ResponseEntity<Response>(userResponse, HttpStatus.OK);
		}
		catch (Exception exception) 
		{
			exception.printStackTrace();
			logger.error("Registration Failed");
			errorResponse.setStatus(-1);
			errorResponse.setMessage("DataBase server error....");
			return new ResponseEntity<Response>(errorResponse,HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
