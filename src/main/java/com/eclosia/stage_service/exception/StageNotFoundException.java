package com.eclosia.stage_service.exception;

public class StageNotFoundException extends RuntimeException{
  public StageNotFoundException(String message) {
    super(message);
  }
}
