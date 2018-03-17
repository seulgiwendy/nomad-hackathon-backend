package com.nomad.printboard.exceptions.security;

import org.springframework.http.HttpStatus;

public class BadCredentialsException extends BaseSecurityException {

    public BadCredentialsException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
