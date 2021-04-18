package com.company.errors;

public class IncorrectPriceException extends RuntimeException {
    public IncorrectPriceException(String message) {
        super(message);
    }
}
