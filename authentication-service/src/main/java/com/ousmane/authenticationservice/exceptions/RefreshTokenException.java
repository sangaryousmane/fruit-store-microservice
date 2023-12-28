package com.ousmane.authenticationservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RefreshTokenException extends RuntimeException{

    public RefreshTokenException() {
    }

    public RefreshTokenException(String message) {
        super(message);
    }
}
