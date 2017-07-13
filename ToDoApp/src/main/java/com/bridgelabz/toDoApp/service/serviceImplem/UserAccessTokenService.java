package com.bridgelabz.toDoApp.service.serviceImplem;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.util.UserToken;
import com.bridgelabz.toDoApp.validator.TokenValidation;

@Component
public class UserAccessTokenService
{
	
	@Autowired
	UserToken userToken;
	
	@Autowired
	TokenValidation tokenValidation;
	
    Map<Integer, UserToken> map = new HashMap<Integer, UserToken>();
   

	public Map<Integer, UserToken> getUserToken(HttpServletRequest request) 
	{
		HttpSession httpSession = request.getSession();
		User userobject = (User) httpSession.getAttribute("UserSession");
		int uid =(userobject.getId());
		String comparetime=tokenValidation.tokenTimeInvalidat();
		System.out.println("msg :"+comparetime);
		 String accesstoken=userToken.generateAccessToken();
		 String refreshtoken= userToken.generateRefreshToken();
		 
		if(map.containsKey(uid))
		{
		return map;
		}
		else if(comparetime.equals("loginAgainTokenInvalid"))
		{
		
		map.remove(uid, userToken);
		System.out.println("map after remove :"+map);
		userToken.setAccesstoken(accesstoken);
		userToken.setRefreshtoken(refreshtoken);
		
		map.put(uid, userToken);
		return map;
		}
		
		else if(comparetime.equals("tokenValid"))
		{
		
		map.put(uid, userToken);
		return map;
		}
		
		else
		{
			userToken.setAccesstoken(accesstoken);
			userToken.setRefreshtoken(refreshtoken);
			map.put(uid, userToken);
			return map;
		}
		
	}
}
