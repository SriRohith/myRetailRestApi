package com.target.caseStudy.myRetail.exceptions;

/*
 * Exception handling for connection time out
 */

public class ConnectionTimeOutException extends  RuntimeException{

	private static final long serialVersionUID = 1L;

	public ConnectionTimeOutException(String exception) {
		super(exception);
		
	}
	
	
}
