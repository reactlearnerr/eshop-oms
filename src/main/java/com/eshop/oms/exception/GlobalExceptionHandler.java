package com.eshop.oms.exception;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class.getName());

	@ExceptionHandler(EntityNotFoundException.class)
	public String handleEntityNotFoundException(Exception ex) {
		LOGGER.severe(ex.getMessage());
		return ex.getMessage();
	}
}
