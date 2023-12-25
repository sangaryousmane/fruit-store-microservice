package com.example.fruitsorderservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
@Builder @AllArgsConstructor
public class ExceptionApi {

    private HttpStatus status;
    private String errorMessage;

    public ExceptionApi() {
    }

}
