package com.bridgelabz.toDoApp.model;

import java.io.Serializable;
import java.util.Date;


public class Token implements Serializable
{

	private static final long serialVersionUID = 1L;
private int id;
   private String accesstoken;
   private String refreshtoken;
   private Date createdon;
	  
   public int getId() 
   {
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getAccesstoken()
	{
		return accesstoken;
	}
	public void setAccesstoken(String accesstoken)
	{
		this.accesstoken = accesstoken;
	}
	public String getRefreshtoken()
	{
		return refreshtoken;
	}
	public void setRefreshtoken(String refreshtoken)
	{
		this.refreshtoken = refreshtoken;
	}
	public Date getCreatedon() 
	{
		return createdon;
	}
	public void setCreatedon(Date createdon)
	{
		this.createdon = createdon;
	}
	@Override
	public String toString() {
		return "Token [id=" + id + ", accesstoken=" + accesstoken + ", refreshtoken=" + refreshtoken + ", createdon="
				+ createdon + "]";
	}

}
