package com.nomad.printboard.exceptions;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;


    public ApplicationException(String messsage) {
        super(messsage);
        this.message = messsage;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApplicationException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
