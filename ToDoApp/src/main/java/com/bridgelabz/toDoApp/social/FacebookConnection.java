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

import com.bridgelabz.toDoApp.model.FaceBookProfile;
import com.bridgelabz.toDoApp.model.FacebookAccessToken;
@Component
public class FacebookConnection {
	public static final String App_Id = "689518927923824";
	public static final String Secret_Id = "bd25a0c5561c2b0adce47e042f580765";
	public static final String Redirect_URI = "http://localhost:8008/ToDoApp/facebookConnection";
	
	
	
	public String getFbAuthURL(String unid){
		
		String fbLoginURL = "";
       
		
		try{
			fbLoginURL =  "http://www.facebook.com/dialog/oauth?"+"client_id="+App_Id+"&redirect_uri="+URLEncoder.encode(Redirect_URI, "UTF-8")+"&state="+unid+"&response_type=code"+"&scope=public_profile,email";
		  }
		catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		System.out.println("inside google authentication"+fbLoginURL);
		return fbLoginURL;
	}
	
	public String getFbAccessToken(String code) throws UnsupportedEncodingException{
		
		
		String fbaccessTokenURL = "https://graph.facebook.com/v2.9/oauth/access_token?"+"client_id="+App_Id+"&redirect_uri="+URLEncoder.encode(Redirect_URI, "UTF-8")+"&client_secret="+Secret_Id+ "&code="+code;
		System.out.println("facebook user accesstoken url"+fbaccessTokenURL);
        ResteasyClient restCall = new ResteasyClientBuilder().build();
		
		ResteasyWebTarget target = restCall.target(fbaccessTokenURL);
		
		Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
		
		FacebookAccessToken accessToken = response.readEntity(FacebookAccessToken.class);
		System.out.println("facebook user accesstoken"+accessToken);
		
		restCall.close();
		
		return accessToken.getAccess_token();
	}

	public FaceBookProfile getFbUserProfile(String accessToken) {
       String getUserURL = "https://graph.facebook.com/v2.9/me?access_token="+accessToken+"&fields=id,name,email";
		System.out.println("facebook user"+getUserURL);
		ResteasyClient restCall = new ResteasyClientBuilder().build();
		
		ResteasyWebTarget target = restCall.target(getUserURL);
		
		Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
		
		FaceBookProfile profile = response.readEntity(FaceBookProfile.class);
		System.out.println("huser details"+profile);
		restCall.close();
		
		return profile;
	}

}
