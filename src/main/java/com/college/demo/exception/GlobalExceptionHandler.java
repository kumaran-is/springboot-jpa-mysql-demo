package com.college.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/*
 * Central point for treating exceptions and wrapping them up in an ErrorDetails object 
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND, ex.getMessage(),
				request.getDescription(false), ex);
		// log exception first, then return Not Found (404)
		log.error(ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, errorDetails.getStatus());
	}

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	protected ResponseEntity<ErrorDetails> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(HttpStatus.CONFLICT, ex.getMessage(),
				request.getDescription(false), ex);
		// log exception first, then return Conflict (409)
		log.error(ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, errorDetails.getStatus());
	}

	@ExceptionHandler(InvalidInputException.class)
	protected ResponseEntity<ErrorDetails> handleInvalidInputException(InvalidInputException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, ex.getMessage(),
				request.getDescription(false), ex);
		// log exception first, then return Bad Request (400)
		log.error(ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, errorDetails.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		/*Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {

			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		}); */
		
		ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, "Validation Error",
				request.getDescription(false), ex);
		errorDetails.addValidationErrors(ex.getBindingResult().getFieldErrors());
		log.error(ex.getMessage());
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST); 
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorDetails> handlGlobeExceptionHandler(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
				request.getDescription(false), ex);
		log.error(ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, errorDetails.getStatus());
	}
}
