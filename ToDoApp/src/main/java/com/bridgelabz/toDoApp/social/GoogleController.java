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
	import com.bridgelabz.toDoApp.model.GmailProfile;
    import com.bridgelabz.toDoApp.model.Token;
    import com.bridgelabz.toDoApp.model.User;
    import com.bridgelabz.toDoApp.service.serviceImplem.UserAccessTokenService;
    import com.bridgelabz.toDoApp.service.serviceImplem.UserServiceImplem;
import com.bridgelabz.toDoApp.util.UserToken;

	@RestController
	public class GoogleController 
	{
		@Autowired
		UserToken userToken;
		@Autowired
		private UserServiceImplem userService;
		@Autowired
		UserAccessTokenService tokenService;
		Token token=new Token();
		
		@Autowired
		private GoogleConnection googleConnection;
		
		@RequestMapping(value="loginWithGoogle")
		public void googleConnection(HttpServletRequest request, HttpServletResponse response) throws IOException 
		{
			String unid = UUID.randomUUID().toString();
			request.getSession().setAttribute("STATE", unid);
			String googleLoginURL = googleConnection.getGoogleAuthURL(unid);
			response.sendRedirect(googleLoginURL);
			return;
		}
		@RequestMapping(value="googleConnection")
		public void redirectFromGoogle(HttpServletRequest request, HttpServletResponse response) throws IOException{
			String sessionState = (String) request.getSession().getAttribute("STATE");
			String googlestate = request.getParameter("state");
			
			if( sessionState == null || !sessionState.equals(googlestate) )
			{
				response.sendRedirect("loginWithGoogle");
				return;
			}
			
			String error = request.getParameter("error");
			if( error != null && error.trim().isEmpty() ) 
			{
				response.sendRedirect("signin");
				return;
			}
			
			String authCode = request.getParameter("code");
			String accessToken = googleConnection.getAccessToken(authCode);
			
			GmailProfile profile= googleConnection.getUserProfile(accessToken);
			User user = userService.getUserByEmail(profile.getEmails().get(0).getValue());
			System.out.println("userdetail"+user);
			if(user==null)
			{
				user = new User();
			    String name = profile.getDisplayName();
			    String namesplit[]=name.split(" ");
				user.setFirstname(namesplit[0]);
				user.setLastname(namesplit[1]);
				user.setEmail(profile.getEmails().get(0).getValue());
				user.setPassword("");
				
				userService.signUp(user);
			}
			user.setProfile(profile.getImage().getUrl());
			userService.uploadeProfile(user.getId(), user.getProfile());
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("UserSession", user);
			Token gmaillogintoken = userToken.generateToken();
			token.setUser(user);
			tokenService.tokenSave(gmaillogintoken);
			System.out.println("token.getAccesstoken()"+gmaillogintoken);
			  Cookie cookie = new Cookie("gmaillogintoken",gmaillogintoken.getAccesstoken());
			  response.addCookie(cookie);
			response.sendRedirect("http://localhost:8008/ToDoApp/#!/home");
		}

}
