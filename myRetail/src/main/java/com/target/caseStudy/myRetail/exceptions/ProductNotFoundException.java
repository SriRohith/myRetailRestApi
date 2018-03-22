package com.target.caseStudy.myRetail.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException(String exception) {
		super(exception);
	}

}
