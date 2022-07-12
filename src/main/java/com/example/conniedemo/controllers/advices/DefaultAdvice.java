package com.example.conniedemo.controllers.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.conniedemo.exceptions.ErrorResponse;

@RestControllerAdvice
public class DefaultAdvice {
  
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> defaultAdvice() {
    return new ResponseEntity<ErrorResponse>(
      new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "A server error occured"),
      HttpStatus.INTERNAL_SERVER_ERROR
    );
  }
}
