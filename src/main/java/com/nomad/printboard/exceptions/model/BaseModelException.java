package com.nomad.printboard.exceptions.model;

import com.nomad.printboard.exceptions.ApplicationException;
import com.nomad.printboard.exceptions.ErrorCodes;
import org.springframework.http.HttpStatus;

public class BaseModelException extends ApplicationException {

    private String message;
    private HttpStatus httpStatus;
    private ErrorCodes errorCodes;


    public BaseModelException(String messsage) {
        super(messsage);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.errorCodes = ErrorCodes.MODEL_ITEM_NOT_FOUND;
    }

    public BaseModelException(String message, HttpStatus httpStatus, ErrorCodes errorCodes) {
        super(message, httpStatus);
        this.message = message;
        this.httpStatus = httpStatus;
        this.errorCodes = errorCodes;
    }
}
