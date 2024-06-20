package com.example.Order_Service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OrderAlreadyPresentException extends RuntimeException {

	public OrderAlreadyPresentException(String msg) {
		super(msg);
	}
}