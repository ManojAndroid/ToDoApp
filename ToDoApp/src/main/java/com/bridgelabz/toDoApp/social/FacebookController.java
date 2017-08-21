package com.bridgelabz.toDoApp.social;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.toDoApp.model.FaceBookProfile;
import com.bridgelabz.toDoApp.model.Token;
import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceImplem.UserAccessTokenService;
import com.bridgelabz.toDoApp.service.serviceInterface.UserService;
@RestController
public class FacebookController {

	@Autowired
	private UserService userService;
	@Autowired
	UserAccessTokenService tokenService;
	Token token=new Token();
	
	@Autowired
	private FacebookConnection facebookConnection;
	
	@RequestMapping(value="loginWithFacebook")
	public void googleConnection(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String unid = UUID.randomUUID().toString();
		request.getSession().setAttribute("STATE", unid);
		System.out.println("enside gmail controller ");
		
		String fbLoginURL = facebookConnection.getFbAuthURL(unid);
		
		response.sendRedirect(fbLoginURL);
		System.out.println("enside gmail controller55 "+fbLoginURL);
		return;
	}
	
	
	@RequestMapping(value="facebookConnection")
	public void redirectFromGoogle(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String sessionState = (String) request.getSession().getAttribute("STATE");
		String googlestate = request.getParameter("state");
		
		if( sessionState == null || !sessionState.equals(googlestate) )
		{
			System.out.println(sessionState+""+googlestate);
			response.sendRedirect("loginWithFacebook");
			return;
		}
		
		String error = request.getParameter("error");
		if( error != null && error.trim().isEmpty() ) 
		{
			response.sendRedirect("signin");
			return;
		}
		
		
		String authCode = request.getParameter("code");
		String accessToken = facebookConnection.getFbAccessToken(authCode);
		FaceBookProfile profile= facebookConnection.getFbUserProfile(accessToken);
		User user = userService.getUserByEmail(profile.getEmail());
		token.setAccesstoken(accessToken);
		token.setUser(user);
		tokenService.tokenSave(token);
		response.setHeader("accesstoken", token.getAccesstoken());
		
		
		//get user profile 
		if(user==null){
			user = new User();
		    String name = profile.getName();
		    String namesplit[]=name.split(" ");
			user.setFirstname(namesplit[0]);
			user.setLastname(namesplit[1]);
			user.setEmail(profile.getEmail());
			user.setProfile(profile.getImage().getUrl());
			user.setPassword("");
			userService.signUp(user);
		}
		HttpSession session = request.getSession();
		session.setAttribute("UserSession", user);
		response.sendRedirect("http://localhost:8008/ToDoApp/#!/home");
		
		
	}

}
