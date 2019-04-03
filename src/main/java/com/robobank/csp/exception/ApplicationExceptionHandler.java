package com.robobank.csp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Karthikeyan N
 *
 */
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = FileNotSupportedException.class)
	public ResponseEntity<String> handleUpdateViolationException(RuntimeException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
	}
	
	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<String> handleValidationException(RuntimeException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(ex.getMessage());
	}
	
}