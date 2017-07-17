package com.bridgelabz.toDoApp.util;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bridgelabz.toDoApp.model.Token;
@Component
public class UserToken 
{
	
	private static final long serialVersionUID = 1L;
	
	Token token=new Token();

	/************ Access Token Generate Method *******************/
	public void generateAccessToken() 
	{
		token.setAccesstoken(UUID.randomUUID().toString());
		token.setCreatedtime(new Date());

	}

	/************ Refresh Token Generate Method *******************/

	public void generateRefreshToken()
	{
		token.setRefreshtoken(UUID.randomUUID().toString());
		token.setCreatedtime(new Date());
	}
	
	public Token generateToken()
	{
		token.setAccesstoken(UUID.randomUUID().toString());
		token.setRefreshtoken(UUID.randomUUID().toString());
		token.setCreatedtime(new Date());
		return token;

		
	}
	/************ validateToken Generate Method *******************/

	
      public boolean validateToken()
      {
    	long createdtime=  token.getCreatedtime().getTime();
    	long currenttime=new Date().getTime();
    	long differencetime=currenttime-createdtime;
    	long differencetimeinminute=TimeUnit.MILLISECONDS.toMinutes(differencetime);
    	
    	if(differencetimeinminute>2)
    	  {
    		return false;
    	  }
		  return  true;
    	  
      }
}
