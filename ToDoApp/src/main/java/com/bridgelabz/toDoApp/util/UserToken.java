package com.bridgelabz.toDoApp.util;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.bridgelabz.toDoApp.model.Token;
import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceImplem.UserAccessTokenService;
@Component
public class UserToken 
{
	
/*	 private static final String KEY = "Student";
     
	    private RedisTemplate<String,  Token> redisTemplate;
	    private HashOperations hashOps;*/
	
	
	
	
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	Token token=new Token();

	@Autowired
	UserAccessTokenService tokenServices;
	
	
	public Token generateNewAccessToken()
	{
	
		token.setAccesstoken(UUID.randomUUID().toString().replace("-", ""));
		token.setCreatedtime(new Date());
		return token;
		
	}
	
	public Token generateToken()
	{
		token.setAccesstoken(UUID.randomUUID().toString().replace("-", ""));
		token.setRefreshtoken(UUID.randomUUID().toString().replace("-", ""));
		token.setCreatedtime(new Date());
		saveToken(token);
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
    		long differencetimeinminute=TimeUnit.MILLISECONDS.toMinutes(diff);
    		System.out.println("difference"+differencetimeinminute);
    		if(differencetimeinminute>180)
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
    		if(differencetimeinminute>240)
    		{
    			System.out.println("returning false");
    			return false;
    			
    		}	
    		System.out.println("returning true");
    			return true; 
    	
    	}
    		
    	return false;	
    	  
      }
      
      private static final String KEY = "Token";
      
      private RedisTemplate<String, Token> redisTemplate;
      private HashOperations<String, Integer, Token> hashOps;
   
      @Autowired
      private UserToken(RedisTemplate redisTemplate) {
          this.redisTemplate = redisTemplate;
      }
   
      @PostConstruct
      private void init() {
          hashOps = redisTemplate.opsForHash();
      }
       
      public void saveToken(Token token) {
          hashOps.put(KEY, token.getId(), token);
      }
      
      
      
      
      
      
      
      
      
      
      
      
}
