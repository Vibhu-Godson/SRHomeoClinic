package com.srhomoeo.clinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.srhomoeo.clinic.payloads.APIResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIResponse> responseNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message = ex.getMessage();
		APIResponse apiResponse = new APIResponse(message,false);
		return new ResponseEntity(apiResponse,HttpStatus.NOT_FOUND);
	}
}
