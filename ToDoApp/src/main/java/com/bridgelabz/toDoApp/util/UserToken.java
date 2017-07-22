package com.bridgelabz.toDoApp.util;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bridgelabz.toDoApp.model.Token;
import com.bridgelabz.toDoApp.service.serviceImplem.ToDoTaskServices;
import com.bridgelabz.toDoApp.service.serviceImplem.UserAccessTokenService;
@Component
public class UserToken 
{
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	Token token=new Token();

	@Autowired
	UserAccessTokenService tokenServices;
	
	/************ Access Token Generate Method *******************/
	public void generateAccessToken() 
	{
		token.setAccesstoken(UUID.randomUUID().toString().replace("-", ""));
		token.setCreatedtime(new Date());

	}

	/************ Refresh Token Generate Method *******************/

	public void generateRefreshToken()
	{
		token.setRefreshtoken(UUID.randomUUID().toString().replace("-", ""));
		token.setCreatedtime(new Date());
	}
	
	public Token generateToken()
	{
		token.setAccesstoken(UUID.randomUUID().toString().replace("-", ""));
		token.setRefreshtoken(UUID.randomUUID().toString().replace("-", ""));
		token.setCreatedtime(new Date());
		return token;

		
	}
	/************ validateToken Generate Method *******************/

	
      public boolean validateToken(String accesstoken)
      {
    	Token token=tokenServices.getToken(accesstoken);
    	if(token!=null)
    	{
    		long diff =	(token.getCreatedtime().getTime()) - (new Date().getTime());
    		long differencetimeinminute=TimeUnit.MILLISECONDS.toMinutes(diff);
    		if(differencetimeinminute>2)
    		{
    			return false;
    		}	
    	
    			return true; 
    	
    	}
    		
    	return false;	
    	  
      }
}
