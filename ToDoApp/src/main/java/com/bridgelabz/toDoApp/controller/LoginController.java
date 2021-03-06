package com.bridgelabz.toDoApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.toDoApp.JSONResponse.Response;
import com.bridgelabz.toDoApp.JSONResponse.UserResponse;
import com.bridgelabz.toDoApp.model.Token;
import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceImplem.UserAccessTokenService;
import com.bridgelabz.toDoApp.service.serviceImplem.UserServiceImplem;
import com.bridgelabz.toDoApp.util.CryptoUtil;
import com.bridgelabz.toDoApp.util.UserToken;

/**
 * this is
 * 
 * @author bridgeit
 *
 */
@RestController
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	private UserServiceImplem userService;
	@Autowired
	UserAccessTokenService tokenService;
	@Autowired
	UserToken userToken;
	/*Token token = new Token();*/
	@Autowired
	private Response errorResponse;
	@Autowired
	private UserResponse userResponse;

	/**
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/signin")
	public ResponseEntity<Response> signIn(@RequestBody User user, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			user.setPassword(CryptoUtil.getDigest(user.getPassword()));
			User signinresult = userService.signIn(user.getEmail(), user.getPassword(), request);

			if (signinresult != null) {
				logger.error("Logging successful!");
				Token token = userToken.generateToken();
				token.setUser(signinresult);
				userToken.saveToken(token);
				tokenService.tokenSave(token);
				userResponse.setStatus(1);
				userResponse.setMessage("logging sucessfully");
				userResponse.setToken(token);
				return new ResponseEntity<Response>(userResponse, HttpStatus.OK);
			}
			else{
			logger.error("Logging Unsuccessful!.... try Again");
			userResponse.setStatus(-1);
			userResponse.setMessage(" Email/Password Invalid.....try Again!!");
			return new ResponseEntity<Response>(userResponse,HttpStatus.OK);
			}
		} 
		
		catch (Exception exception) 
		{
			exception.printStackTrace();
			logger.error("Registration Failed");
			errorResponse.setStatus(-2);
			errorResponse.setMessage("  Internal server error....");
			return new ResponseEntity<Response>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
