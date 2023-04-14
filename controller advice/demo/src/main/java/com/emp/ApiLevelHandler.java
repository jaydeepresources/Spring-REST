package com.emp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiLevelHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
		return new ResponseEntity<String>("Employee couldn't be found.", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateEmployeeException.class)
	public ResponseEntity<String> handleDuplicateEmployeeException(DuplicateEmployeeException exception) {
		return new ResponseEntity<String>("Employee Id is duplicate.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
