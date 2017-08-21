package com.bridgelabz.toDoApp.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GmailProfile {

	private String id;
	private String displayName;
	private UserImage image;
	private List<GoogleEmails> emails;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public UserImage getImage() {
		return image;
	}
	public void setImage(UserImage image) {
		this.image = image;
	}
	public List<GoogleEmails> getEmails() {
		return emails;
	}
	public void setEmails(List<GoogleEmails> emails) {
		this.emails = emails;
	}
	@Override
	public String toString() {
		return "GmailProfile [id=" + id + ", displayName=" + displayName + ", image=" + image + ", emails=" + emails
				+ "]";
	}

}
