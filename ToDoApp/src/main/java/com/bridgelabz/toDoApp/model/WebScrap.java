package com.bridgelabz.toDoApp.model;

import org.springframework.stereotype.Component;

@Component
public class WebScrap
{
  private String title;
  private String image;
   private String host;

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}


public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}

public String getHost() {
	return host;
}

public void setHost(String host) {
	this.host = host;
}

@Override
public String toString() {
	return "WebScrap [title=" + title + ", image=" + image + ", host=" + host + "]";
}



  
}
