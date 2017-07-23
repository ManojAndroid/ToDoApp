package com.bridgelabz.toDoApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.toDoApp.JSONResponse.Response;
import com.bridgelabz.toDoApp.model.User;

@RestController
public class TokenGenerateController 
{
	public ResponseEntity<Response> generateNewToken( HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		
		return new ResponseEntity<Response>(HttpStatus.NOT_ACCEPTABLE);

	}

}
