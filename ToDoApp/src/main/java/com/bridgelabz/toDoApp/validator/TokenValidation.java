package com.bridgelabz.toDoApp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.toDoApp.util.UserToken;

@Component
public class TokenValidation {
	@Autowired
	UserToken userToken;

	/*************
	 * CurrentTokenTimeInvalidate Generate Method
	 *******************/
	public String tokenTimeInvalidat() {
		System.out.println("sfdjhgiotri :: " + System.currentTimeMillis() / 1000);
		long getaccessexpirytime = userToken.getAccesstokenexpire();
		long getrefreshexpirytime = userToken.getRefreshtokenexpire();

		System.out.println(System.currentTimeMillis() / 1000);
		long currtime = (System.currentTimeMillis() / 1000);
		if (currtime >= getaccessexpirytime) {
			if (currtime < getrefreshexpirytime) {
				return "tokenValid";
			}
		}
		return "loginAgainTokenInvalid";
	}

}
