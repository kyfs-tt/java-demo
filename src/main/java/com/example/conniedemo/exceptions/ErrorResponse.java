package com.example.conniedemo.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorResponse extends Exception {
  
  private HttpStatus status;

  public ErrorResponse(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }
}
