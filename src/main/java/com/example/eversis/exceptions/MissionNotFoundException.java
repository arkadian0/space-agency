package com.example.eversis.exceptions;

public class MissionNotFoundException extends RuntimeException {
    public MissionNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
