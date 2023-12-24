package com.ousmane.fruitsconsumerservice.exceptions;

public class FruitNotFoundException extends RuntimeException{

    public FruitNotFoundException() {
        super();
    }

    public FruitNotFoundException(String message) {
        super(message);
    }
}
