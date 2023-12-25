package com.example.fruitsorderservice.exceptions;

public class OrderOutOfStockException extends RuntimeException{

    public OrderOutOfStockException() {
        super();
    }

    public OrderOutOfStockException(String message) {
        super(message);
    }
}
