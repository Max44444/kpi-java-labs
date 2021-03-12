package com.company.errors;

public class IncorrectTourException extends RuntimeException{
    public IncorrectTourException(String message) {
        super(message);
    }
}
