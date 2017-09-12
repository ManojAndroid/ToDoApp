package com.bridgelabz.toDoApp.util;


import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class OtpNumber {
	static String otp;
	static Date date=null;
	public static String generateOTP( )
	{
		 int randomPin   =(int)(Math.random()*9000)+1000;
	     otp  =String.valueOf(randomPin);
		 date=new Date();
		 return otp;
	}
	
	
	public static boolean OtpValidation(String matchotp) 
	{
		long diff =	(new Date().getTime())-(date.getTime());
		long differencetimeinminute=TimeUnit.MILLISECONDS.toMinutes(diff);
		if(differencetimeinminute<=1)
		{
		      if (otp.equalsIgnoreCase(matchotp)) 
		      {
			    return true;
              }
		      
		}
		return false;
		
	}

}
