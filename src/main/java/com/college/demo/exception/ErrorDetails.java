package com.college.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

/*
 * ErrorDetails to represent API errors. The goal of this class is to wrap exceptions in a nice 
 * JSON representation to make life easier for API clients.
 */
@Getter
public class ErrorDetails {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
	private LocalDateTime timestamp; // property holds the date-time instance of when the error happened.
	private String message; // property holds a user-friendly message about the error.
	private String details; // property holds details about the call
	private String debugMessage; // property holds a system message describing the error in more detail.
	private HttpStatus status; // property holds the operation call status like 4xx to signalize client errors
								// or 5xx to mean server errors

	public ErrorDetails() {
		timestamp = LocalDateTime.now();
	}

	public ErrorDetails(String message, String details) {
		this();
		this.message = message;
		this.details = details;
	}

	public ErrorDetails(HttpStatus status, String message, String details) {
		this();
		this.status = status;
		this.message = message;
		this.details = details;
	}

	public ErrorDetails(HttpStatus status, String message, String details, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.details = details;
		this.debugMessage = ex.getLocalizedMessage();
	}

}
