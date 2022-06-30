package com.cybage.exception;

public class SportsAlreadyExistsException extends RuntimeException {
	String message;

	public SportsAlreadyExistsException() {

	}

	public SportsAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

}
