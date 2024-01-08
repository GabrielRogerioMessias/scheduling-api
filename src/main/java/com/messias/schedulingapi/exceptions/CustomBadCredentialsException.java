package com.messias.schedulingapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CustomBadCredentialsException extends RuntimeException {

    public CustomBadCredentialsException(String message) {
        super(message);
    }
}
