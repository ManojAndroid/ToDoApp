package com.bridgelabz.toDoApp.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ToDoAppFilter implements Filter
{

	@Override
	public void init(FilterConfig filterConfig) 
			throws ServletException 
	{

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException 
	{
		HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		HttpServletResponse httpServletResponse=(HttpServletResponse) response;
		
		
		
		
		String accesstken=httpServletRequest.getHeader("accesstoken");
		String refreshtoken=httpServletRequest.getHeader("refreshtoken");
		String createdtime=httpServletRequest.getHeader("createdtime");
		long getcreatedtime=Long.parseLong(createdtime);
		
		System.out.println(getcreatedtime);
		
		chain.doFilter(request, response);
		  
	}

	@Override
	public void destroy() 
	{

	}

}
