package com.bridgelabz.toDoApp.util;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bridgelabz.toDoApp.model.Token;
@Component
public class UserToken extends Token
{
	@Autowired
	UserToken userToken;
	private static final long serialVersionUID = 1L;

	/************ Access Token Generate Method *******************/
	public String generateAccessToken() 
	{
		String accesstoken = UUID.randomUUID().toString();
		userToken.setAccesstokenexpire((System.currentTimeMillis()/1000+(1*60)));
		return accesstoken;

	}

	/************ Refresh Token Generate Method *******************/

	public String generateRefreshToken()
	{
		String refreshtoken = UUID.randomUUID().toString();
		userToken.setRefreshtokenexpire((System.currentTimeMillis()/1000+(5*60)));
		return refreshtoken;
	}

}
