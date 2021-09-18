package com.college.demo.exception;

public class InvalidInputException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	private String errorCode;
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public InvalidInputException(String message) {
		super(message);
	}
	
	public InvalidInputException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public InvalidInputException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public InvalidInputException(String errorCode, String message, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}
}
