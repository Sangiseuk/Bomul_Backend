package com.example.bomul_backend.common.exceptions;

public class FailGameCreationException extends RuntimeException {

    public FailGameCreationException(String errorMessage) {
        super(errorMessage);
    }
}
