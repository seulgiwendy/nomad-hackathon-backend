package com.nomad.printboard.exceptions;

import com.nomad.printboard.exceptions.model.BaseModelException;
import com.nomad.printboard.exceptions.security.BaseSecurityException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionResponseResource {

    private String message;
    private int code;

    public ExceptionResponseResource(BaseSecurityException e) {
        this.message = e.getMessage();
        this.code = e.getErrorCodes().getCode();
    }

    public ExceptionResponseResource(BaseModelException e) {
        this.message = e.getMessage();
        this.code = e.getErrorCodes().getCode();
    }
}
