package com.example.fruitsorderservice.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderNotFoundException extends RuntimeException{

    private String errorCode;

    public OrderNotFoundException() {
        super();
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
    public OrderNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
