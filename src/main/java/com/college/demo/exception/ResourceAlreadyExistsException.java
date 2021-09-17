package com.college.demo.exception;


import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResourceAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public ResourceAlreadyExistsException(String message) {
		super(message);
	}
	
	public ResourceAlreadyExistsException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public ResourceAlreadyExistsException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public ResourceAlreadyExistsException(String errorCode, String message, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}

}