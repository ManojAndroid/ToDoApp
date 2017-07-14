package com.bridgelabz.toDoApp.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.toDoApp.JSONResponse.Response;
import com.bridgelabz.toDoApp.JSONResponse.UserResponse;
import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceImplem.UserAccessTokenService;
import com.bridgelabz.toDoApp.service.serviceInterface.UserService;
import com.bridgelabz.toDoApp.util.CryptoUtil;
import com.bridgelabz.toDoApp.util.UserToken;

/**
 * this is 
 * @author bridgeit
 *
 */
@RestController
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	private UserService userService;
	@Autowired
	UserAccessTokenService tokenService;
	/**
	 * @param user 
	 * @param request 
	 * @param response 
	 * @return 
	 * @throws Exception 
	 */
	@PostMapping(value = "/signin")
	public ResponseEntity<Response> signIn(@RequestBody User user, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		/*Map<Integer, UserToken> map = tokenService.getUserToken(request);*/
		user.setPassword(CryptoUtil.getDigest(user.getPassword()));
		User signinresult = userService.signIn(user.getEmail(), user.getPassword(), request);
		
		if (signinresult != null) 
		{
			logger.error("Logging successful!");
			/*System.out.println("Your token number is"+map.keySet() +" "+ map.values().toString());*/
			return new ResponseEntity<Response>(HttpStatus.OK);
		} 
		else 
		{
			logger.error("Logging Unsuccessful!.... try Again");
			return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
		}
	}
}
