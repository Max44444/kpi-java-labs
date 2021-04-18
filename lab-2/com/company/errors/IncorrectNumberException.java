package com.company.errors;

public class IncorrectNumberException extends RuntimeException {
    public IncorrectNumberException(String message) {
        super(message);
    }
}
