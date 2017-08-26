package com.bridgelabz.toDoApp.model;

import org.springframework.stereotype.Component;

@Component
public class WebScrap
{
  private String title;

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

@Override
public String toString() {
	return "WebScrap [title=" + title + "]";
}
  
  
}
