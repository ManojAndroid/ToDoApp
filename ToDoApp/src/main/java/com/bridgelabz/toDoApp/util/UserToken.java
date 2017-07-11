package com.bridgelabz.toDoApp.util;

import java.util.UUID;
import org.springframework.stereotype.Component;

import com.bridgelabz.toDoApp.model.Token;

@Component
public class UserToken extends Token

{
	private static final long serialVersionUID = 1L;

	/************ Access Token Generate Method *******************/
	public String accessToken() {
		String accesstoken = UUID.randomUUID().toString();
		return accesstoken;

	}

	/************ Refresh Token Generate Method *******************/

	public String refreshToken() {
		String refreshtoken = UUID.randomUUID().toString();
		return refreshtoken;
	}

}
