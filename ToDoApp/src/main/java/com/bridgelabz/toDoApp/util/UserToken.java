package com.bridgelabz.toDoApp.util;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bridgelabz.toDoApp.model.Token;
import com.bridgelabz.toDoApp.model.User;
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
	
	public Token generateNewAccessToken()
	{
	
		token.setAccesstoken(UUID.randomUUID().toString().replace("-", ""));
		return token;
	}
	
	public Token generateToken()
	{
		token.setAccesstoken(UUID.randomUUID().toString().replace("-", ""));
		token.setRefreshtoken(UUID.randomUUID().toString().replace("-", ""));
		token.setCreatedtime(new Date());
		return token;

		
	}
	/************ validateToken Generate Method *******************/

	
      public User validateToken(String accesstoken)
      {
    	Token token=tokenServices.getToken(accesstoken);
    	System.out.println("token"+token);
    	if(token!=null)
    	{
    		long diff =	(new Date().getTime())-(token.getCreatedtime().getTime());
    		System.out.println("created time"+(token.getCreatedtime().getTime()));
    		System.out.println("current time"+(new Date().getTime()));
    		System.out.println("difference time"+diff);
    		long differencetimeinminute=TimeUnit.MILLISECONDS.toMinutes(diff);
    		System.out.println("difference"+differencetimeinminute);
    		if(differencetimeinminute>30)
    		{
    			return null;
    		}	
    		System.out.println( "user Token IN Validation"+token.getUser());
    			return (token.getUser()); 
    	
    	}
    		
    	return null;	
    	  
      }
      public boolean refreshTokenValidation(String refreshtoken)
      {
    	Token token=tokenServices.getRefreshToken(refreshtoken);
    	System.out.println("token"+token);
    	if(token!=null)
    	{
    		long diff =	(new Date().getTime())-(token.getCreatedtime().getTime());
    		System.out.println("created time"+(token.getCreatedtime().getTime()));
    		System.out.println("current time"+(new Date().getTime()));
    		System.out.println("difference time"+diff);
    		long differencetimeinminute=TimeUnit.MILLISECONDS.toMinutes(diff);
    		System.out.println("difference"+differencetimeinminute);
    		if(differencetimeinminute>10)
    		{
    			return false;
    		}	
    			return true; 
    	
    	}
    		
    	return false;	
    	  
      }
}
