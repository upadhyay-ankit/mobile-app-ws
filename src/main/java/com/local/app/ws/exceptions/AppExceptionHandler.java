package com.local.app.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.local.app.ws.ui.model.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		String errorMessage = ex.getLocalizedMessage();
		if (errorMessage == null) {
			errorMessage = ex.toString();
		}
		ErrorMessage errorMessageOb = new ErrorMessage();
		errorMessageOb.setTimestamp(new Date());
		errorMessageOb.setMessage(errorMessage);
		return new ResponseEntity<>(errorMessageOb, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
	public ResponseEntity<Object> handleCustomrException(Exception ex, WebRequest request) {
		String errorMessage = ex.getLocalizedMessage();
		if (errorMessage == null) {
			errorMessage = ex.toString();
		}
		ErrorMessage errorMessageOb = new ErrorMessage();
		errorMessageOb.setTimestamp(new Date());
		errorMessageOb.setMessage(errorMessage);
		return new ResponseEntity<>(errorMessageOb, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
