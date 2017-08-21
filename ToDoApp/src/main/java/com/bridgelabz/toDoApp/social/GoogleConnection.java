package com.bridgelabz.toDoApp.social;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.stereotype.Component;
import com.bridgelabz.toDoApp.model.GmailProfile;
import com.bridgelabz.toDoApp.model.GoogleAccessToken;
@Component
public class GoogleConnection
{

		public static final String App_Id = "401386757933-6nrofikhrbiqm3etbptd5cqcqji8o40e.apps.googleusercontent.com";
		public static final String Secret_Id = "QxhltOL-qVwGOAErF1OSWulL";
		public static final String Redirect_URI = "http://localhost:8008/ToDoApp/googleConnection";
		
		
		
		public String getGoogleAuthURL(String unid){
			
			String googleLoginURL = "";
           
			
			try{
				googleLoginURL = "https://accounts.google.com/o/oauth2/auth?client_id="+App_Id+"&redirect_uri="+URLEncoder.encode(Redirect_URI,"UTF-8")+
						"&state="+unid+"&response_type=code&scope=profile email&approval_prompt=force&access_type=offline";
				
			}
			catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}
			System.out.println("inside google authentication"+googleLoginURL);
			return googleLoginURL;
		}
		
		public String getAccessToken(String authCode) throws UnsupportedEncodingException{
			
			
			String accessTokenURL = "https://accounts.google.com/o/oauth2/token";
			
			ResteasyClient restCall = new ResteasyClientBuilder().build();
			
			ResteasyWebTarget target = restCall.target(accessTokenURL);
			
			Form form = new Form();
			form.param("client_id", App_Id);
			form.param("client_secret", Secret_Id);
			form.param("redirect_uri",Redirect_URI );
			form.param("code", authCode);
			form.param("grant_type","authorization_code");
			
			Response response = target.request().accept(MediaType.APPLICATION_JSON).post(Entity.form(form) );
			
			GoogleAccessToken accessToken = response.readEntity(GoogleAccessToken.class);
			
			restCall.close();
			return accessToken.getAccess_token();
		}
		

		public GmailProfile getUserProfile(String accessToken) 
		{
			String gmail_user_url= "https://www.googleapis.com/plus/v1/people/me";
			ResteasyClient restCall = new ResteasyClientBuilder().build();
			ResteasyWebTarget target = restCall.target(gmail_user_url);
			
			String headerAuth="Bearer "+accessToken;
			Response response = target.request().header("Authorization", headerAuth).accept(MediaType.APPLICATION_JSON).get();
			
			GmailProfile profile = response.readEntity(GmailProfile.class);
			restCall.close();
			return profile;
		}
	}

