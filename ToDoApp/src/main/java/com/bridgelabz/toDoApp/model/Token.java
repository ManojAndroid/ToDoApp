package com.bridgelabz.toDoApp.model;

import java.io.Serializable;


public class Token implements Serializable
{

   private static final long serialVersionUID = 1L;
  
   private String accesstoken;
   private String refreshtoken;
   private long refreshtokenexpire;
   private long accesstokenexpire;
   
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
	public long getRefreshtokenexpire() 
	{
		return refreshtokenexpire;
	}
	public void setRefreshtokenexpire(long refreshtokenexpire)
	{
		this.refreshtokenexpire = refreshtokenexpire;
	}
	public long getAccesstokenexpire() 
	{
		return accesstokenexpire;
	}
	public void setAccesstokenexpire(long accesstokenexpire) 
	{
		this.accesstokenexpire = accesstokenexpire;
	}
	@Override
	public String toString() {
		return "Token [accesstoken=" + accesstoken + ", refreshtoken=" + refreshtoken + ", refreshtokenexpire="
				+ refreshtokenexpire + ", accesstokenexpire=" + accesstokenexpire + "]";
	}
	
	
}
