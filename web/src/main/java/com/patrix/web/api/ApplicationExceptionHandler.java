package com.patrix.web.api;

import com.patrix.util.api.ErrorMessage;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<ErrorMessage> handleError(Exception ex) {
		log.error("API Internal Error", ex);
		final ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setCode(-1);
		errorMessage.setText(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}
}
