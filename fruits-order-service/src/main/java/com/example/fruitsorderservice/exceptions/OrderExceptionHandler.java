package com.example.fruitsorderservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ExceptionApi> orderNotFound(
        OrderNotFoundException ex){
        ExceptionApi api=ExceptionApi.builder()
                .errorMessage(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
        return ResponseEntity.ok(api);
    }
}
