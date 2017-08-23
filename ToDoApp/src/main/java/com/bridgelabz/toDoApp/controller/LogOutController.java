package com.bridgelabz.toDoApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.toDoApp.JSONResponse.Response;
import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceImplem.UserAccessTokenService;

/**
 * this is a simple logout controller where we check session is 
 * @author bridgeit
 *@version
 *@since
 */
@RestController
public class LogOutController {
	
	/**
	 * @param request 
	 * @param response 
	 * @return 
	 * @throws Exception
	 */
	@Autowired
	UserAccessTokenService service;
	@PostMapping(value = "/rest/logout" )
	public ResponseEntity<Response> logOut( HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		System.out.println("Inside the logout");
		HttpSession httpSession = request.getSession(false);
		User userobject = (User) httpSession.getAttribute("UserSession");
		/*String accesstken = request.getHeader("accToken");*/
		
		if (userobject != null) {
			httpSession.invalidate();
			/*service.tokenDelete(accesstken);*/
			HttpSession session = request.getSession(true);
			return new ResponseEntity<Response>(HttpStatus.OK);
		}

		return new ResponseEntity<Response>(HttpStatus.NOT_ACCEPTABLE);

	}
}
