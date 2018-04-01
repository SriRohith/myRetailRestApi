package com.target.caseStudy.myRetail.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionalHandlerAdvice extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ExceptionDetails> productNotFoundExceptionAdvice(ProductNotFoundException exception,
			WebRequest request) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<ExceptionDetails> jsonProcessingException(JsonProcessingException exception,
			WebRequest request) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ConnectionTimeOutException.class)
	public ResponseEntity<ExceptionDetails> connectionTimeOutException(ConnectionTimeOutException exception,
			WebRequest request) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(), exception.getMessage());
		return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionDetails> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
