package com.bridgelabz.toDoApp.service.serviceImplem;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.util.UserToken;

@Component
public class UserAccessTokenService {
	
	@Autowired
	UserToken userToken;

	public Map<Integer, UserToken> getUserToken(HttpServletRequest request) {

		HttpSession httpSession = request.getSession();
		User userobject = (User) httpSession.getAttribute("UserSession");
		
		userToken.setAccesstoken(userToken.accessToken());
		userToken.setRefreshtoken(userToken.refreshToken());
		
		int uid = userobject.getId();
		Map<Integer, UserToken> map = new HashMap<Integer, UserToken>();

		map.put(uid, userToken);
		return map;
	}
}
