package com.bridgelabz.toDoApp.JSONResponse;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class ErrorResponse extends Response
{
	Exception exception;
	List<FieldError> list;
	public Exception getException()
	{
		return exception;
	}
	public void setException(Exception exception)
	{
		this.exception = exception;
	}
	public List<FieldError> getList() 
	{
		return list;
	}
	public void setList(List<FieldError> list) 
	{
		this.list = list;
	}

}