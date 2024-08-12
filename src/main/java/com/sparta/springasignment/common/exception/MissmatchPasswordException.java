package com.sparta.springasignment.common.exception;

public class MissmatchPasswordException extends IllegalArgumentException {

  public MissmatchPasswordException(String string) {
    super(string);
  }
}
