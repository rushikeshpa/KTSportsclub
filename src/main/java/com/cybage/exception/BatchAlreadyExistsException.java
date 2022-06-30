package com.cybage.exception;

public class BatchAlreadyExistsException extends RuntimeException {
	String message;

	public BatchAlreadyExistsException() {
		// TODO Auto-generated constructor stub
	}

	public BatchAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

}
