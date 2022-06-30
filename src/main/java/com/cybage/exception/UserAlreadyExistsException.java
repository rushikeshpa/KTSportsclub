package com.cybage.exception;

public class UserAlreadyExistsException extends RuntimeException {
	String message;

	public UserAlreadyExistsException() {

	}

	public UserAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

}
