package com.bridgelabz.toDoApp.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bridgelabz.toDoApp.util.UserToken;
public class ToDoAppFilter implements Filter
{

	@Override
	public void init(FilterConfig filterConfig) 
			throws ServletException 
	{

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException ,NullPointerException
	{
		HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		HttpServletResponse httpServletResponse=(HttpServletResponse) response;
		
	    WebApplicationContext context =WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
	    UserToken userToken=context.getBean(UserToken.class);
		
		/*
		String accesstken=httpServletRequest.getHeader("accesstoken");
		String refreshtoken=httpServletRequest.getHeader("refreshtoken");
		String createdtime=httpServletRequest.getHeader("createdtime");*/
		
		/*long getcreatedtime=Long.parseLong(createdtime);
		System.out.println(getcreatedtime);
		
		boolean validaterequest=userToken.validateToken();
		System.out.println(validaterequest);
		if(validaterequest==true)
		{
			System.out.println("token is valid");
			
			httpServletResponse.sendRedirect("/home");
		}
		else
		{
			System.out.println("token is invalid");

			httpServletResponse.sendRedirect("/signin");
		}
		
		chain.doFilter(request, response);
		*/  
	}

	@Override
	public void destroy() 
	{

	}

}
