package com.bridgelabz.toDoApp.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.util.UserToken;

public class ToDoAppFilter implements Filter
{
	public void init(FilterConfig filterConfig) throws ServletException 
	{

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException, NullPointerException 
	{
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		UserToken userToken = context.getBean(UserToken.class);
		
		String accesstken = httpServletRequest.getHeader("accToken");
		System.out.println(accesstken);
		User validaterequest = userToken.validateToken(accesstken);
		System.out.println("in filter User"+validaterequest);
		if (validaterequest !=null)
        {
			request.setAttribute("userobject",validaterequest );
			chain.doFilter(request, response);
		}
		else
		{
		response.setContentType("application/json");
		String jsonResp = "{\"status\":\"-4\",\"errorMessage\":\"Access token is expired. Generate new Access Tokens\"}";
		response.getWriter().write(jsonResp);
		return;
		}
	}

	public void destroy() 
	{

	}

}
