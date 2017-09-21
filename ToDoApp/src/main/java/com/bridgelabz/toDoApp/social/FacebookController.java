package com.bridgelabz.toDoApp.social;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.Cookie;
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
import com.bridgelabz.toDoApp.service.serviceImplem.UserServiceImplem;
import com.bridgelabz.toDoApp.util.UserToken;
import com.fasterxml.jackson.databind.JsonNode;
@RestController
public class FacebookController {

	@Autowired
	private UserServiceImplem userService;
	@Autowired
	UserAccessTokenService tokenService;
	Token token=new Token();
	@Autowired
	UserToken userToken;
	
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
		System.out.println("fbaccessToken "+ accessToken);
		JsonNode profile= facebookConnection.getFbUserProfile(accessToken);
		System.out.println("fb profile :" +profile);
		User user = userService.getUserByEmail(profile.get("email").asText());
		//get user profile 
		if(user==null){
			user = new User();
		    String name = profile.get("name").asText();
		    String namesplit[]=name.split(" ");
			user.setFirstname(namesplit[0]);
			user.setLastname(namesplit[1]);
			user.setEmail(profile.get("email").asText());
			user.setProfile(profile.get("picture").get("data").get("url").asText());
			user.setPassword("");
			userService.signUp(user);
		}
		user.setProfile(profile.get("picture").get("data").get("url").asText());
		userService.uploadeProfile(user.getId(), user.getProfile());
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("UserSession", user);
		Token facebooklogintoken = userToken.generateToken();
		
		token.setUser(user);
		tokenService.tokenSave(facebooklogintoken);
		System.out.println("token.getAccesstoken()"+facebooklogintoken);
		  Cookie cookie = new Cookie("gmaillogintoken",facebooklogintoken.getAccesstoken());
		  response.addCookie(cookie);
		response.sendRedirect("http://localhost:8008/ToDoApp/#!/home");
		
		
	}

}
