package com.bridgelabz.toDoApp.service.serviceImplem;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.toDoApp.dao.daoimpl.TokenImplement;
import com.bridgelabz.toDoApp.model.Token;

@Component
public class UserAccessTokenService
{
	@Autowired
	TokenImplement tokenImplement ;
	
    public  void tokenSave( Token token)
    {
    	
    	tokenImplement.tokenSave(token);
    	
    }
    
    public Token getToken(String accesstoken)
    {
    	return 	tokenImplement.getToken(accesstoken);
    }
    
    
    
    
    
    
    
    
    
    
   
/*
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
		
	}*/
}
