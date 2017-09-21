package com.bridgelabz.toDoApp.social;

import java.io.IOException;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FacebookConnection {
	public static final String App_Id = "689518927923824";
	public static final String Secret_Id = "bd25a0c5561c2b0adce47e042f580765";
	public static final String Redirect_URI = "http://localhost:8008/ToDoApp/facebookConnection";

	public String getFbAuthURL(String unid) {

		String fbLoginURL = "";

		try {
			fbLoginURL = "http://www.facebook.com/dialog/oauth?" + "client_id=" + App_Id + "&redirect_uri="
					+ URLEncoder.encode(Redirect_URI, "UTF-8") + "&state=" + unid + "&response_type=code"
					+ "&scope=public_profile,email";
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		System.out.println("inside fb authentication" + fbLoginURL);
		return fbLoginURL;
	}

	public String getFbAccessToken(String code) throws UnsupportedEncodingException {

		String fbaccessTokenURL = "https://graph.facebook.com/v2.9/oauth/access_token?" + "client_id=" + App_Id
				+ "&redirect_uri=" + URLEncoder.encode(Redirect_URI, "UTF-8") + "&client_secret=" + Secret_Id + "&code="
				+ code;
		System.out.println("facebook user accesstoken url" + fbaccessTokenURL);
		ResteasyClient restCall = new ResteasyClientBuilder().build();

		ResteasyWebTarget target = restCall.target(fbaccessTokenURL);

		Response response = target.request().accept(MediaType.APPLICATION_JSON).get();

		String accessToken = response.readEntity(String.class);
		ObjectMapper mapper = new ObjectMapper();

		String FBacc_token = null;
		try {
			FBacc_token = mapper.readTree(accessToken).get("access_token").asText();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		restCall.close();
		return FBacc_token;
	}

	public JsonNode getFbUserProfile(String accessToken) {
		String getUserURL = "https://graph.facebook.com/v2.9/me?access_token=" + accessToken
				+ "&fields=id,name,email,picture";

		ResteasyClient restCall = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = restCall.target(getUserURL);
		Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
		String profile = response.readEntity(String.class);
		ObjectMapper mapper = new ObjectMapper();

		JsonNode FBprofile = null;
		try {
			FBprofile = mapper.readTree(profile);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		restCall.close();
		return FBprofile;
	}
}
