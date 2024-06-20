package com.example.User_Service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String resourceName, String fieldName, int fieldValue) {
		super(String.format("%s not found with the given input data %s : '%s'", resourceName, fieldName, fieldValue));
	}
}
