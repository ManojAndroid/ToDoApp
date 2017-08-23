package com.bridgelabz.toDoApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.toDoApp.JSONResponse.Response;
import com.bridgelabz.toDoApp.model.Token;
import com.bridgelabz.toDoApp.service.serviceImplem.UserAccessTokenService;
import com.bridgelabz.toDoApp.util.UserToken;

@RestController
public class GenerateNewAccessTokenController
{
	@Autowired
	UserAccessTokenService tokenService;
	UserToken userToken;

	@RequestMapping(value = "/newaccesstoken")
	public ResponseEntity<Response> generateNewToken(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String accesstken = request.getHeader("accToken");
		System.out.println("heder token"+accesstken);
		Token token = tokenService.getRefreshToken(accesstken);
		System.out.println(token);
		String refreshToken = token.getRefreshtoken();
		try {
			
			boolean refreshtokenresult = userToken.refreshTokenValidation(refreshToken);
			System.out.println("refreshtokenresult"+refreshtokenresult);
			if (refreshtokenresult == true)
			{
				Token accesstoken = userToken.generateNewAccessToken();
				tokenService.tokenUpdate(accesstoken);
				return new ResponseEntity<Response>(HttpStatus.OK);
			}
			
			return new ResponseEntity<Response>(HttpStatus.NOT_ACCEPTABLE);
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return new ResponseEntity<Response>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
