package com.bridgelabz.toDoApp.social;


	import java.io.IOException;
	import java.util.UUID;

	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

	import com.bridgelabz.toDoApp.model.GmailProfile;
    import com.bridgelabz.toDoApp.model.User;
    import com.bridgelabz.toDoApp.service.serviceInterface.UserService;

	@RestController
	public class GoogleController {

		@Autowired
		private UserService userService;
		
		
		@Autowired
		private GoogleConnection googleConnection;
		
		@RequestMapping(value="/loginWithGoogle")
		public void googleConnection(HttpServletRequest request, HttpServletResponse response) throws IOException {
			
			String unid = UUID.randomUUID().toString();
			request.getSession().setAttribute("STATE", unid);
			System.out.println("enside gmail controller ");
			
			String googleLoginURL = googleConnection.getGoogleAuthURL(unid);
			
			response.sendRedirect("http://localhost:8008/toDoApp/#!/home");
			System.out.println("enside gmail controller55 "+googleLoginURL);
			return;
		}
		
		
		@RequestMapping(value="/googleConnection")
		public void redirectFromGoogle(HttpServletRequest request, HttpServletResponse response) throws IOException{
			
			System.out.println("inside google connection");
			
			String sessionState = (String) request.getSession().getAttribute("STATE");
			String googlestate = request.getParameter("state");
			
			if( sessionState == null || !sessionState.equals(googlestate) ){
				response.sendRedirect("/loginWithGoogle");
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
			
			//get user profile 
			GmailProfile profile= googleConnection.getUserProfile(accessToken);
			
			User user = userService.getUserByEmail(profile.getEmails().get(0).getValue());
			
			if(user==null){
				user = new User();
				user.setFirstname(profile.getDisplayName());
				user.setEmail(profile.getEmails().get(0).getValue());
				user.setPassword("");
				user.setProfile(profile.getImage().getUrl());
				
				userService.signUp(user);
			}
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			
			
		}

}
