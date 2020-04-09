package com.example.eversis.exceptions;

public class ProductNotFoundExceptions extends RuntimeException {
    public ProductNotFoundExceptions(String errorMessage) {
        super(errorMessage);
    }
}
