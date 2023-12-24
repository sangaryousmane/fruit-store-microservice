package com.example.fruitsorderservice.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
@Builder
public class ExceptionApi {

    private HttpStatus status;
    private String errorMessage;

    public ExceptionApi() {
    }

    public ExceptionApi(String errorMessage, HttpStatus status) {
        this.errorMessage = errorMessage;
        this.status = status;
    }
}
