package com.cybage.exception;

public class OfferAlreadyExistsException extends RuntimeException{
	String message;
	
	
	public OfferAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}


	public OfferAlreadyExistsException() {
	}
}
