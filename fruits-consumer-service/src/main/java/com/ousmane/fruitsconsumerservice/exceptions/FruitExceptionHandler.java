package com.ousmane.fruitsconsumerservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FruitExceptionHandler {

    @ExceptionHandler(FruitNotFoundException.class)
    public ResponseEntity<ExceptionApi> fruitNotFound(
            FruitNotFoundException ex){
        return ResponseEntity.ok(
                ExceptionApi.builder()
                        .errorMessage(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND)
                        .build());
    }
}
