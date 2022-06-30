package com.cybage.exception;

public class UserFoundException  extends Exception{

	public UserFoundException()
	{
		super("User with this username is already there in DB! try with another name");
	}
	
	public UserFoundException(String msg) {super(msg);}
}
