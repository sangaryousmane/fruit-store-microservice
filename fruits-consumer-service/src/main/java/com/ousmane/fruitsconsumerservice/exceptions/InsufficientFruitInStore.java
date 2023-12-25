package com.ousmane.fruitsconsumerservice.exceptions;

public class InsufficientFruitInStore extends RuntimeException{

    public InsufficientFruitInStore() {
    }

    public InsufficientFruitInStore(String message) {
        super(message);
    }
}
