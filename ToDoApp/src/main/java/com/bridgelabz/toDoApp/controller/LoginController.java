package com.bridgelabz.toDoApp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.bridgelabz.toDoApp.service.serviceImplem.UserAccessTokenService;
import com.bridgelabz.toDoApp.service.serviceInterface.UserService;
import com.bridgelabz.toDoApp.util.CryptoUtil;
import com.bridgelabz.toDoApp.util.UserToken;
import com.bridgelabz.toDoApp.validator.UserValidation;

@RestController
public class LoginController {

	@Autowired
	private UserValidation userValidation;
	private static final Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	private UserService userService;
	@Autowired
	UserAccessTokenService tokenService;

	@Autowired
	private UserResponse userResponse;

	@Autowired
	private ErrorResponse errorResponse;

	@PostMapping(value = "/signin")
	public ResponseEntity<Response> signIn(@RequestBody User user, HttpServletRequest request,
			HttpServletResponse response, BindingResult result) throws Exception {

		userValidation.userLoginValidation(user, result);
		System.out.println(result.hasErrors());
		if (result.hasErrors()) {
			List<FieldError> list = result.getFieldErrors();
			System.out.println(list);
			errorResponse.setList(list);
			errorResponse.setStatus(-1);
			errorResponse.setMessage("binding result error");
			return new ResponseEntity<Response>(errorResponse, HttpStatus.EXPECTATION_FAILED);
		}
		
		user.setPassword(CryptoUtil.getDigest(user.getPassword()));
		User signinresult = userService.signIn(user.getEmail(), user.getPassword(), request);
		if (signinresult != null) {
			logger.error("Logging successful!");

			Map<Integer, UserToken> m = tokenService.getUserToken(request);
			System.out.println("Your token number is" + m.values().toString());
			return new ResponseEntity<Response>(HttpStatus.FOUND);
		} 
		else {

			logger.error("Logging Unsuccessful!.... try Again");
			return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
		}
	}
}
