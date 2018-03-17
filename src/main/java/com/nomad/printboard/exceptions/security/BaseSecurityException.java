package com.nomad.printboard.exceptions.security;

import com.nomad.printboard.exceptions.ApplicationException;
import com.nomad.printboard.exceptions.ErrorCodes;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseSecurityException extends ApplicationException {

    private String message;
    private HttpStatus httpStatus;
    private ErrorCodes errorCodes;

    public BaseSecurityException(String messsage) {
        super(messsage);
        this.message = messsage;
        this.httpStatus = HttpStatus.FORBIDDEN;
        this.errorCodes = ErrorCodes.GENERAL;
    }

    public BaseSecurityException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
        this.message = message;
        this.httpStatus = httpStatus;
        this.errorCodes = ErrorCodes.GENERAL;
    }

    public BaseSecurityException(String message, HttpStatus httpStatus, ErrorCodes errorCodes) {
        super(message, httpStatus);
        this.message = message;
        this.httpStatus = httpStatus;
        this.errorCodes = errorCodes;
    }
}
