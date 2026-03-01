package com.example.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex){
		
		ErrorResponse error = ErrorResponse.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.NOT_FOUND.value())
				.error(HttpStatus.NOT_FOUND.getReasonPhrase())
				.message(ex.getMessage())
				.build();
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex){

        String message = ex.getBindingResult()
        		.getFieldErrors()
        		.stream()
        		.map(error -> error.getField() + " : " + error.getDefaultMessage())
        		.collect(Collectors.joining(","));
                

        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(message)
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
	
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {

	        ErrorResponse error = ErrorResponse.builder()
	                .timestamp(LocalDateTime.now())
	                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
	                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
	                .message(ex.getMessage())
	                .build();

	        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

}
