package com.bridgelabz.toDoApp.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bridgelabz.toDoApp.util.UserToken;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ToDoAppFilter implements Filter
{

	
	public void init(FilterConfig filterConfig) 
			throws ServletException 
	{

	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException ,NullPointerException
	{
		HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		HttpServletResponse httpServletResponse=(HttpServletResponse) response;
		
	    WebApplicationContext context =WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
	    UserToken userToken=context.getBean(UserToken.class);
	    ObjectMapper mapper = new ObjectMapper();
	
		String accesstken=httpServletRequest.getHeader("accesstoken");
		boolean validaterequest=userToken.validateToken(accesstken);
		System.out.println(validaterequest);
		if(validaterequest==true)
		{
			System.out.println("token is valid");
			response.setContentType("application/json");
			String jsonResp = "{\"status\":\"200\",\"sucessMessage\":\"Access token is valid\"}";
			response.getWriter().write(jsonResp);
			
			
		}
		/*String jsonInString = mapper.writeValueAsString( validaterequest );
		response.getWriter().write(jsonInString);
			System.out.println("token is invalid");*/
		
		response.setContentType("application/json");
		String jsonResp = "{\"status\":\"-4\",\"errorMessage\":\"Access token is expired. Generate new Access Tokens\"}";
		response.getWriter().write(jsonResp);
		
		chain.doFilter(request, response);
		
	}

	
	public void destroy() 
	{

	}

}
