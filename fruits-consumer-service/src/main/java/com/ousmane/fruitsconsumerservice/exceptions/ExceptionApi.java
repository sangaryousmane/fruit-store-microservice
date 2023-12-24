package com.ousmane.fruitsconsumerservice.exceptions;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class ExceptionApi {

    private HttpStatus status;
    private String errorMessage;

    public ExceptionApi(){}

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
