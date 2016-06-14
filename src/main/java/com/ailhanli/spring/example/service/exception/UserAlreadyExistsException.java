package com.ailhanli.spring.example.service.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(final String message) {
        super(message);
    }
}
