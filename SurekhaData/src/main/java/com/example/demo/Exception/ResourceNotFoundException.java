package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{
	
	private static final long serivalVersionUID = 1;
	
	
	public ResourceNotFoundException(String message)
	{
		super(message);
	}

}
