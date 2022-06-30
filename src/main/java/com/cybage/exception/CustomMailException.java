package com.cybage.exception;

public class CustomMailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CustomMailException(String msg){
		super(msg);
	}

}
