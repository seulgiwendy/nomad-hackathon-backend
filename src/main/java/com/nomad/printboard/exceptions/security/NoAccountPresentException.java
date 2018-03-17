package com.nomad.printboard.exceptions.security;

import com.nomad.printboard.exceptions.ErrorCodes;
import org.springframework.http.HttpStatus;

public class NoAccountPresentException extends BaseSecurityException {

    public NoAccountPresentException(String message, HttpStatus httpStatus, ErrorCodes errorCodes) {
        super(message, httpStatus, errorCodes);
    }
}
